package com.leonteqsecurity.cysec.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    public TokenService(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }
    public String generateJWT(Authentication authentication)
    {
        Instant now=Instant.now();
        String scope= authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));

        JwtClaimsSet claimsSet=JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(60*60*6)) //the token expires after 6 hours
                .subject(authentication.getName())
                .claim("roles",scope)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public Jwt decodeJWT(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            throw new JwtDecoderInitializationException("Error decoding JWT token", e);
        }
    }
    public  String getNameFromJWT(String rawToken) {
        String[] tokenList=rawToken.split(" ");
        String token=tokenList[1];
        Jwt jwt = decodeJWT(token);
        String name = jwt.getSubject();
        return name;
    }
}