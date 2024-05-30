package dev.mutti.urlshortener.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUrlCommand {
    private String customCode;
    private String url;
}
