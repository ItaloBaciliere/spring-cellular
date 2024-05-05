package br.com.springcellular.billingjob.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Requires a PlatformTransactionManager
 * to manager the transaction of the tasklet.
 *
* */
public class tasklet {

  public RepeatStatus execute(
      /* StepContribution - represents the contribution of this Tasklet to the step (for example how many items were read, written, or otherwise processed) */
      StepContribution contribution,
      /* ChunkContext - object, which is a bag of key/value pairs that provide detail about the execution context of the Tasklet */
      ChunkContext chunkContext
  ) throws Exception {

    return null;
  }
}
