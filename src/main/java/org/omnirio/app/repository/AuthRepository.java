package org.omnirio.app.repository;

import org.omnirio.app.domain.UserAuthEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<UserAuthEntity, String> {
}
