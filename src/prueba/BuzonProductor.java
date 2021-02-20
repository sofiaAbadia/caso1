package prueba;

import java.util.LinkedList;

public class BuzonProductor 
{
	private int capacidad;
	
	private int productores;

	private LinkedList<Producto> producto;
	
	public BuzonProductor(int capacidad, int clientes) 
	{
		// TODO Auto-generated constructor stub
		this.capacidad = capacidad;
		this.producto = new LinkedList<Producto>();
		this.productores = clientes;
	}
	
	public synchronized boolean almacenarMensaje(Producto m)
	{
		capacidad--;
		if(capacidad < 0)
		{
			System.out.println("BP Capacidad Excedida, buffer lleno");
			return false;
		}
		else
		{
			System.out.println("BP Mensaje almacenado, espera por procesar");
			this.producto.add(m);
			return true;
		}
	}
	
	public synchronized Producto obtenerMensaje()
	{
		return this.producto.poll();
	}
	
	public synchronized void anunciarRetiro()
	{
		productores--;
	}
	
	public synchronized int obtenerClientesActuales()
	{
		System.out.println("Productores en linea: " + this.productores);
		return this.productores;
	}
}
