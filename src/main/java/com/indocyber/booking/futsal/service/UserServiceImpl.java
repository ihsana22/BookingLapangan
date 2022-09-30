package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.configuration.PasswordConfiguration;
import com.indocyber.booking.futsal.dao.UserRepository;
import com.indocyber.booking.futsal.dto.user.RegisterUserDTO;
import com.indocyber.booking.futsal.entity.Users;
import com.indocyber.booking.futsal.utility.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findById(username);

        Users tempUser = null;
        if(optionalUsers.isPresent()){
            tempUser = optionalUsers.get();
        }

        if(tempUser == null){
            throw new UsernameNotFoundException("Username not found");
        }

        return new ApplicationUserDetails(tempUser);

    }

    @Override
    public String getAccountRole(String username) {
        Optional<Users> optionalUsers = userRepository.findById(username);
        Users tempUser = null;
        if (optionalUsers.isPresent()){
            tempUser = optionalUsers.get();
            return tempUser.getRole();
        }
        return "User Not Found";
    }

    @Override
    public Users registerUser(RegisterUserDTO dto) {
        String role = "Admin";
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        Users newUsers = new Users(dto.getUsername(),hashPassword,role, dto.getName());
       return userRepository.save(newUsers);
    }
}
