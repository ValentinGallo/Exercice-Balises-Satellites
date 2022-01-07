package model;

import views.GrAntenne;
import views.GrBalise;
import views.GrElementMobile;
import views.GrSatellite;

import java.awt.*;

public class VisitorColor {

    public Color visit(GrAntenne grAntenne) {
        return Color.RED;
    }

    public Color visit(GrSatellite grSatellite) {
        return Color.ORANGE;
    }

    public Color visit(GrBalise grBalise) {
        return Color.ORANGE;
    }
}
