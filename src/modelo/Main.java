package modelo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cache c = new Cache(4,4,16,1,2,3);
		c.ler(2);
		c.ler(3);
		c.ler(1);
		c.ler(15);
		c.ler(21);
		c.ler(13);
		c.ler(9);
		c.ler(20);
		c.ler(7);
		c.ler(17);
		c.ler(1);
		c.ler(2);
		c.ler(22);
		c.ler(14);
		c.ler(6);
		c.ler(1);	
		
		c.show();
	}

}
