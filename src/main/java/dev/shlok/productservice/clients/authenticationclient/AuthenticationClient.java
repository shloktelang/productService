package dev.shlok.productservice.clients.authenticationclient;

import dev.shlok.productservice.clients.authenticationclient.dtos.ValidateTokenRequestDto;
import dev.shlok.productservice.clients.authenticationclient.dtos.ValidatetokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidatetokenResponseDto validate(String token, Long userId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidatetokenResponseDto> l = restTemplate.postForEntity(
                "http://localhost:8090/auth/validate",
                request,
                ValidatetokenResponseDto.class
        );

        return l.getBody();
    }
}
