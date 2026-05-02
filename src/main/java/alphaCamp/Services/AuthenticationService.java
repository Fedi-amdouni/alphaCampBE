package alphaCamp.Services;

import alphaCamp.Models.Ambassador;
import alphaCamp.Models.Participant;
import alphaCamp.Models.UserBase;
import alphaCamp.Models.Vendor;
import alphaCamp.Repositories.UserRepository;
import alphaCamp.dtos.LoginUserDto;
import alphaCamp.dtos.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserBase signup(RegisterUserDto input) {
        UserBase user;

        if (input.getRole() == null) {
            throw new IllegalArgumentException("Role must be provided");
        }

        switch (input.getRole().toUpperCase()) {
            case "PARTICIPANT":
                user = new Participant();
                break;
            case "VENDOR":
                user = new Vendor();
                break;
            case "AMBASSADOR":
                user = new Ambassador();
                break;
            default:
                throw new IllegalArgumentException("Unsupported role: " + input.getRole());
        }

        user.setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setEmail(input.getEmail())
                .setPhoneNumber(input.getPhoneNumber())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public UserBase authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}