package com.pozharsky.dmitri.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.pozharsky.dmitri.util", "com.pozharsky.dmitri.service",
        "com.pozharsky.dmitri.validator","com.pozharsky.dmitri.controller"})
public class TestAppConfig {

}
