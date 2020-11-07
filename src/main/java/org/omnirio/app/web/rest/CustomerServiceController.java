package org.omnirio.app.web.rest;

import org.apache.commons.lang3.StringUtils;
import org.omnirio.app.domain.CustomerEntity;
import org.omnirio.app.domain.RoleEntity;
import org.omnirio.app.domain.UserAuthEntity;
import org.omnirio.app.repository.AuthRepository;
import org.omnirio.app.repository.CustomerRepository;
import org.omnirio.app.repository.RoleRepository;
import org.omnirio.app.utils.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerServiceController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<CustomerR>> getAllCustomer(){
        List<CustomerR> resultList = StreamSupport.stream(customerRepository.findAll().spliterator(), true)
                         .map(this::toRestCustomer)
                     .collect(Collectors.toList());

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getCustomer (@PathVariable @Valid String userId){
        Optional<CustomerEntity> customer = customerRepository.findById(userId);

        return customer.<ResponseEntity<Object>>map(
            customerEntity -> new ResponseEntity<>(toRestCustomer(customerEntity), HttpStatus.FOUND))
            .orElseGet(() -> new ResponseEntity<>(new ApiError(String.format("User with id %s not found", userId),
                                                               Collections.singletonList(Links.CREATE_USER.getLink())),
                                                  HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<CustomerR> createCustomer (@RequestBody CustomerR user){
        CustomerEntity customer = customerRepository.save(toEntityCustomer(user));
        Optional<RoleEntity> role = roleRepository.findByRoleCode("Customer");
        authRepository.save(new UserAuthEntity(customer.getUserId(), "test",role.map(RoleEntity::getRoleCode)
                                                                                        .orElseGet(() -> "")));
        if(StringUtils.isNoneBlank(customer.getUserId()))
            return new ResponseEntity<>(toRestCustomer(customer), HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateCustomer (@PathVariable @Valid String userId, @RequestBody CustomerR user){
        Optional<CustomerEntity> customer = customerRepository.findById(userId);
        if(customer.isPresent()){
            if(StringUtils.isNotBlank(user.getDateOfBirth())) customer.get().setDateOfBirth(Date.valueOf(user.getDateOfBirth()));
            if(StringUtils.isNotBlank(user.getGender())) customer.get().setGender(user.getGender());
            if(StringUtils.isNotBlank(user.getPhoneNumber()))  customer.get().setPhoneNumber(user.getPhoneNumber());
            if(StringUtils.isNotBlank(user.getUserName())) customer.get().setUserName(user.getUserName());

            CustomerEntity updated = customerRepository.save(customer.get());

            return new ResponseEntity<>(toRestCustomer(updated), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(new ApiError(String.format("User with id %s not found", userId),
                                                 Arrays.asList(Links.CREATE_USER.getLink(), Links.GET_USER.getLink())),
                                    HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> removeCustomer (@Valid String userId){

        if(!customerRepository.existsById(userId))
            return new ResponseEntity<>(new ApiError(String.format("User with id %s not found", userId),
                                                     Arrays.asList(Links.CREATE_USER.getLink(), Links.GET_USER.getLink())),
                                        HttpStatus.NOT_FOUND);

        customerRepository.deleteById(userId);
        return new ResponseEntity<>(String.format("User with id %s successfully deleted",userId), HttpStatus.OK);
    }

    private CustomerR toRestCustomer (CustomerEntity entity){
        return new CustomerR(entity.getUserId(), entity.getUserName(), entity.getDateOfBirth().toString(), entity.getGender(),
                             entity.getPhoneNumber());
    }

    private CustomerEntity toEntityCustomer (CustomerR rest){
        return new CustomerEntity(rest.getUserId(), rest.getUserName(), Date.valueOf(rest.getDateOfBirth()),
                             rest.getGender(),
                             rest.getPhoneNumber());
    }
}
