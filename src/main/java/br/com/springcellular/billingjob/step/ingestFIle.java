package br.com.springcellular.billingjob.step;

import br.com.springcellular.billingjob.model.BillingData;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class ingestFIle {

  @Bean
  public Step ingestFile(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      FlatFileItemReader<BillingData> billingDataFileReader,
      JdbcBatchItemWriter<BillingData> billingDataTableWriter) {

    return new StepBuilder("fileIngestion", jobRepository)
        .<BillingData, BillingData>chunk(100, transactionManager)
        .reader(billingDataFileReader)
        .writer(billingDataTableWriter)
        .build();
  }

}
