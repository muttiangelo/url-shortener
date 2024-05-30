package dev.mutti.urlshortener.services;

import dev.mutti.urlshortener.command.CustomUrlCommand;
import dev.mutti.urlshortener.domain.Url;
import dev.mutti.urlshortener.exceptions.InvalidUrlException;
import dev.mutti.urlshortener.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        return urlRepository.findByUniqueCode(uniqueCode).map(Url::getUrl).orElse(null);
    }

    public String saveWithCustomUniqueCode(CustomUrlCommand command) {
        if (isUniqueCodeTakenAndValid(command.getCustomCode())) {
            throw new InvalidUrlException("Custom code already exists");
        }

        return urlRepository.save(new Url(UUID.randomUUID().toString(), command.getUrl(), command.getCustomCode(),
                LocalDateTime.now(), LocalDateTime.now().plusDays(7))).getUniqueCode();
    }

    private boolean isUniqueCodeTakenAndValid(String customCode) {
        Optional<Url> url = urlRepository.findByUniqueCode(customCode);
        return url.map(value -> value.getDateExpires().isAfter(LocalDateTime.now())).orElse(false);
    }
}
