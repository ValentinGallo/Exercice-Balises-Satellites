package model;

import java.util.ArrayList;

import events.SatelliteMoved;
import nicellipse.component.NiLabel;

/**
 * Class permettant de gérer les satellites et balises de la simulation
 */
public class Manager {
	ArrayList<Satellite> sats = new ArrayList<Satellite>();
	ArrayList<Balise> bals = new ArrayList<Balise>();
	ArrayList<Antenne> ants = new ArrayList<Antenne>();
	
	public void addBalise(Balise bal) {
		bals.add(bal);
		bal.setManager(this);
	}
	public void addSatellite(Satellite sat) {
		this.sats.add(sat);
		sat.setManager(this);
	}
	public void addAntenne(Antenne ant) {
		this.ants.add(ant);
		this.antenneReadyForSynchro(ant);
		ant.setManager(this);
	}
	public void tick() {
		for (Balise b : this.bals) {
			b.tick();
		}
		for (Satellite s : this.sats) {
			s.tick();
		}
		for (Antenne a : this.ants) {
			a.tick();
		}
	}
	
	public void baliseReadyForSynchro(Balise b) {
		for (Satellite s : this.sats) {
			s.registerListener(SatelliteMoved.class, b);
		}
	}

	public void baliseSynchroDone(Balise b) {
		for (Satellite s : this.sats) {			
			s.unregisterListener(SatelliteMoved.class, b);
		}
	}

	public void antenneReadyForSynchro(Antenne a) {
		for (Satellite s : this.sats) {
			s.registerListener(SatelliteMoved.class, a);
		}
	}

	public int getTotalDataCollected(){
		int total = 0;
		for (Satellite s : this.sats) {
			total += s.dataSize;
		}
		return total;
	}

}
