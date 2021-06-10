package processors;

import javax.batch.api.chunk.ItemProcessor;

public class OpenInterestProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object item) throws Exception {
		System.out.println("processor");
		return item;
	}

}
