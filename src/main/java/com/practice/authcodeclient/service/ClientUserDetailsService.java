package com.practice.authcodeclient.service;

import com.practice.authcodeclient.model.ClientUser;
import com.practice.authcodeclient.model.ClientUserDetails;
import com.practice.authcodeclient.repository.ClientUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientUser> optionlUser = clientUserRepository.findByUsername(username);
        if(!optionlUser.isPresent()){
            throw new UsernameNotFoundException("invalid username or password!");
        }
        return new ClientUserDetails(optionlUser.get());
    }
}
