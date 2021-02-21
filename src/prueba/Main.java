package prueba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
public static String data = "./data/config.txt";
	
	public static void main(String[] args) 
	{
		// DONE Auto-generated method stub
		File file = new File(data);
		Integer[] attrib = new Integer[4];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			 String st;
			 int c = 0;
			  while ((st = br.readLine()) != null) 
			  {
				  int i = Integer.parseInt(st.split("=")[1]);
				  attrib[c] = i;
				  c++;
			  }
			  		   
		} catch (FileNotFoundException e) {
			// DONE Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// DONE Auto-generated catch block
			e.printStackTrace();
		}
		
		int productores = attrib[0];
		int consumidores = attrib[0];
		int productos = attrib[1];
		int buzonProd = attrib[2];
		int buzonCons = attrib[3];
		
		Productor[] servs = new Productor[productores];
		Consumidor[] clients = new Consumidor[consumidores];

		BuzonProductor bp = new BuzonProductor(buzonProd);
		BuzonConsumidor bc = new BuzonConsumidor(buzonCons);
		BuzonIntermedio bi= new BuzonIntermedio();
		Intermediario1 I1= new Intermediario1(bp, bi);
		Intermediario2 I2= new Intermediario2(bi, bc);
		for (int i = 0; i < productores/2; i++) 
		{
			servs[i] = new Productor(productos,bp,'A');
		}
		for (int i = productores/2; i < productores; i++) 
		{
			servs[i] = new Productor(productos,bp,'B');
		}
		
		for (int i = 0; i < consumidores/2; i++) 
		{
			clients[i] = new Consumidor(bc,'A');
		}
		for (int i = consumidores/2; i < consumidores; i++) 
		{
			clients[i] = new Consumidor(bc,'B');
		}
		
//		Intermediario1[] int1= new Intermediario1[productores];
//		Intermediario2[] int2= new Intermediario2[productores];
		
//		for (int i = 0; i < productores; i++) 
//		{
//			int1[i] = new Intermediario1(bp,bi);
//		}
		
//		for (int i = 0; i < productores; i++) 
//		{
//			int2[i] = new Intermediario2(bi,bc);
//		}
		
		for (int i = 0; i < productores; i++) 
		{
			servs[i].start();
		}
		
		I1.start();
		I2.start();
		
//		for (int i = 0; i < productores; i++) 
//		{
//			int1[i].start();
//		}
		
//		for (int i = 0; i < productores; i++) 
//		{
//			int2[i].start();
//		}
		
		for (int i = 0; i < consumidores; i++) 
		{
			clients[i].start();
		}
	
		
	}

}
