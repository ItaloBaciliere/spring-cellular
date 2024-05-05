package br.com.springcellular.billingjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;

public class PartitionedStep{
  @Bean
  public Step partitionedtStep(JobRepository jobRepository, Partitioner partitioner) {
    return new StepBuilder("step1", jobRepository)
        .partitioner("worker", partitioner)
        .build();
  }
}

