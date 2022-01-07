package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

/**
 * Class Balise qui hérite de ElementMobile et implémente l'interface SatelitteMoveListener
 * Elle communique avec les satellites
 */
public class Balise extends ElementMobile implements SatelitteMoveListener{

	/**
	 * Boolean qui précise si la balise est en collecte ou non
	 */
	boolean isCollectingData = true;

	/**
	 * Constructeur de la Balise
	 * @param memorySize Quantité de données que peut recevoir la balise
	 */
	public Balise(int memorySize) {
		super(memorySize);
	}

	/**
	 * @return renvoie la profondeur
	 */
	public int profondeur() { 
		return this.getPosition().y; 
	}

	/**
	 * Permet de lire les capteurs afin de collecter de la donnée
	 */
	protected void readSensors() {
		this.dataSize++;
	}

	/**
	 * Fonction appelée à chaque tick qui permet de réaliser les actions et déplacements de la balise
	 * Elle permet aussi de changer l'indicateur de progression de collecte de données
	 */
	public void tick() {
		if (isCollectingData) {
			this.readSensors();
			if(this.memoryFull()) {
				Deplacement redescendre = new Redescendre(this.deplacement(), this.profondeur());
				Deplacement deplSynchro = new DeplSyncBalise(redescendre);
				Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
				this.setDeplacement(nextDepl);
				this.isCollectingData = false;
			}
		}
		super.tick();
	}

	/**
	 * Fonction permettant d'appeler la fonction whenSatelitteMoved() du déplacement
	 * @param arg SatelliteMoved
	 */
	@Override
	public void whenSatelitteMoved(SatelliteMoved arg) {
		DeplacementBalise dp = (DeplacementBalise) this.depl;
		dp.whenSatelitteMoved(arg, this);
	}

}
