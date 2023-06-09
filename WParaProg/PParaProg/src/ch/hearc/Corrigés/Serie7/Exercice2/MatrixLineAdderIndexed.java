package ch.hearc.Corrig�s.Serie7.Exercice2;
import java.util.concurrent.Callable;

public class MatrixLineAdderIndexed implements Callable<Integer[]> {

	private Integer[] line1, line2;
	int size;
	int index;

	public MatrixLineAdderIndexed(Integer[] line1, Integer[] line2, int index) {
		this.line1 = line1;
		this.line2 = line2;
		this.index = index;
	}

	@Override
	public Integer[] call() throws Exception {

		int size = line1.length;
		Integer[] returnLine = new Integer[size + 1];

		for (int i = 0; i < size; i++) {
			returnLine[i] = line1[i] + line2[i];
		}

		returnLine[size] = index;

		return returnLine;
	}
}
