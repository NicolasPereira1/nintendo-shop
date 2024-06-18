package nintendo.shop.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByUsername(String username);
}
