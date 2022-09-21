package ch.zli.m223;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {

    public static String getMockToken() {
        String token = Jwt.issuer("https://example.com/issuer")
                .upn("gaben@valve.com")
                .groups(new HashSet<>(Arrays.asList("User","Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();
        return token;
    }
}
