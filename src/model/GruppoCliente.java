package model;

import java.util.Random;

public class GruppoCliente {
	
	public enum statoGruppo {
		ARRIVO,
		ATTESA,
		SEDUTI,
		BANCONE,
		USCITA
	}
	
	private int id;
	private int numero;
	private double tolleranza;
	private statoGruppo stato;
	private Random random;
	private Tavolo tavolo;
	
	public GruppoCliente(int id) {
		super();
		random = new Random();
		this.id = id;
		this.numero = random.nextInt(10);
		this.tolleranza = random.nextDouble()*0.9;
		this.stato = statoGruppo.ARRIVO;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	public statoGruppo getStato() {
		return stato;
	}

	public void setStato(statoGruppo stato) {
		this.stato = stato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GruppoCliente other = (GruppoCliente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
