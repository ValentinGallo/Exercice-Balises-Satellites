package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

/**
 * Class Balise qui hérite de ElementMobile
 * Elle implémente l'interface SatelitteMoveListener
 */
public class Balise extends ElementMobile implements SatelitteMoveListener{

	//Boolean qui précise si la balise est en collecte ou non
	boolean isCollectingData = false;
	
	public Balise(int memorySize) {
		super(memorySize);
	}
	
	public int profondeur() { 
		return this.getPosition().y; 
	}
	
	protected void readSensors() {
			this.dataSize++;
	}

	/**
	 * Action réalisé pour chaque tick sur la balise
	 */
	public void tick() {
		if (!this.isCollectingData) this.readSensors();
		if (this.memoryFull()) {
			Deplacement redescendre = new Redescendre(this.deplacement(), this.profondeur());
			Deplacement deplSynchro = new DeplSynchronisation(redescendre);
			Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
			this.setDeplacement(nextDepl);
			this.resetData();
		}
		this.isCollectingData = false;
		super.tick();
	}

	@Override
	public void whenSatelitteMoved(SatelliteMoved arg) {
		DeplacementBalise dp = (DeplacementBalise) this.depl;
		dp.whenSatelitteMoved(arg, this);
		this.isCollectingData = true;
	}


}
