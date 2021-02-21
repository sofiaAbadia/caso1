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
		// Done Auto-generated method stub
		this.crearProducto();
	}
	
	private void crearProducto()
	{
		for(int i = 0; i < numProd; i++)
		{
			Producto m = new Producto(tipo);
			synchronized (m) 
			{
				if(bp.almacenarProducto(m))
				{
					System.out.println("Producto entregado");
					m.notify();
					numProd--;
				}
				else
				{
					System.out.println("Producto no pudo ser entregado, reintentando");
					Thread.yield();
				}
			}
			
		}
		System.out.println("Todos los Productos han sido entregados, finalizando");
	}
}
