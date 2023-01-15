package it.aci.informatica.nstar.anagrafi.flusso.convenzioni;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class FlussoConvenzioniLegacyApplication {

    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(FlussoConvenzioniLegacyApplication.class, args);

        CamelSpringBootApplicationController applicationController = ctx.getBean(CamelSpringBootApplicationController.class);
        applicationController.run();
        
    }
}