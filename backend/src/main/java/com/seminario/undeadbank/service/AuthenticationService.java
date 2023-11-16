package com.seminario.undeadbank.service;

import com.seminario.undeadbank.dto.CredentialsDto;
import com.seminario.undeadbank.dto.TokenDto;
import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

//    public TokenDto signup()

    public TokenDto signin(CredentialsDto credentials) throws IOException {
        var user = userRepository.findByUsername(credentials.username());

        if (user.isEmpty())
            throw new BankException("invalid user request !").withStatus(HttpStatus.NOT_FOUND);

        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password()));
        if (authentication.isAuthenticated()) {
            var token = jwtService.generateToken(user.get());
            return new TokenDto(token);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
