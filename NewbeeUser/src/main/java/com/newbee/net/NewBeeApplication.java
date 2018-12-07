package com.newbee.net;

import com.newbee.net.constant.ApplicationConstant;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
@Configuration
public class NewBeeApplication {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NewBeeApplication.class);

    @Autowired
    private Environment env;

    /**
     * Initializes app.
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOGGER.warn("没有指定加载的配置文件，将加载默认的配置文件!");
        } else {
            LOGGER.info("加载指定的配置文件 : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(NewBeeApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();
        LOGGER.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port")+env.getProperty("server.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port")+env.getProperty("server.context-path"));
    }

    /**
     * 设置DispatcherServlet遇到404错误时,能抛出错误到GlobalControllerExceptionHandler
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

    /**
     * Set a default profile if it has not been set
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty(ApplicationConstant.SPRING_PROFILE_ACTIVE)) {
            app.setAdditionalProfiles(ApplicationConstant.DEVELOPMENT_CHINA);
        }
    }
}
