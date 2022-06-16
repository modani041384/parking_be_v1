import com.parking.engine.utils.AESUtils;

public class AESUtilsTest {
    public static void main(String[] args) {
        final String secretKey = "Smart parking success!!!!";

        String originalString = "TestAESUtils with smart parking!!!!";
        String encryptedString = AESUtils.encrypt(originalString, secretKey) ;
        String decryptedString = AESUtils.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
