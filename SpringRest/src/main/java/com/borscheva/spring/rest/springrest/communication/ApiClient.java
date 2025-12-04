package com.borscheva.spring.rest.springrest.communication;

import com.borscheva.spring.rest.springrest.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Getter
public class ApiClient {

    private RestTemplate restTemplate;
    private String URL = "http://94.198.50.185:7081/api/users";
    private String sessionCookie;

    @Autowired
    public ApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                URL,                    // Куда: http://94.198.50.185:7081/api/users
                HttpMethod.GET,         // Как: GET запрос
                null,                   // Тело запроса: пустое (нет данных для отправки)
                new ParameterizedTypeReference<List<User>>() {
                }  // Ожидаем List<User> в ответе
        );
        this.sessionCookie = response.getHeaders().getFirst("Set-Cookie");
        return response.getBody();
    }

    public void saveUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        // Сервер вернёт ЧАСТЬ КОДА в теле ответа, например: "abc123"
        String partOfCode = response.getBody();
        System.out.println("Часть кода: " + partOfCode);
    }

    public void updateUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        String partOfCode = response.getBody();
        System.out.println("Часть кода " + partOfCode);
    }

    public void deleteUser(Long id, HttpEntity<User> httpEntity) {
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        String partOfCode = response.getBody();
        System.out.println("Часть кода " + partOfCode);
    }
}
