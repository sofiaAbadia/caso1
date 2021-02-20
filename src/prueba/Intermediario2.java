package prueba;

public class Intermediario2 extends Thread
{

	private BuzonIntermedio bi;
	private BuzonConsumidor bc;
	
	public Intermediario2(BuzonIntermedio buffer, BuzonConsumidor bc) 
	{
		// TODO Auto-generated constructor stub
		this.bi = buffer;
		this.bc=bc;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (bi.obtenerClientesActuales() != 0)
		{
			procesarMensaje();
		}
	}
	
	private void procesarMensaje()
	{
		Producto m = bi.obtenerMensaje();
		
		if(m != null)
		{
			synchronized (m) {
				if(bc.almacenarMensaje(m))
				{
					try {
						System.out.println("INTER2 entregado");
						m.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("INTER2 no pudo ser entregado, reintentando");
					Thread.yield();
				}
			}
		}
		else
		{
			System.out.println("INTER2 como, reintentando");
			Thread.yield();
		}
	}
}
