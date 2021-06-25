package eg.gov.iti.jets.petstore.services.Impl;

import eg.gov.iti.jets.petstore.dto.UserLoginDTO;
import eg.gov.iti.jets.petstore.dto.UserRegistrationDTO;
import eg.gov.iti.jets.petstore.entities.User;
import eg.gov.iti.jets.petstore.repositories.UserRepository;
import eg.gov.iti.jets.petstore.security.model.CustomUserDetails;
import eg.gov.iti.jets.petstore.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserRegistrationDTO signUp(UserRegistrationDTO userRegistrationDTO) {
        userRegistrationDTO.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setActive(true);
        user.setNotLocked(true);
        User save = userRepository.save(user);
        return modelMapper.map(save, UserRegistrationDTO.class);
    }

    @Override
    public String signIn(UserLoginDTO userLoginDTO) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return new CustomUserDetails(user);
    }

}
