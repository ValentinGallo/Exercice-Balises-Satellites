package model;

import events.SatelliteMoved;

/**
 * Class permettant de gérer les déplacements d'antenne qui hérite de la classe générique "Deplacement"
 */
public class DeplacementAntenne extends Deplacement {

    protected Deplacement next;

    public DeplacementAntenne() {}

    public void bouge(Antenne target) {
    }

    @Override
    public void bouge(ElementMobile target) {
        this.bouge((Antenne) target);
    }

    public void whenSatelitteMoved(SatelliteMoved arg, Antenne target) { }

}
