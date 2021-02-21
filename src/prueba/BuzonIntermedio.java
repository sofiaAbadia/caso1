package prueba;

public class BuzonIntermedio 
{
	private int capacidad=1;
	
	private Producto producto;
	
	public BuzonIntermedio() 
	{
		// TODO Auto-generated constructor stub
		
	}
	
	public synchronized boolean almacenarProducto(Producto m)
	{
		
		
		if(capacidad <= 0)
		{
			System.out.println("Buzon Intermedio Capacidad Excedida, buzón  lleno");
			return false;
		}
		else
		{
			System.out.println("Buzon Intermedio Mensaje almacenado, espera por procesar");
			this.producto=m;
			capacidad--;
			return true;
		}
	}
	
	public synchronized Producto obtenerProducto()
	{
		capacidad++;
		return this.producto;
	}
	

	
	public synchronized int obtenerProductosActuales()
	{
		System.out.println("BI en linea: " + capacidad);
		return capacidad;
	}
}
