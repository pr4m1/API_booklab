package com.reservasApi.packages.repository;

import com.reservasApi.packages.repository.models.TokenE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenE, Long> {

    @Query(value = """
      select t from TokenE t inner join UserE u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenE> findAllValidTokenByUser(Long id);

    Optional<TokenE> findByToken(String token);
}
