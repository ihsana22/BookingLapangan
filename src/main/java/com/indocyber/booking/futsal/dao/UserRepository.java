package com.indocyber.booking.futsal.dao;

import com.indocyber.booking.futsal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, String> {
}