package nintendo.shop.user;

import lombok.AllArgsConstructor;
import nintendo.shop.dto.LoginDTO;
import nintendo.shop.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok(userService.register(dto));
    }
}
