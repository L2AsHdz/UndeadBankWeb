package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.dto.CredentialsDto;
import com.seminario.undeadbank.dto.TokenDto;
import com.seminario.undeadbank.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> getToken(@RequestBody CredentialsDto credentials) throws IOException {
        return ResponseEntity.ok(authenticationService.signin(credentials));
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String customer() {
        return "Welcome Customer";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "Welcome Admin";
    }

}
