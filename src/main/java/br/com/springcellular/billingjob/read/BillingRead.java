package br.com.springcellular.billingjob.read;

import br.com.springcellular.billingjob.model.BillingData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BillingRead {

  private final String READ_NAME = "billingDataFileReader";

  @Bean
  public FlatFileItemReader<BillingData> billingDataFileReader(){
    return new FlatFileItemReaderBuilder<BillingData>()
        .name(this.READ_NAME)
        .resource(new FileSystemResource("/staging/billing-2023-01.csv"))
        .delimited()
        .names("dataYear", "dataMonth", "accountId", "phoneNumber", "dataUsage", "callDuration", "smsCount")
        .targetType(BillingData.class)
        .build();
  }
}
