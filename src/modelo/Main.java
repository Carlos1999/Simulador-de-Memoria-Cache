package modelo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cache c = new Cache(4,8,16,1,2,3);
		c.escrever(2,41);
		c.escrever(3,41);
		c.escrever(1,41);
		c.escrever(15,41);
		c.escrever(21,41);
		c.escrever(13,41);
		c.escrever(9,41);
		c.escrever(20,41);
		c.escrever(7,41);
		c.escrever(17,41);
		c.escrever(1,41);
		c.escrever(2,41);
		c.escrever(22,41);
		c.escrever(14,41);
		c.escrever(6,41);
		c.escrever(1,41);
		c.ler(25);
		c.ler(28);
		
		c.show();
	}

}
