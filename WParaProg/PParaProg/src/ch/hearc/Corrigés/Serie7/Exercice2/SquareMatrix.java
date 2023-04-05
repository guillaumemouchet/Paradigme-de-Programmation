package ch.hearc.Corrigés.Serie7.Exercice2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SquareMatrix {

	private Integer[][] lines;
	private int size;

	public SquareMatrix(int size) {
		lines = new Integer[size][size];
		this.size = size;

		Random rand = new Random();

		for (Integer[] line : lines) {
			for (int i = 0; i < size; i++) {
				line[i] = rand.nextInt(10);
			}
		}
	}

	public void setLine(Integer[] line, int index) {
		lines[index] = line;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Integer[] line : lines) {
			builder.append("| ");
			for (Integer number : line) {
				builder.append(number + " | ");
			}
			builder.append("\n");
		}

		return builder.toString();
	}




	public SquareMatrix add(SquareMatrix matrix, ExecutorService executorService) {
		SquareMatrix resultMatrix = new SquareMatrix(size);
		// start calculations

		List<Callable<Integer[]>> tasks = new ArrayList<Callable<Integer[]>>();
		for (int i = 0; i < size; i++) {
			tasks.add(new MatrixLineAdder(this.lines[i], matrix.lines[i])); //i represente la ligne Ã  calculer
		}

		List<Future<Integer[]>> futurlines = null;
		try {
			System.out.println("Invoking all task");
			futurlines = executorService.invokeAll(tasks);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		int i = 0;
		// put results in matrix
		for (Future<Integer[]> futurLine : futurlines) {
			try {
				System.out.println("Receiving line " + i);
				resultMatrix.setLine(futurLine.get(), i++);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// return matrix
		return resultMatrix;
	}

}
