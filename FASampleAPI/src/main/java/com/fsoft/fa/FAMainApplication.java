package com.fsoft.fa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Main spring boot application
 */
@SpringBootApplication
public class FAMainApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(FAMainApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FAMainApplication.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FAMainApplication.class, args);

        Environment env = ctx.getEnvironment();

        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }

        log.info(
                "Access URLs:\n----------------------------------------------------------\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"), getLocalHostName(), env.getProperty("server.port"));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        String activeProfile = System.getProperty("spring.profiles.active");
        if (activeProfile == null) {
            activeProfile = "default";
        }
        return loadPropertyFiles(activeProfile);
    }

    public static PropertySourcesPlaceholderConfigurer loadPropertyFiles(String activeProfile) {
        String propertiesFilename = "application-" + activeProfile + ".properties";
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("config/" + propertiesFilename));
        return configurer;
    }

    private static String getLocalHostName() {
        InetAddress address;
        try {
            address = getFallbackLocalHost();
        } catch (Throwable t) {
            address = null;
        }
        if (address == null) {
            return "UNKNOWN";
        } else {
            return address.getHostName();
        }
    }

    private static InetAddress getFallbackLocalHost() {
        InetAddress candidateAddress = null;
        for (InetAddress inetAddr : getInetAddressesFromNetworkInterfaces()) {
            if (!inetAddr.isLoopbackAddress()) {

                if (inetAddr.isSiteLocalAddress()) {
                    return inetAddr;
                } else if (candidateAddress == null) {
                    candidateAddress = inetAddr;
                }
            }
        }
        return candidateAddress;
    }


    private static Collection<InetAddress> getInetAddressesFromNetworkInterfaces() {
        Collection<InetAddress> inetAddresses = new ArrayList<>(3);

        try {
            for (NetworkInterface iface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                inetAddresses.addAll(Collections.list(iface.getInetAddresses()));
            }
        } catch (SocketException e) {
            return inetAddresses; // empty list
        }

        return inetAddresses;
    }

}
