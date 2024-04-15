package com.webserviceex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webserviceex.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
