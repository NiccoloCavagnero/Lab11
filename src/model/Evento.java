package model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	public enum Tipo {
		IN,
		OUT
	}
	
	private LocalTime ora;
	private GruppoCliente g;
	private Tipo tipo;

	public Evento(LocalTime ora, Tipo tipo, GruppoCliente g) {
		super();
		this.ora = ora;
		this.tipo = tipo;
		this.g = g;
	}
	
	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public GruppoCliente getG() {
		return g;
	}

	public void setG(GruppoCliente g) {
		this.g = g;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Evento e) {
		return this.ora.compareTo(e.ora);
	}

	@Override
	public String toString() {
		return "Evento [ora=" + ora + ", tipo=" + tipo + "]";
	}
	
	

}
