package prueba;

import java.util.LinkedList;

public class BuzonConsumidor 
{
	private int capacidad;
	
	private int consumidor;
	
	private int cliente=1;

	private LinkedList<Producto> producto;
	
	public BuzonConsumidor(int capacidad, int clientes) 
	{
		// TODO Auto-generated constructor stub
		this.capacidad = capacidad;
		this.producto = new LinkedList<Producto>();
		this.consumidor = clientes;
	}
	
	public synchronized boolean almacenarMensaje(Producto m)
	{
		capacidad--;
		if(capacidad < 0)
		{
			System.out.println("BC Capacidad Excedida, buffer lleno");
			return false;
		}
		else
		{
			System.out.println("BC Mensaje almacenado, espera por procesar");
			this.producto.add(m);
			return true;
		}
	}
	
	public int getLista()
	{
		return this.producto.size();
	}
	
	public synchronized Producto obtenerMensaje()
	{
		return this.producto.poll();
	}
	
	public synchronized void anunciarRetiro()
	{
		consumidor--;
	}
	
	public synchronized int obtenerClientesActuales()
	{
		System.out.println("Consumidores en linea: " + this.consumidor);
		return this.consumidor;
	}
}
