package project.shop.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.shop.book.Entity.RoleEntity;
import project.shop.book.Entity.UserEntity;
import project.shop.book.Repository.RoleRepository;
import project.shop.book.Repository.UserRepository;
import project.shop.book.security.JwtProvider;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }



    public Optional<String> signin(String email, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                token = Optional.of(jwtProvider.createToken(email,user.get().getUserId().toString(), user.get().getRoles()));
            } catch (AuthenticationException e){
                LOGGER.info("Log in failed for user {}", email);
            }
        }
        return token;
    }


    public Optional<UserEntity> signup(UserEntity userTemp) {
        System.out.print(userTemp.toString());
        LOGGER.info("New user attempting to sign in");
        Optional<UserEntity> user = Optional.empty();
        if (!userRepository.findByEmail(userTemp.getEmail()).isPresent()) {
            Optional<RoleEntity> role = roleRepository.findByName("user");
            userTemp.setPassword(passwordEncoder.encode(userTemp.getPassword()));
            userTemp.getRoles().add(role.orElse(null));
            user = Optional.of(userRepository.save(userTemp));
        }
        return user;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
