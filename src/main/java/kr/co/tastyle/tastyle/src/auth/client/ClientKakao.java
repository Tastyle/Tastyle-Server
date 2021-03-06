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
public class ClientKakao implements ClientProxy {
    private final WebClient webClient;
    private final String KAKAO_URL = "https://kapi.kakao.com/v2/user/me";

    @Override
    public String getUserInfo(String accessToken) {
        JSONObject response = webClient.get()
                .uri(KAKAO_URL)
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_TOKEN)))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new CommonException(ErrorCode.INVALID_OAUTH_SERVER_ERROR)))
                .bodyToMono(JSONObject.class)
                .block();

        String socialId = String.valueOf(response.get("id"));

        return socialId;
    }
}
