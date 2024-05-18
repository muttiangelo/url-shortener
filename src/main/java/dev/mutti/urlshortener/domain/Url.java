package dev.mutti.urlshortener.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    private String id;
    private String url;
    private String uniqueCode;
    private LocalDateTime dateAdded;
    private LocalDateTime dateExpires;
}
