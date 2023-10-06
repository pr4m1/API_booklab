package com.reservasApi.packages.repository;

import com.reservasApi.packages.repository.models.UserE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserE,Long> {
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM UserE WHERE username = :username")
    Boolean existsByUsername(@Param("username") String username);
    @Query("SELECT u FROM UserE u WHERE u.username = :username")
    Optional<UserE> findByUsername(@Param("username")String username);

}
