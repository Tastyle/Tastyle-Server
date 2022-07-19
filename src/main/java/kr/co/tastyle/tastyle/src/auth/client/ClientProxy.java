package kr.co.tastyle.tastyle.src.auth.client;

import java.security.NoSuchAlgorithmException;

public interface ClientProxy {
    String getUserInfo(String accessToken) throws NoSuchAlgorithmException;
}
