package br.com.springcellular.billingjob.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;

public class BillingJob implements Job {

    private JobRepository jobRepository;

    public BillingJob(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Override
    public String getName() {
        return "Billing Job";
    }

    @Override
    public void execute(JobExecution execution) {
//        try {
//            System.out.println("Executing job");
//            int valor = (10/0);
//            execution.setStatus(BatchStatus.COMPLETED);
//            execution.setExitStatus(ExitStatus.COMPLETED);
//            throw new Exception("Unable to process billing information");
//        } catch (Exception exception) {
//            execution.addFailureException(exception);
//            execution.setStatus(BatchStatus.COMPLETED);
//            execution.setExitStatus(ExitStatus.FAILED.addExitDescription(exception.getMessage()));
//        } finally {
//            this.jobRepository.update(execution);
//        }

        JobParameters jobParameters = execution.getJobParameters();
        String inputFile = jobParameters.getString("input.file");
        System.out.println("processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }
}
