package org.omnirio.app.repository;

import org.omnirio.app.domain.RoleEntity;

import java.util.Optional;

public interface CustomRoleRepository {
    Optional<RoleEntity> findByRoleCode (String roleCode);
}
