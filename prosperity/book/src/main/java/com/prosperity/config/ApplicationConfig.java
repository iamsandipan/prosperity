package com.prosperity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.data.export.BookExportConfig;
@Configuration
@Import({BookExportConfig.class})
public class ApplicationConfig {

}
