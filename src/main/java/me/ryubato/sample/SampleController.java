package me.ryubato.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    public static final Logger log = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/test/params")
    public void test(SampleReqDto dto) {
        log.info(dto.toString());
    }
}
