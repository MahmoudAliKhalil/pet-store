package eg.gov.iti.jets.petstore.security;//package eg.gov.iti.jets.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import eg.gov.iti.jets.dto.UserAuthenticationDto;
//import eg.gov.iti.jets.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtUtil jwtUtil;
//
//
//    @Autowired
//    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        UserAuthenticationDto userAuthenticationDto = null;
//        try {
//            userAuthenticationDto = new ObjectMapper()
//                    .readValue(request.getInputStream(), UserAuthenticationDto.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                                new UsernamePasswordAuthenticationToken(userAuthenticationDto.getEmail()
//                                                             , userAuthenticationDto.getPassword(), Collections.emptyList());
//        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        return authenticate;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String name = authResult.getName();
//
//        User user = new User();
//        user.setEmail(name);
//        System.out.println(user.getEmail());
//        String token = jwtUtil.generateToken(user);
//        System.out.println(token);
//        response.addHeader("Authorization", "Bearer "+token);
//
//    }
//}
