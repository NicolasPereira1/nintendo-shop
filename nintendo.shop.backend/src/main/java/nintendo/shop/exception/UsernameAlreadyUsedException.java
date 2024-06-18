package nintendo.shop.exception;

public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String username) {
        super(String.format("The username : %s is already used.", username));
    }
}
