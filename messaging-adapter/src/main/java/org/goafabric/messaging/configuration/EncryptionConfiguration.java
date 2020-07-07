package org.goafabric.messaging.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.IvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class EncryptionConfiguration {

    @Bean //just so we don't have to put the bean name inside application.yml
    public StringEncryptor jasyptStringEncryptor() {
        return propertyEncryptor();
    }

    //property encryptor, always needed
    @Bean
    public PBEStringEncryptor propertyEncryptor() {
        return getAES256Encryptor("property_passphrase",
                new RandomIvGenerator(), new RandomSaltGenerator());
    }


    private StandardPBEStringEncryptor getAES256Encryptor(String configKey, IvGenerator ivGenerator, SaltGenerator saltGenerator) {
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(ivGenerator);
        encryptor.setSaltGenerator(saltGenerator);
        encryptor.setPassword("0251cb27-2b1b-4d64-850b-a754b3021769"); //should go to the DB
        return encryptor;
    }

    @Bean
    public CommandLineRunner encryptionCommandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            if ((args.length > 0) && (args[0].contains("-encryptproperty="))) {
                final String string = args[0].split("-encryptproperty=")[1].split("-terminate")[0];
                log.info("encrypted string will be: {}", propertyEncryptor().encrypt(string));
                SpringApplication.exit(applicationContext, () -> 0);
            }
        };
    }
}

