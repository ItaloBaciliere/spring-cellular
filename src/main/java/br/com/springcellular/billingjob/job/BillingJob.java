package br.com.springcellular.billingjob.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;

public class BillingJob implements Job {

    private String JOB_NAME = "Billing Job";
    private JobRepository jobRepository;

    public BillingJob(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Override
    public String getName() {
        return this.JOB_NAME;
    }

    @Override
    public void execute(JobExecution execution) {
        JobParameters jobParameters = execution.getJobParameters();
        String inputFile = jobParameters.getString("input.file");
        System.out.println("processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }
}
