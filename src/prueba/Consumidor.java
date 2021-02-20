package prueba;

public class Consumidor extends Thread
{
	private BuzonConsumidor bc;
	private char tipo;
	
	public Consumidor(BuzonConsumidor buffer, char tipo) 
	{
		// TODO Auto-generated constructor stub
		this.bc = buffer;
		this.tipo=tipo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (bc.obtenerClientesActuales() != 0 && bc.getLista()!=0)
		{
			procesarMensaje();
		}
	}
	
	private void procesarMensaje()
	{
		Producto m = bc.obtenerMensaje();
		
		if(m != null)
		{
			if(m.getMensaje()==tipo)
			{
				synchronized (m) {
					System.out.println("CONSUM Mensaje procesado, notificando ");
					m.notify();
					}
			}
		}
		else
		{
			System.out.println("CONSUM estas, reintentando");
			Thread.yield();
		}
		System.out.println("Todos los consumidor entregados, finalizando");
		bc.anunciarRetiro();
	}
	
}
