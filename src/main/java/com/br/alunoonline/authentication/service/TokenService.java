package com.br.alunoonline.authentication.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.alunoonline.authentication.model.UserModel;
import com.br.alunoonline.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    //public String gerarToken(UserModel usuario){

//        return JWT.create()
//                .withClaim("roles", usuario.getRoles().stream()
//                        .map( u -> u.getRoleName().toString())
//                        .toList())
//                .withSubject(usuario.getUsername())
//                .withIssuer("alunoOline")
//                .withExpiresAt(Date.from(LocalDateTime.now()
//                        .plusMinutes(10)
//                        .toInstant(ZoneOffset.of("-03:00"))))
//                .sign(Algorithm.HMAC256("secreta"));

   // }

    public TokenDto gerarToken(UserModel usuario) {

        List<String> roles = usuario.getRoles().stream()
                .map( u -> u.getRoleName().toString()).toList();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(usuario.getUsername(), roles, now, validity);
        var refreshToken = getRefreshToken(usuario.getUsername(), roles, now);

        return new TokenDto(usuario.getUsername(), true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString()
                + "/alunoOnline";
        return JWT.create()
                .withClaim("listaPerfeil", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }

    public String getSubject(String token) {
       return JWT.require(Algorithm.HMAC256(secretKey.getBytes()))
                .build().verify(token).getSubject();
    }

//    public TokenDto refreshToken(String refreshToken) {
//        if (refreshToken.contains("Bearer ")) refreshToken =
//                refreshToken.substring("Bearer ".length());
//
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(refreshToken);
//        String username = decodedJWT.getSubject();
//        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
//        return gerarToken(username, roles);
//    }

}
