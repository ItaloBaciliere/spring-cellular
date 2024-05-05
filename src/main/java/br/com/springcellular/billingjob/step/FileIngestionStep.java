package br.com.springcellular.billingjob.step;

import br.com.springcellular.billingjob.model.BillingData;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;

@Configuration
public class FileIngestionStep {

  private final String STEP_NAME = "fileIngestion";

  @Bean
  public Step step2(
      JobRepository jobRepository, JdbcTransactionManager transactionManager,
      ItemReader<BillingData> billingDataFileReader, ItemWriter<BillingData> billingDataTableWriter) {
    return new StepBuilder(STEP_NAME, jobRepository)
        .<BillingData, BillingData>chunk(100, transactionManager)
        .reader(billingDataFileReader)
        .writer(billingDataTableWriter)
        .build();
  }
}
