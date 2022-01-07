package model;

import java.awt.*;

/**
 * Interface de classe Visitable
 */
public interface Visitable {
    public Color accept(VisitorColor v);
}
