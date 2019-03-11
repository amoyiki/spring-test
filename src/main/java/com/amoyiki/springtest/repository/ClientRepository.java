package com.amoyiki.springtest.repository;

import com.amoyiki.springtest.entry.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author amoyiki
 * @since 2019/3/11
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c FROM Client c WHERE c.clientId=:clientId")
    Client findByClientId(String clientId);
    @Query(value = "SELECT c FROM Client c WHERE c.clientSecret=:clientSecret")
    Client findByClientSecret(String clientSecret);

}
