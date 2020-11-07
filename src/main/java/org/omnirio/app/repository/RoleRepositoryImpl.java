package org.omnirio.app.repository;

import org.omnirio.app.domain.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RoleRepositoryImpl implements CustomRoleRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<RoleEntity> findByRoleCode (String roleCode) {
        RoleEntity result = jdbcTemplate.queryForObject(String.format("Select * from role where roleCode = %s",
                                                                      roleCode), RoleEntity.class);
        if( null != result)
            return Optional.of(result);
        return Optional.empty();
    }
}
