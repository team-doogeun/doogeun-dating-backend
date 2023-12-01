package com.project.dugeun.domain.signup.application;

import com.univcert.api.UnivCert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UnivCertService {

    @Value("${api.key}")
    private String apiKey;

    public Map<String, Object> certify(String email, String uniName) throws IOException {
        return UnivCert.certify(apiKey, email, uniName, false);
    }

    public Map<String, Object> certifyCode(String email, String univName, int code) throws IOException {
        return UnivCert.certifyCode(apiKey, email, univName, code);
    }

    public Map<String, Object> clear() throws IOException {
        return UnivCert.clear(apiKey);
    }
}