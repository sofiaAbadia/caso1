package prueba;

import java.util.LinkedList;

public class BuzonIntermedio 
{
	private int capacidad=1;
	
	private int clientes=1;

	private Producto mensaje;
	
	public BuzonIntermedio() 
	{
		// TODO Auto-generated constructor stub
		
	}
	
	public synchronized boolean almacenarMensaje(Producto m)
	{
		
		capacidad--;
		if(capacidad < 0)
		{
			System.out.println("BI Capacidad Excedida, buffer lleno");
			return false;
		}
		else
		{
			System.out.println("BI Mensaje almacenado, espera por procesar");
			this.mensaje=m;
			return true;
		}
	}
	
	public synchronized Producto obtenerMensaje()
	{
		return this.mensaje;
	}
	
	public synchronized void anunciarRetiro()
	{
		clientes--;
	}
	
	public synchronized int obtenerClientesActuales()
	{
		System.out.println("BI en linea: " + this.clientes);
		return this.clientes;
	}
}
