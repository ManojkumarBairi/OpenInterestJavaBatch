package businessObjects;

import java.math.BigDecimal;
import java.util.List;

public class OpenInterest {
private BigDecimal numbers;

public BigDecimal getNumbers() {
	return numbers;
}

public void setNumbers(BigDecimal numbers) {
	this.numbers = numbers;
}

private BigDecimal futuresprice;

private List<List<Double>> datahc;

public  List<List<Double>> getDatahc() {
	return datahc;
}

public void setDatahc( List<List<Double>> datahc) {
	this.datahc = datahc;
}

public BigDecimal getFuturesprice() {
	return futuresprice;
}

public void setFuturesprice(BigDecimal futuresprice) {
	this.futuresprice = futuresprice;
}




}
