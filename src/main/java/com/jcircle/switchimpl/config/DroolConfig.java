package com.jcircle.switchimpl.config;

import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.kie.api.KieServices;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import java.io.IOException;


@Configuration
public class DroolConfig {

    private KieServices kieServices = KieServices.Factory.get();

    private static final String RULES_PATH = "rules/";

    /**
     * Read all the .DRL / .xls rule files under the resources folder
     * @return
     * @throws IOException
     */
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    /**
     * Create the File System handler to access all the DRL files in UTF-8 format.
     * @return
     * @throws IOException
     */
    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = this.kieServices.newKieFileSystem();
        // kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH+"movie.xlsx"));

        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }

        return kieFileSystem;
    }

    /**
     * Initializing the KieContainer with the DROOLS file system.
     *
     * @return
     * @throws IOException
     */
    @Bean
    public KieContainer getKieContainer() throws IOException {

        this.getKieRepository();
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kieContainer;

    }

    /**
     *  Creating the Rules repository
     *
     */
    private void getKieRepository() {
        final KieRepository kieRepository = this.kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });


    }

    /**
     * Creating thr KieSession to validate the rules during run time.
     * @return
     * @throws IOException
     */
    @Bean
    public KieSession getKieSession() throws IOException {

        return getKieContainer().newKieSession();
    }




}
