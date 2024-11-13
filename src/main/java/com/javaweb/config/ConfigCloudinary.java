package com.javaweb.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary cloudinary() {
        HashMap<String,String> config = new HashMap<>();
        config.put("cloud_name", "dycf8ddyw");
        config.put("api_key", "514245122451418");
        config.put("api_secret", "c16vKcInuiMDj2RWhbT_4c6kMC4");
        return new Cloudinary(config);
    }
}
