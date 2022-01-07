package model;

import views.GrElementMobile;

import java.awt.*;

public interface Visitable {
    public Color accept(VisitorColor v);
}
