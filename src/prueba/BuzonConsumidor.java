package prueba;

import java.util.LinkedList;

public class BuzonConsumidor 
{
	private int capacidad;
	private LinkedList<Producto> producto;
	
	public BuzonConsumidor(int capacidad) 
	{
		// DONE Auto-generated constructor stub
		this.capacidad = capacidad;
		this.producto = new LinkedList<Producto>();
	}
	
	public synchronized boolean almacenarProducto(Producto m)
	{
		
		if(capacidad <= 0)
		{
			System.out.println("BC Capacidad Excedida, buffer lleno");
			return false;
		}
		else
		{
			System.out.println("BC Mensaje almacenado, espera por procesar");
			this.producto.add(m);
			capacidad--;
			return true;
		}
	}
	

	
	public synchronized Producto obtenerProducto()
	{
		capacidad++;
		return this.producto.poll();
		
	}
	
	public synchronized int obtenerProductosActuales()
	{
		System.out.println("PRODUCTOS EN EL BUZON CONSUMIDOR: " + producto.size());
		return producto.size();
	}
}
