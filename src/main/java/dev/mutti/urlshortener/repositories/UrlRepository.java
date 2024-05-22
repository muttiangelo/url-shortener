package dev.mutti.urlshortener.repositories;

import dev.mutti.urlshortener.domain.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

    Url findByUniqueCode(String uniqueCode);
}
