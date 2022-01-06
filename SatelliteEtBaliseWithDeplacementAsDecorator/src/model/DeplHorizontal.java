package model;

import java.awt.Point;

/**
 * Class permettant le déplacement horizontal des balises héritant de "DeplacementBalise"
 */
public class DeplHorizontal extends DeplacementBalise {
	Integer start;
	Integer end;
	Boolean fromStartToEnd = true;

	public DeplHorizontal(Integer start, Integer end) {
		super (null);
		this.start = start;
		this.end = end;
	}

	/**
	 * Fonction qui déplace la balise horizontalement
	 * @param target ElementMobile à déplacer horizontalement
	 */
	@Override
	public void bouge(ElementMobile target) {
		Point p = target.getPosition();
		int x = p.x;
		if (fromStartToEnd) {
			x += 6;
			if (x > end) fromStartToEnd = false;
		} else {
			x -= 2;
			if (x < start) fromStartToEnd = true;
		}
		target.setPosition(new Point(x, p.y));
	}

}
