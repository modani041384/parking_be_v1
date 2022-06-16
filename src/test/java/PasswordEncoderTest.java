import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        PasswordEncoderTest test = new PasswordEncoderTest();
        String password = "admin";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String data = passwordEncoder.encode(password);
        System.out.println("Data encode is: " + data);
        boolean match = passwordEncoder.matches(password, data);
        System.out.println("Check data  is: " + match);
        UUID uuid = UUID.randomUUID();
        System.out.println("password-admin123:" + passwordEncoder.encode("admin123"));
        System.out.println("userId:" + uuid.toString());
        System.out.println("password-customer123:" + passwordEncoder.encode("customer123"));
        System.out.println("userId:" + uuid.toString());
        System.out.println(passwordEncoder.matches("admin123",
                "$2a$10$GsDJmXxMCYF8wC2D3LxLiOXwAGQRo0dAI3YByWzZ9x/He9ShqSmSO"));
        System.out.println("Done!!!!");
    }
}
