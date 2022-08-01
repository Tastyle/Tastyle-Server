package kr.co.tastyle.tastyle.src.auth.client;

import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientGoogle implements ClientProxy {
    private final WebClient webClient;
    private final String GOOGLE_URL = "https://oauth2.googleapis.com/tokeninfo";

    @Override
    public String getUserInfo(String accessToken) {
        JSONObject response = webClient.get()
                .uri(GOOGLE_URL, builder -> builder.queryParam("id_token", accessToken).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_TOKEN)))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR)))
                .bodyToMono(JSONObject.class)
                .block();

        String socialId = String.valueOf(response.get("sub"));

        return socialId;
    }
}
