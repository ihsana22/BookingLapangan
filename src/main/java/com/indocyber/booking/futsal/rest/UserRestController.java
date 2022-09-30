package com.indocyber.booking.futsal.rest;

import com.indocyber.booking.futsal.dto.user.RegisterUserDTO;
import com.indocyber.booking.futsal.dto.user.RequestTokenDTO;
import com.indocyber.booking.futsal.dto.user.ResponseTokenDTO;
import com.indocyber.booking.futsal.entity.Users;
import com.indocyber.booking.futsal.service.UserService;
import com.indocyber.booking.futsal.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody RequestTokenDTO dto){

        try{
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

            Authentication authentication = authenticationManager.authenticate(token);

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", e);
        }
        String role = userService.getAccountRole(dto.getUsername());

        String token = jwtToken.generateToken(
                dto.getSubject(),
                dto.getUsername(),
                role
        );

        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO(dto.getUsername(), role, token);
        return new ResponseEntity<>(responseTokenDTO,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> addAccount(@Valid @RequestBody RegisterUserDTO dto){
        Users user =  userService.registerUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
