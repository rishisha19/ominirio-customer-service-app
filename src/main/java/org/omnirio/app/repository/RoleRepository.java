package org.omnirio.app.repository;

import org.omnirio.app.domain.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, String>, CustomRoleRepository {
}
