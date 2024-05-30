package dev.mutti.urlshortener.repositories;

import dev.mutti.urlshortener.domain.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

    Optional<Url> findByUniqueCode(String uniqueCode);
}
