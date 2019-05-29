package model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import model.Evento.Tipo;
import model.GruppoCliente.statoGruppo;

public class Simulator {
	
	private PriorityQueue<Evento> queue;
	Random rand;
	
	// STATO DEL SISTEMA
	private LocalTime now; 
	
	// PARAMETRI
	private int nGruppi = 2000;
	private Map<Integer,Tavolo> tavoliMap;
	
	// STATISTICHE
	private int nClienti;
	private int nClientiNo;
	
	// VARIABILI INTERNE
	
	public Simulator() {
		tavoliMap = new HashMap<>();
		tavoliMap.put(1,new Tavolo(1,10));
		tavoliMap.put(2,new Tavolo(2,10));
		tavoliMap.put(3,new Tavolo(3,8));
		tavoliMap.put(4,new Tavolo(4,8));
		tavoliMap.put(5,new Tavolo(5,8));
		tavoliMap.put(6,new Tavolo(6,8));
		tavoliMap.put(7,new Tavolo(7,6));
		tavoliMap.put(8,new Tavolo(8,6));
		tavoliMap.put(9,new Tavolo(9,6));
		tavoliMap.put(10,new Tavolo(10,6));
		tavoliMap.put(11,new Tavolo(11,4));
		tavoliMap.put(12,new Tavolo(12,4));
		tavoliMap.put(13,new Tavolo(13,4));
		tavoliMap.put(14,new Tavolo(14,4));
		tavoliMap.put(15,new Tavolo(15,4));
	}
	
	public void init() {
		queue = new PriorityQueue<>();
		rand = new Random();
		now = LocalTime.of(12, 0);
		
		now = now.plusMinutes((long)(rand.nextDouble()*10));
		
		queue.add(new Evento(now, Tipo.IN, new GruppoCliente(0)));
		
		for ( int i=1; i<nGruppi; i++) {
			now = now.plusMinutes((long)(rand.nextDouble()*10));
			queue.add(new Evento(now, Tipo.IN, new GruppoCliente(i)));
			//System.out.println(now);
		}
	}
	
	public void run() {
		nClienti = 0;
		nClientiNo = 0;
		Evento e;
		
		while((e=queue.poll())!=null) {
			System.out.println(e.getOra()+" "+e.getTipo());
			switch(e.getTipo()) {
			
			case IN:
				
				boolean flag = false;
				for ( int i=tavoliMap.size(); i>0; i-- ) {
					if ( !tavoliMap.get(i).isOccupato() ) {
						if ( tavoliMap.get(i).getPosti() >= e.getG().getNumero() && tavoliMap.get(i).getPosti() <= e.getG().getNumero()*2 ) {
							nClienti += e.getG().getNumero();
							tavoliMap.get(i).setOccupato(true);
							e.getG().setStato(statoGruppo.SEDUTI);
							e.getG().setTavolo(tavoliMap.get(i));
							queue.add(new Evento(e.getOra().plusMinutes((long)(rand.nextDouble()*60+60)), Tipo.OUT, e.getG()));
						    System.out.println(e.getG().getStato());
						    flag = true;
						    break;
						}
					}
				}
				if ( flag == false ) {
				double prob = rand.nextDouble();
			    
				if ( e.getG().getTolleranza() >= prob ) {
					nClienti+=e.getG().getNumero();
					e.getG().setStato(statoGruppo.BANCONE);
					System.out.println(e.getG().getStato());
				}
				else {
					nClientiNo+=e.getG().getNumero();
					e.getG().setStato(statoGruppo.USCITA);
					System.out.println(e.getG().getStato());
				}
				}
				break;
				
			case OUT:
				
				e.getG().getTavolo().setOccupato(false);
				e.getG().setStato(statoGruppo.USCITA);
				System.out.println(e.getG().getStato());
				break;
			}
		}
	}
	
	public void stat() {
		System.out.println("Clienti totali: "+(nClienti+nClientiNo));
		System.out.println("Clienti serviti: "+nClienti);
		System.out.println("Clienti non serviti: "+nClientiNo);
	}
}
