package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.AuthenticationResponse;
import eg.gov.iti.jets.petstore.dto.UserLoginDTO;
import eg.gov.iti.jets.petstore.dto.UserRegistrationDTO;
import eg.gov.iti.jets.petstore.entities.User;
import eg.gov.iti.jets.petstore.security.JwtUtil;
import eg.gov.iti.jets.petstore.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;

@RestController
@RequestMapping("/auth/")
public class AuthResource {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResource(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "SignUp User",
            description = "provide User information to SignUp"
    )
    @ApiResponse(responseCode = "201", description = "Successfully SignUp.")
    @ApiResponse(responseCode = "400", description = "Bad request, you must provide all the fields", content = @Content)
    @PostMapping("signUp")
    public ResponseEntity<HttpStatus> signUp ( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User Information ToSign Up", required = true) @RequestBody UserRegistrationDTO userRegistrationDTO){
        authService.signUp(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail()
                            , userLoginDTO.getPassword(), Collections.emptyList()));
        }catch (BadCredentialsException exception){
            throw new Exception("Incorrect UserName or Password " + exception);
        }
        final UserDetails userDetails =authService.loadUserByUsername(userLoginDTO.getEmail());
        Long userId = ((User) userDetails).getId();
        String token = jwtUtil.generateToken(userDetails, userId);
        return ResponseEntity.ok(new AuthenticationResponse(token,userId));
    }

//    @PostMapping(value = "/refresh")
//    public ResponseEntity<AuthenticationResponse> refreshToken(Principal principal) {
//        UserDetails userDetails = authService.loadUserByUsername(principal.getName());
//        String token = jwtUtil.generateToken(userDetails);
//        return  ResponseEntity.ok(new AuthenticationResponse(token));
//    }

}
