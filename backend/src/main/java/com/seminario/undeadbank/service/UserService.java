package com.seminario.undeadbank.service;

import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.User;
import com.seminario.undeadbank.model.UserInfoDetails;
import com.seminario.undeadbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BankException("Usuario no encontrado"));
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id, User user) {
        var oUser = repository.findById(id);

        if (oUser.isEmpty()) throw new BankException("User not found");

        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username)
                .map(UserInfoDetails::new)
                .orElseThrow(() -> new BankException("Usuario no encontrado"));
    }
}
