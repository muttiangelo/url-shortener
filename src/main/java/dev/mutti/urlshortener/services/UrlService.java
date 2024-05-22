package dev.mutti.urlshortener.services;

import dev.mutti.urlshortener.domain.Url;
import dev.mutti.urlshortener.exceptions.InvalidUrlException;
import dev.mutti.urlshortener.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UrlService {

    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*$");

    private final UrlRepository urlRepository;

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    public String shortenUrl(String url) {
        if (!URL_PATTERN.matcher(url).matches()) {
            throw new InvalidUrlException("Invalid URL");
        }

        String uuid = UUID.randomUUID().toString();

        return urlRepository.save(new Url(uuid, url, uuid.substring(uuid.length() - 8),
                LocalDateTime.now(), LocalDateTime.now().plusDays(7))).getUniqueCode();
    }

    public String getUrlByUniqueCode(String uniqueCode) {
        return urlRepository.findByUniqueCode(uniqueCode).getUrl();
    }
}
