package com.crud_restfulapi.securityy.controller;


import com.crud_restfulapi.model.User;
import com.crud_restfulapi.model.responseObject;
import com.crud_restfulapi.securityy.UserPrinciple;
import com.crud_restfulapi.securityy.jwt.JwtResponse;
import com.crud_restfulapi.securityy.jwt.JwtService;
import com.crud_restfulapi.securityy.service.IUserService;
//import com.example.officebuilding.securityy.entities.User;
//import com.example.officebuilding.securityy.jwt.JwtResponse;
//import com.example.officebuilding.securityy.jwt.JwtService;
//import com.example.officebuilding.securityy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody User user) {
       // try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

            //ResponseCookie jwtCookie = jwtService.generateJwtCookie(userDetails);

            String token = jwtService.generateTokenLogin(authentication);

//          return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(jwtCookie);
//           return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(token);

//            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            User currentUser = userService.findByUsername(user.getUsername()).get();
            return ResponseEntity.ok(new responseObject(authentication.getName(), token, userDetails.getAuthorities()));
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi đăng nhập");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.CreateUser(user);
        return new ResponseEntity<>("User register successfully!", HttpStatus.OK);
    }
    @PostMapping(value = "/signout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//
////        ResponseCookie cookie = jwtService.getCleanJwtCookie();
////        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
////                .body("Da dang xuat!");
//    }
}
