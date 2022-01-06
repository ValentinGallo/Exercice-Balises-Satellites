package model;

import events.SatelliteMoved;

/**
 * Class Satellite qui hérite de ElementMobile
 * Il communique avec les balises
 */
public class Satellite extends ElementMobile {
			
	public Satellite(int memorySize) {
		super(memorySize);
	}
	
	public void bouge () {
		super.bouge();
		this.send(new SatelliteMoved(this));		
	}
}
