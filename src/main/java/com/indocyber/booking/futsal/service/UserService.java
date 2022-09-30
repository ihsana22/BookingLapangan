package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dto.user.RegisterUserDTO;
import com.indocyber.booking.futsal.entity.Users;

public interface UserService {
    String getAccountRole(String username);

    public Users registerUser(RegisterUserDTO dto);
}
