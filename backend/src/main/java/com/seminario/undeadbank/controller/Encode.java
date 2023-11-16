package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.dto.CredentialsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Encode {

    private final PasswordEncoder encoder;

    @PostMapping("/encode")
    public ResponseEntity<String> encode(@RequestBody CredentialsDto credentialsDto) {
        return ResponseEntity.ok(encoder.encode(credentialsDto.password()));
    }
}
