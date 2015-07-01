package com.prosperity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.data.export.BookExportConfig;
import com.data.security.export.SecurityExport;
@Configuration
@Import({BookExportConfig.class, SecurityExport.class})
public class ApplicationConfig {

}
