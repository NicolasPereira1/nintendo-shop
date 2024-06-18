package nintendo.shop.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nintendo.shop.user.UserEntity;
import nintendo.shop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User not found : %s", username))
        );
        Collection<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRole().name()));
        return new User(user.getUsername(), user.getPassword(), roles);
    }
}
