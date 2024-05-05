package br.com.springcellular.billingjob.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingJobConfig {

    private final String JOB_NAME = "BillingJob";

    @Bean
    public Job job(
        JobRepository jobRepository,
        Step step1,
        Step step2
    ){
        return new JobBuilder(JOB_NAME, jobRepository)
            .start(step1)
            .next(step2)
            .build();
    }

}
