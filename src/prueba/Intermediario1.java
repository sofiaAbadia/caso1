package prueba;

public class Intermediario1 extends Thread
{
	private BuzonProductor bp;
	private BuzonIntermedio bi;
	
	public Intermediario1(BuzonProductor bp, BuzonIntermedio bi) 
	{
		// DONE Auto-generated constructor stub
		this.bp = bp;
		this.bi=bi;
	}
	
	@Override
	public void run() {
		// DONE Auto-generated method stub
		if (bp.obtenerProductosActuales() != 8)
		{
			procesarProducto();
		}
		
	}
	
	private void procesarProducto()
	{
		Producto m = bp.obtenerProducto();
		
		if(m != null)
		{
			synchronized (m) {
				if(bi.almacenarProducto(m))
				{
					try {
						System.out.println("INTERMEDIARIO1 entregado");
						m.wait();
					} catch (InterruptedException e) {
						// DONE Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("INTER1 no pudo ser entregado, reintentando");
					Thread.yield();
				}
			}
		}
		else
		{
			System.out.println("Inter1 hola, reintentando");
			Thread.yield();
		}
	}
}
