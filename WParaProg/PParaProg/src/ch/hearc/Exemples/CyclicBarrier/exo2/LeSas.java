package ch.hearc.Exemples.CyclicBarrier.exo2;

public class  LeSas{


public static void main(String []args) {

	 int cstMaxpassagers =6;
	 int cstCapaciteSas =3;

	SasCB leSas = new SasCB(cstCapaciteSas);
	for(int i =0; i< cstMaxpassagers; i++){
		new Thread(new Passager(i+1, leSas)).start();
	}
}
}




