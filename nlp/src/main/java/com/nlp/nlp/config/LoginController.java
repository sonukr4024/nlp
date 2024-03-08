//package com.nlp.nlp.config;
//
//import com.nlp.nlp.utils.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.security.Principal;
//import java.util.Collection;
//
//@RestController
//@RequestMapping("user")
//public class LoginController {
//    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//
//    @PostMapping("/login")
//    public Response test(@RequestBody LoginInput sessionRequest, Principal principal) {
//        Collection<GrantedAuthority> permissions = ((UsernamePasswordAuthenticationToken) principal).getAuthorities();
//        setSession(principal.getName(), permissions, sessionRequest);
//        return Response.setResponse(RequestContextHolder.currentRequestAttributes().getSessionId());
//    }
//
//    private void setSession(String userProjectId, Collection<GrantedAuthority> permissions, LoginInput sessionRequest) {
//        SessionUtils.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
//        SessionUtils.setUserId(userProjectId.split("#")[0]);
//        SessionUtils.setEmailId(sessionRequest.getEmailId());
//        SessionUtils.setEnv(sessionRequest.getEnv());
//        SessionUtils.setProduct(sessionRequest.getProduct());
//        SessionUtils.setProject(sessionRequest.getProject());
//        SessionUtils.setPermissions(permissions);
//        SessionUtils.setUtcOffset(sessionRequest.getOffset());
//    }
//}
