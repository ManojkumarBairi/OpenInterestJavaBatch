package writers;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;

public class OpenInterestWriter extends AbstractItemWriter{

	@Override
	public void writeItems(List<Object> items) throws Exception {
		System.out.println("Write Items");
		
	}

	
	
}
