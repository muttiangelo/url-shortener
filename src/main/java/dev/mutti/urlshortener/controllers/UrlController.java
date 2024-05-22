package dev.mutti.urlshortener.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mutti.urlshortener.domain.Url;
import dev.mutti.urlshortener.services.UrlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public ResponseEntity<List<Url>> getUrl() {
        return ResponseEntity.ok(urlService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> saveUrl(@RequestBody @JsonProperty("url") String url) {
        return ResponseEntity.ok(urlService.shortenUrl(url));
    }

    @GetMapping("/{uniqueCode}")
    public ResponseEntity<String> getUrlByUniqueCode(@PathVariable String uniqueCode) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(urlService.getUrlByUniqueCode(uniqueCode));
    }
}
