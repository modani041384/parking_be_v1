import com.parking.engine.dto.JwtDTO;
import com.parking.engine.utils.JwtsUtils;

import java.util.Date;
import java.util.HashMap;

public class JwtUtilsTest {
    public static void main(String[] args) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000);
        //access-token with signature
        String accessToken = JwtsUtils.setJwtToken(new HashMap<>(), "3e5ad0fb-6a97-4889-bffb-8267aa0ae1cc",
                "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4", expiryDate);
        System.out.println("Access token with secret key:" + accessToken);
        JwtDTO jwtDTO = JwtsUtils.decodeJWT(accessToken);
        System.out.println("Header" + jwtDTO.getHeader());
        System.out.println("Pay-load:" + jwtDTO.getPayload());
        System.out.println("Signature:" + jwtDTO.getSignature());
        System.out.println("Done!!!!");
    }
}
