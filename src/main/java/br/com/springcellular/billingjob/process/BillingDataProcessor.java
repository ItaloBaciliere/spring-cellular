package br.com.springcellular.billingjob.process;

import br.com.springcellular.billingjob.model.BillingData;

public class BillingDataProcessor implements ItemProcessor<BillingData, ReportingData> {

  public ReportingData process(BillingData item) {
    return new ReportingData(item);
  }
}
