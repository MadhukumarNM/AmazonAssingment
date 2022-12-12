package com.amazon.webdriverfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.amazon")
public class SpringConfiguration {

            @Bean
            public DriverHelper driverHelper(){
                return new DriverHelperImpl();
            }
}
