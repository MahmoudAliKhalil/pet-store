package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.UserLoginDTO;
import eg.gov.iti.jets.petstore.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    void signUp(UserRegistrationDTO userRegistrationDTO);
    String signIn(UserLoginDTO userLoginDTO);
}
