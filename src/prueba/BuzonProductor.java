package prueba;

import java.util.LinkedList;

public class BuzonProductor 
{
	private int capacidad;
	private LinkedList<Producto> producto;
	
	public BuzonProductor(int capacidad) 
	{
		// DONE Auto-generated constructor stub
		this.capacidad = capacidad;
		this.producto = new LinkedList<Producto>();
	}
	
	public synchronized boolean almacenarProducto(Producto m)
	{
		
		if(capacidad <= 0)
		{
			System.out.println("BP Capacidad Excedida, Buzón Productores lleno");
			return false;
		}
		else
		{
			System.out.println("BP Mensaje almacenado, espera por procesar");
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
		System.out.println("Productos actuales: " + producto.size());
		return producto.size();
	}
}
