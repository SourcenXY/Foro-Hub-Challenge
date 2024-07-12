package com.alura.foro_hub.security;

import com.alura.foro_hub.databaseRepositories.UserRepository;
import com.alura.foro_hub.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  implements UserDetailsService {
    @Autowired
    UserRepository usersRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }
}
