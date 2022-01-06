package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

/**
 * Class Balise qui hérite de ElementMobile et implémente l'interface SatelitteMoveListener
 * Elle communique avec les satellites
 */
public class Balise extends ElementMobile implements SatelitteMoveListener{

	// Boolean qui précise si la balise est en collecte ou non
	boolean isCollectingData = true;
	
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
	 * Fonction appelée à chaque tick qui permet de réaliser les actions et déplacements de la balise
	 */
	public void tick() {
		this.readSensors();
		if (this.memoryFull() && isCollectingData) {
			Deplacement redescendre = new Redescendre(this.deplacement(), this.profondeur());
			Deplacement deplSynchro = new DeplSynchronisation(redescendre);
			Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
			this.setDeplacement(nextDepl);
			this.isCollectingData = false;
		}
		super.tick();
	}

	/**
	 * Fonction permettant d'appeler la fonction whenSatelitteMoved() du déplacement
	 * @param arg Satellite
	 */
	@Override
	public void whenSatelitteMoved(SatelliteMoved arg) {
		DeplacementBalise dp = (DeplacementBalise) this.depl;
		dp.whenSatelitteMoved(arg, this);
	}


}
