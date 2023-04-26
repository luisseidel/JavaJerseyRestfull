package com.seidelsoft.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class MyApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.packages", "com.seidelsoft");

        return props;
    }
}
