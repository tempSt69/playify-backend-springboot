package ad.com.playify.domain.port.out;

public interface AuthPort {

    String generateToken(String id);

    String encodePassword(String password);

    Boolean verifyPassword(String input, String persistence);

    Boolean validateToken(String token, String id);

}
