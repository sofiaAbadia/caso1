package prueba;


public class Productor extends Thread
{
	private int numProd;
	
	private BuzonProductor bp;
	
	private char tipo;
	
	public Productor(int numProd, BuzonProductor bp, char tipo) 
	{
		this.numProd = numProd;
		this.bp = bp;
		this.tipo=tipo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.crearConsultas();
	}
	
	private void crearConsultas()
	{
		for(int i = 0; i < numProd; i++)
		{
			Producto m = new Producto(tipo);
			synchronized (m) 
			{
				if(bp.almacenarMensaje(m))
				{
					System.out.println("Productor entregado");
					m.notify();
					bp.anunciarRetiro();
				}
				else
				{
					System.out.println("Productor no pudo ser entregado, reintentando");
					Thread.yield();
				}
			}
			
		}
		System.out.println("Todos los Productor entregados, finalizando");
	}
}
