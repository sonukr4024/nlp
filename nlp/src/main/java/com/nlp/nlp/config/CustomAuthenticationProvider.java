//package com.nlp.nlp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        if (StringUtils.hasText(username)) {
//            if (validateUser(username, password)) {
//                List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", Arrays.asList("OK", "OKAY")));
//                return new UsernamePasswordAuthenticationToken(username + "#" + username, password, authorityList);
//            }
//        }
//        throw new BadCredentialsException("Invalid credentials");
//    }
//
//    private boolean validateUser(String username, String password) {
//        if (username.equalsIgnoreCase("abcd")) {
//            return true;
//        }
//        return false;
//    }
//
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}