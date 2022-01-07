package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

/**
 * Class Antenne qui hérite de ElementMobile
 * Elle communique avec les satellites
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
     * Fonction appelée à chaque tick qui permet de lancer la synchronisation des antennes
     */
    public void tick() {
        this.getManager().antenneReadyForSynchro(this);
        super.tick();
    }

    /**
     * Fonction permettant d'appeler la fonction whenSatelitteMoved() du déplacement
     * @param arg SatelliteMoved
     */
    @Override
    public void whenSatelitteMoved(SatelliteMoved arg) {
        DeplSyncAntenne dp = (DeplSyncAntenne) this.depl;
        dp.whenSatelitteMoved(arg, this);
    }


}
