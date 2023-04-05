package ch.hearc.Corrigés.Serie7.Exercice1;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Integer> {

	private final int duree;

	public MyTask(int duree) {
		this.duree = duree;
	}

	@Override
	public Integer call() {
		System.out.println("Debut tache " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000 * duree);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin tache " + Thread.currentThread().getName());
		return duree;
	}

}