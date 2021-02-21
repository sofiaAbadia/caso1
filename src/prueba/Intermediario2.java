package prueba;

public class Intermediario2 extends Thread
{

	private BuzonIntermedio bi;
	private BuzonConsumidor bc;
	
	public Intermediario2(BuzonIntermedio pBi, BuzonConsumidor bc) 
	{
		//DONE Auto-generated constructor stub
		this.bi = pBi;
		this.bc=bc;
	}
	
	@Override
	public void run() {
		//DONE Auto-generated method stub
		if (bi.obtenerProductosActuales() != 1)
		{
			procesarMensaje();
		}
	}
	
	private void procesarMensaje()
	{
		Producto m = bi.obtenerProducto();
		
		if(m != null)
		{
			synchronized (m) {
				if(bc.almacenarProducto(m))
				{
					try {
						System.out.println("INTER2 entregado");
						m.wait();
					} catch (InterruptedException e) {
						// DONE Auto-generated catch block
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
