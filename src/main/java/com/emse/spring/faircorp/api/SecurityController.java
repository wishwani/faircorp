//package com.emse.spring.faircorp.api;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
////@RestController
//public class SecurityController {
//    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    //String currentPrincipalName = authentication.getName();
//
//    @GetMapping(path = "/{id}")
//    public String findUserName(@AuthenticationPrincipal UserDetails userDetails) {
//        return userDetails.getUsername();
//    }
//
//    @Secured("ROLE_ADMIN") // (1)
//    @GetMapping
//    public ResponseEntity<String> findAll(@AuthenticationPrincipal UserDetails userDetails) {
//        return ResponseEntity.ok(userDetails.getUsername());
//    }
//}