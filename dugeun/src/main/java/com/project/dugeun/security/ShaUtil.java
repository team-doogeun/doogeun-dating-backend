package com.project.dugeun.security;

import com.google.common.hash.Hashing;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class ShaUtil {
    public String sha256Encode(String plainText){

        return Hashing.sha256()
                .hashString(plainText, StandardCharsets.UTF_8)
                .toString();
    }
}
