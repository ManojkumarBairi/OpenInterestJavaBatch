package readers;

import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

public class OpenInterestReader extends AbstractItemReader {
	

	@Inject	JobContext jobContext;
	
	@Inject StepContext stepContext;
	
	@Override
	public void open(Serializable checkpoint) throws Exception { 
		
		System.out.println("Started");
	}
	
	
	@Override
	public Object readItem() 
	{
		String s ="Manoj";
		System.out.println("readItem");
		jobContext.setTransientUserData(s);
		return s;
		
	}

}
