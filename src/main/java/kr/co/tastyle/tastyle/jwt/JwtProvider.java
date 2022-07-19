package kr.co.tastyle.tastyle.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static kr.co.tastyle.tastyle.common.exception.ErrorCode.WRONG_TOKEN_SIGNATURE;

@Slf4j
@Component
public class JwtProvider {
    private final Key SECRET_KEY;
    private final long ACCESS_TOKEN_EXPIRE_TIME;
    private final long REFRESH_TOKEN_EXPIRE_TIME;

    private final String USER_ID = "userId";
    private final String USER_SOCIAL_ID = "socialId";
    private final String USER_PLATFORM = "platform";

    public JwtProvider(@Value("${jwt.secret-key}") String secretKey,
                       @Value("${jwt.access-expire-time}") long accessTokenExpireTime,
                       @Value("${jwt.refresh-expire-time}") long refreshTokenExpireTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
        this.ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;
        this.REFRESH_TOKEN_EXPIRE_TIME = refreshTokenExpireTime;
    }

    public String createAccessToken(SecurityUser user) {
        return createToken(user, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String createRefreshToken(SecurityUser user) {
        return createToken(user, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String createToken(SecurityUser user, long time) {
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + time);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(createClaims(user))
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    private Map<String, Object> createClaims(SecurityUser securityUser) {
        Map<String, Object> map = new HashMap<>();
        map.put(USER_ID, securityUser.getUserId());
        map.put(USER_PLATFORM, securityUser.getUserDeviceType());
        map.put(USER_SOCIAL_ID, securityUser.getUsername());
        return map;
    }

    public boolean validateToken(String token) {
        try {
            parse(token);
            return true;
        }
        catch (MalformedJwtException exception) {
            log.error("잘못된 JWT 서명입니다.");
            throw new CommonException(WRONG_TOKEN_SIGNATURE);
        }
        catch (ExpiredJwtException exception) {
            log.error("만료된 토큰입니다.");
            throw new CommonException(ErrorCode.EXPIRED_TOKEN);
        }
        catch (UnsupportedJwtException exception) {
            log.error("지원하지 않는 토큰입니다.");
            throw new CommonException(ErrorCode.UNSUPPORTED_TOKEN);
        }
        catch (IllegalArgumentException exception) {
            log.error("잘못된 토큰입니다.");
            throw new CommonException(ErrorCode.WRONG_TOKEN);
        }
    }

    public String getSocialId(String token) {
        return parseClaims(token)
                .get(USER_SOCIAL_ID, String.class);
    }

    private Claims parseClaims(String token) {
        try {
            return parse(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
    }
}
