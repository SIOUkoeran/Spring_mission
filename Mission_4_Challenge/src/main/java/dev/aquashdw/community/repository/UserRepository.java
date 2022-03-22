package dev.aquashdw.community.repository;

import dev.aquashdw.community.entity.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @EntityGraph
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);
}
