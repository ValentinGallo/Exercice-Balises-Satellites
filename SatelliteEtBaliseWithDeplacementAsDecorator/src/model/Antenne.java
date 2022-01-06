package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

/**
 * Class Antenne qui hérite de ElementMobile
 * Il communique avec les satellites
 */
public class Antenne extends ElementMobile implements SatelitteMoveListener {

    /**
     * Constructeur de l'antenne
     * @param memorySize Quantité de données que peut recevoir l'antenne
     */
    public Antenne(int memorySize) {
        super(memorySize);
    }

    /**
     * Fonction appelée à chaque tick qui permet de réaliser les actions et déplacements de la balise
     * Elle permet aussi de changer l'indicateur de progression de collecte de données
     */
    public void tick() {
        Deplacement mouv = new DeplacementAntenne(new DeplHorizontal(-2, 2));
        Deplacement deplSynchro = new DeplSyncAntenne(mouv);
        this.setDeplacement(deplSynchro);
        super.tick();
    }

    /**
     * Fonction permettant d'appeler la fonction whenSatelitteMoved() du déplacement
     * @param arg SatelliteMoved
     */
    @Override
    public void whenSatelitteMoved(SatelliteMoved arg) {
        DeplacementAntenne dp = (DeplacementAntenne) this.depl;
        dp.whenSatelitteMoved(arg, this);
    }


}
