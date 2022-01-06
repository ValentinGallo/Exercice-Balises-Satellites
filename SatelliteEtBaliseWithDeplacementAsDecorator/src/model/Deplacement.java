package model;

/**
 * Class générique d'un déplacement utilisé par les balises et satellites
 */
public abstract class Deplacement {
	abstract public void bouge(ElementMobile target) ;
	public Deplacement replacement() { return this; }
}
