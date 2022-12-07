package org.hotel.dao;

import org.hotel.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, String> {


}

