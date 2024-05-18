package dev.mutti.urlshortener.controllers;

import dev.mutti.urlshortener.repositories.UrlRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mutti.urlshortener.domain.Url;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/url")
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping
    public List<Url> getUrl() {
        return urlRepository.findAll();
    }

    @PostMapping
    public Url saveUrl(@RequestBody String url) {
        Url urlObj = new Url(UUID.randomUUID().toString(), url, UUID.randomUUID().toString().substring(0, 8), LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        return urlRepository.save(urlObj);
    }
}
