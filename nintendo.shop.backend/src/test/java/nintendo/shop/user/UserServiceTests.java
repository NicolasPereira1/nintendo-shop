package nintendo.shop.user;

import nintendo.shop.dto.LoginDTO;
import nintendo.shop.exception.UsernameAlreadyUsedException;
import nintendo.shop.security.JWTService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private final String USERNAME = "name";
    private final String PASSWORD = "pass";

    @Test
    void userShouldBeAbleToRegisterWithLoginDTO() {
        LoginDTO dto = new LoginDTO(USERNAME, PASSWORD);

        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(new UserEntity()));

        assertThrows(UsernameAlreadyUsedException.class, () -> userService.register(dto));
    }

    @Test
    void usernameShouldBeAlreadyUsed() {
        LoginDTO dto = new LoginDTO(USERNAME, PASSWORD);

        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.save(any())).thenReturn(null);

        assertEquals("Successfully regitered!", userService.register(dto));
    }
}
