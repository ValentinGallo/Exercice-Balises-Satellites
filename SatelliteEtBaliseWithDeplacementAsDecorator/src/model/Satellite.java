package model;

import events.SatelliteMoved;

/**
 * Class Satellite qui hérite de ElementMobile
 * Il communique avec les balises
 */
public class Satellite extends ElementMobile {

	/**
	 * Constructeur du satellite
	 * @param memorySize Quantité de données que peut recevoir le satellite
	 */
	public Satellite(int memorySize) {
		super(memorySize);
	}
	
	public void bouge () {
		super.bouge();
		this.send(new SatelliteMoved(this));		
	}

}
