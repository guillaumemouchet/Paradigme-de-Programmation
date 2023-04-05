package ch.hearc.Corrigés.Serie7.Exercice2;
import java.util.concurrent.Callable;

public class MatrixLineAdder implements Callable<Integer[]> {

	private Integer[] line1, line2;
	int size;

	public MatrixLineAdder(Integer[] line1, Integer[] line2) {
		this.line1 = line1;
		this.line2 = line2;
	}

	@Override
	public Integer[] call() throws Exception {

		int size = line1.length;
		Integer[] returnLine = new Integer[size];

		for (int i = 0; i < size; i++) {
			returnLine[i] = line1[i] + line2[i];
		}

		return returnLine;
	}
}
