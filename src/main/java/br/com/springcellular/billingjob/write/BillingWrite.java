package br.com.springcellular.billingjob.write;

import br.com.springcellular.billingjob.model.BillingData;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BillingWrite {

  @Bean
  public JdbcBatchItemWriter billingDataTableWriter(DataSource dataSource){
    String sql = "insert into BILLING_DATA values (:dataYear, :dataMonth, :accountId, :phoneNumber, :dataUsage, :callDuration, :smsCount)";
    return new JdbcBatchItemWriterBuilder<BillingData>()
        .dataSource(dataSource)
        .sql(sql)
        .beanMapped()
        .build();
  }
}
