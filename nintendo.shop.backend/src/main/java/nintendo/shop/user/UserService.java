package nintendo.shop.user;

import lombok.AllArgsConstructor;
import nintendo.shop.dto.LoginDTO;
import nintendo.shop.dto.TokenDTO;
import nintendo.shop.exception.UsernameAlreadyUsedException;
import nintendo.shop.role.RoleEntity;
import nintendo.shop.role.RoleEnum;
import nintendo.shop.security.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JWTService jwtService;
    private PasswordEncoder passwordEncoder;

    public TokenDTO login(@RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new TokenDTO(token, "Bearer");
    }

    public String register(@RequestBody LoginDTO dto) {
        RoleEntity role = new RoleEntity();
        role.setRole(RoleEnum.ADMIN);
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyUsedException(user.getUsername());
        userRepository.save(user);
        return "Successfully registered!";
    }
}
