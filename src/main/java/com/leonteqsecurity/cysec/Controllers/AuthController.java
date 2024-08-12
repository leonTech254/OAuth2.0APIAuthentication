package com.leonteqsecurity.cysec.Controllers;

import com.leonteqsecurity.cysec.Models.UserModel;
import com.leonteqsecurity.cysec.Security.TokenService;
import com.leonteqsecurity.cysec.Security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/")
@AllArgsConstructor
public class AuthController {
    private  final UserService userService;
    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<String> LoginUser(@RequestBody UserModel user)
    {
        String username=user.getUsername();
        String password=user.getPassword();
       String usertoken= userService.userLogin(username,password);
      return   ResponseEntity.status(200).body(usertoken);

    }

    @GetMapping("get_my_keys")
    public  ResponseEntity<Map<String,String>> getMyKeys(@RequestHeader("Authorization") String token)
    {
       return ResponseEntity.status(200).body(userService.generateMyKeys(token));

    }

}
