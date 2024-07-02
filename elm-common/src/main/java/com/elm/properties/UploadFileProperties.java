package com.elm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "elm.upload")
@Data
public class UploadFileProperties {

    private String tempPath;
    private String savePath;
    private Map<String,String> extensions = new HashMap<String,String>();
}
