package com.newbee.net.config;

import com.newbee.net.swagger2.Swagger2Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = { Swagger2Properties.class })
public class AutoConfig {

}
