package model;

/**
 * Class générique d'un déplacement utilisé par les balises et satellites
 */
public abstract class Deplacement {
	/**
	 *	Fonction de déplacement
	 * @param target Element à déplacer
	 */
	abstract public void bouge(ElementMobile target) ;

	public Deplacement replacement() { return this; }
}
