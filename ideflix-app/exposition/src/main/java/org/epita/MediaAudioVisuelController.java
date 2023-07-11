package org.epita;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class MediaAudioVisuelController {
    @GetMapping
    public String test() {
        return "";
    }
}
