package com.infodev.auth.application.service;

import com.infodev.auth.application.domain.model.account.User;
import com.infodev.auth.application.infrastructure.persistence.hibernate.repository.UserRepository;
import com.infodev.auth.application.presentation.dto.user.RegisterRequestTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void authenticate(String email, String password) {

        User user = userRepository.findByEmail(email);
        boolean encodedPassword = passwordEncoder.matches(password, user.getPassword());

        if (encodedPassword) {
            return;
        }
        throw new RuntimeException("Usuario incorreto.");
    }

    public void register(RegisterRequestTO registerRequestTO) {

        if (userRepository.findByEmail(registerRequestTO.getEmail()) != null) {
            throw new RuntimeException("Usuario já existente.");
        }

        String encodedPassword = passwordEncoder.encode(registerRequestTO.getPassword());

        User user = new User();
        user.setName(registerRequestTO.getName());
        user.setEmail(registerRequestTO.getEmail());
        user.setPassword(encodedPassword);

        userRepository.save(user);  
    }
}
