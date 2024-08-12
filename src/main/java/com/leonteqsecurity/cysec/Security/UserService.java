package com.leonteqsecurity.cysec.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private  ApiKeyService apiKeyService;

    // Mocked user data
    private static final Map<String, String> USER_DATA = new HashMap<>();

    static {
        // Mocked users
        USER_DATA.put("Amiya", "password"); // Admin user
        USER_DATA.put("Ejaz", "password");  // Regular user
        USER_DATA.put("martinleon", "12345678");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user Details: " + username);

        // Check if the user exists in the mocked data
        if (!USER_DATA.containsKey(username)) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        // Get the password from the mocked data
        String password = USER_DATA.get(username);
        System.out.println("Password before encoding: " + password);

        // Encode the password before returning the UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // Encode the password
                .roles(username.equals("Amiya") ? "ADMIN" : "USER") // Assign roles based on username
                .build();
    }

    public String checkUser(String token) {
        System.out.println(token);
        return tokenService.getNameFromJWT(token);
    }

    public String userLogin(String username, String password) {
        System.out.println("In the user login: " + username);

        // Check if the user exists in the mocked data
        if (!USER_DATA.containsKey(username)) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        // Validate the password
        String storedPassword = USER_DATA.get(username);
        if (!passwordEncoder.matches(password, passwordEncoder.encode(storedPassword))) {
            throw new BadCredentialsException("Bad credentials");
        }

        // Create an Authentication object
        UserDetails userDetails = loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Generate and return the JWT token
        return tokenService.generateJWT(authentication);
    }

    public Map<String, String> generateMyKeys(String token)
    {
        String clientId="2342324";
        return apiKeyService.generateAllKeys(clientId);
    }
}
