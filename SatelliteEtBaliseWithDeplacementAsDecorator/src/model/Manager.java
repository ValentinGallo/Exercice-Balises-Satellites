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

	public void refreshCounterDataCollected(NiLabel labelBalises, NiLabel labelSatellites, NiLabel labelAntennes){
		//Balies
		String text = labelBalises.getText().split(":")[0];
		String newText = text+":"+this.getTotalDataCollected(this.bals);
		if(!newText.equals(labelBalises.getText()))labelBalises.setText(newText);

		text = labelSatellites.getText().split(":")[0];
		newText = text+":"+this.getTotalDataCollected(this.sats);
		if(!newText.equals(labelSatellites.getText()))labelSatellites.setText(newText);

		text = labelAntennes.getText().split(":")[0];
		newText = text+":"+this.getTotalDataCollected(this.ants);
		if(!newText.equals(labelAntennes.getText()))labelAntennes.setText(newText);
	}

	public int getTotalDataCollected(ArrayList<? extends ElementMobile>  list){
		int total = 0;
		for (ElementMobile e : list) {
			total += e.dataSize;
		}
		return total;
	}

}
