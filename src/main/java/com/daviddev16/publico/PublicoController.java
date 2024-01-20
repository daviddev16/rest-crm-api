package com.daviddev16.publico;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/publico")
public class PublicoController {

    public final PublicoConfiguration publicoConfiguration;

    public PublicoController(PublicoConfiguration publicoConfiguration) {
        this.publicoConfiguration = publicoConfiguration;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PublicoConfiguration publicoConfiguration() {
        return publicoConfiguration;
    }

}
