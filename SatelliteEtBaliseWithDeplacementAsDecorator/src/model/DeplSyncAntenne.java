package model;

import events.SatelliteMoved;
import events.SynchroEvent;
import model.Balise;
import model.Deplacement;
import model.ElementMobile;

/**
 * Class permettant de gérer les déplacements de balises qui hérite de la classe générique "Deplacement"
 */
public class DeplSyncAntenne extends DeplacementAntenne implements DeplSynchronisation {
    private int synchroTime;
    private Satellite synchro;

    public Boolean synchroStarted() {
        return this.synchro != null;
    }

    public DeplSyncAntenne(Deplacement next) {
        super(next);
        this.synchroTime = 10;
        this.synchro = null;
    }

    @Override
    public void whenSatelitteMoved(SatelliteMoved arg, Antenne target) {
        if (this.synchro != null) return;
        Satellite sat = (Satellite) arg.getSource();
        int satX = sat.getPosition().x;
        int tarX = target.getPosition().x;
        if (satX > tarX - 10 && satX < tarX + 10 && sat.dataSize > 0 && !target.memoryFull()) {
            this.synchro = sat;
            target.send(new SynchroEvent(this));
            this.synchro.send(new SynchroEvent(this));
            sat.sendData(target);
        }
    }


    @Override
    public void bouge(ElementMobile target) {
        if (this.synchro == null) return;
        this.synchroTime--;
        if (synchroTime <= 0) {
            Satellite sat = this.synchro;
            this.synchro = null;
            this.synchroTime = 10;
            target.send(new SynchroEvent(this));
            sat.send(new SynchroEvent(this));
            target.getManager().antenneSynchroDone(((Antenne) target));
            target.setDeplacement(this);
        }
    }
}
