package prueba;

public class Consumidor extends Thread
{
	private BuzonConsumidor bc;
	private char tipo;
	
	public Consumidor(BuzonConsumidor buzonConsumidor, char tipo) 
	{
		// DONE Auto-generated constructor stub
		this.bc = buzonConsumidor;
		this.tipo=tipo;
	}
	
	@Override
	public void run() {
		// DONE Auto-generated method stub
		while (bc.obtenerProductosActuales() != 0)
		{
			recibirProducto();
		}
	}
	
	private void recibirProducto()
	{
		Producto m = bc.obtenerProducto();
		
		if(m != null)
		{
			if(m.getTipo()==tipo)
			{
				synchronized (m) {
					System.out.println("CONSUM Mensaje procesado, notificando ");
					m.notify();
					}
			}
			else
			{
				System.out.println("CONSUM estas, reintentando");
				Thread.yield();
			}
		}
		else
		{
			System.out.println("CONSUM estas, reintentando");
			Thread.yield();
		}
		System.out.println("Todos los consumidor entregados, finalizando");
	}
	
}
