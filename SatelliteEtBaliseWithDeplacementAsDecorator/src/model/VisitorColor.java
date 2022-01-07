package model;

import views.GrAntenne;
import views.GrBalise;
import views.GrElementMobile;
import views.GrSatellite;

import java.awt.*;

/**
 * Visiteur qui retourne la couleur associé à l'élément graphique
 */
public class VisitorColor {
    public Color visit(GrAntenne grAntenne) {
        return Color.RED;
    }

    public Color visit(GrSatellite grSatellite) {
        return Color.BLUE;
    }

    public Color visit(GrBalise grBalise) {
        return Color.ORANGE;
    }
}
