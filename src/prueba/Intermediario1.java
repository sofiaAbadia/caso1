package prueba;

public class Intermediario1 extends Thread
{
	private BuzonProductor bp;
	private BuzonIntermedio bi;
	
	public Intermediario1(BuzonProductor buffer, BuzonIntermedio bi) 
	{
		// TODO Auto-generated constructor stub
		this.bp = buffer;
		this.bi=bi;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (bp.obtenerClientesActuales() != 0)
		{
			procesarMensaje();
		}
	}
	
	private void procesarMensaje()
	{
		Producto m = bp.obtenerMensaje();
		
		if(m != null)
		{
			synchronized (m) {
				if(bi.almacenarMensaje(m))
				{
					try {
						System.out.println("INTER1 entregado");
						m.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
