package com.wo.jwt.service;

import com.wo.jwt.payload.request.LoginRequest;
import com.wo.jwt.payload.response.RegisterResponse;
import com.wo.jwt.model.User;
import com.wo.jwt.payload.request.UserRequest;
import com.wo.jwt.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public RegisterResponse save(UserRequest request) throws Exception {
        try {
            User user = createUser(request);
            userRepository.save(user);
            return new RegisterResponse("Successfully registered");
        } catch (Exception e) {
            throw new Exception("Internal error" + e.getLocalizedMessage());
        }
    }

    @Override
    public User login(LoginRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()));
            return userRepository.findByEmail(request.email()).orElseThrow();
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new Exception("Invalid username/password");
        } catch (Exception e) {
            throw new Exception("Internal server error" + e.getLocalizedMessage());
        }
    }

    private User createUser(UserRequest request) {
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        return user;
    }

}
