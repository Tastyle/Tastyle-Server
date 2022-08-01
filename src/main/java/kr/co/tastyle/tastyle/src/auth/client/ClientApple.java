package kr.co.tastyle.tastyle.src.auth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jsonwebtoken.*;
import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import kr.co.tastyle.tastyle.src.auth.dto.response.ApplePublicKeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientApple implements ClientProxy {
    private final WebClient webClient;
    private final String APPLE_URL = "https://appleid.apple.com/auth/keys";

    @Override
    public String getUserInfo(String accessToken) {
        try {
            ApplePublicKeyResponse applePublicKeyResponse = webClient.get()
                    .uri(APPLE_URL)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_TOKEN)))
                    .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR)))
                    .bodyToMono(ApplePublicKeyResponse.class)
                    .block();

            String headerOfIdentityToken = accessToken.substring(0, accessToken.indexOf("."));
            Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);

            ApplePublicKeyResponse.Key key = applePublicKeyResponse.getMatchedKeyBy(header.get("kid"), header.get("alg"))
                    .orElseThrow(() -> new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR));

            byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
            byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            Claims claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(accessToken).getBody();
            JsonObject userInfoObject = new Gson().fromJson(new Gson().toJson(claims),JsonObject.class);
            JsonElement appleAlg = userInfoObject.get("sub");
            String socialId = appleAlg.getAsString();

            return socialId;
        }
        catch (NoSuchAlgorithmException e) {
            throw new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR);
        }
        catch (InvalidKeySpecException e) {
            throw new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR);
        }
        catch (JsonProcessingException e) {
            throw new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR);
        }
        catch (UnsupportedEncodingException e) {
            throw new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR);
        }
    }
}