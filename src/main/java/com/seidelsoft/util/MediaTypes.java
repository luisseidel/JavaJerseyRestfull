package com.seidelsoft.util;

import jakarta.ws.rs.core.MediaType;

public class MediaTypes extends jakarta.ws.rs.core.MediaType {
    public static final String APPLICATION_JSON_UTF_8 = "application/json;charset=utf-8";
    public static final MediaType APPLICATION_JSON_TYPE_UTF_8 = new MediaType("application", "json", "utf-8");
}
