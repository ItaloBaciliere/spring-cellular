package br.com.springcellular.billingjob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBatchTest
// @SpringBatchTest registers the JobLauncherTestUtils
// and JobRepositoryTestUtils
// as Spring beans in the test context
@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class BillingJobApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@BeforeEach
	void setUp(){
		/*
		* This method uses the JobRepositoryTestUtils to
		* clear all job executions before each test runs,
		*  so every run will have a fresh schema and won't
		*  be impacted by the metadata of other tests.*/
		this.jobRepositoryTestUtils.removeJobExecutions();
		/*
		* TODO - descomentar a linha de cima e
		*  	depois validar esse comando no banco de dados.
		* 	Verá dados duplicados, o que pode atrapalhar a execução
		*		da aplicação.
		*  	[~/exercises] $ docker exec postgres psql
		* 	-U postgres -c 'select * from BATCH_JOB_EXECUTION_PARAMS;'
		* */

		this.jobRepositoryTestUtils.removeJobExecutions();
		JdbcTestUtils.deleteFromTables(this.jdbcTemplate, "BILLING_DATA");
	}

	@Test
	void testJobExecution(CapturedOutput output) throws Exception{

		// given
		JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParametersBuilder()
				.addString("input.file", "src/main/resources/billing-2023-01.csv")
				.toJobParameters();

		// when
		JobExecution jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);

		// then
//		Assertions.assertTrue(output.getOut().contains("processing billing information from file /some/input/file"));
//		Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		Assertions.assertTrue(Files.exists(Paths.get("staging", "billing-2023-01.csv")));
		Assertions.assertEquals(1000, JdbcTestUtils.countRowsInTable(jdbcTemplate, "BILLING_DATA"));
	}

}
