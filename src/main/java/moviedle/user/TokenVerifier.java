package moviedle.user;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class TokenVerifier {

    private final GsonFactory jsonFactory = new GsonFactory();
    private final NetHttpTransport transport = new NetHttpTransport();
    private final String CLIENT_ID = "747713817851-sknc38jco126c6g11aiqjk5otbh1e28k.apps.googleusercontent.com";

    private final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

    public GoogleUser authenticate(String idTokenString){
        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
                Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                // Use or store profile information
                // ...
                System.out.println(userId + " " + " " + email + " " + emailVerified + " " + name + " " + givenName);
                return new GoogleUser(userId,email);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
