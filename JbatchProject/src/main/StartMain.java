import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;



public class StartMain {

	public static void main(String[] args) {
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
		    Long executionId = jobOperator.start("simpleBatchLet", null);
		    System.out.println(executionId);
		    //JobExecution jobExecution = jobOperator.getJobExecution(executionId);
		    System.out.println("Batchlet submitted: " + executionId);
            Thread.sleep(5000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}

	}

}
