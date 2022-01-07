package simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;
import nicellipse.component.NiLabel;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;
import views.*;

public class Simulation {
	//Variables de l'environnement
	final int FPS_MIN = 2;
	final int FPS_MAX = 500;
	final int FPS_INIT = 10;
	final int startDelay = 500 / FPS_INIT;
	Timer animation;
	NiLabel labelCounterSatellite,labelCounterBalises,labelCounterAntenne;
	Manager manager = new Manager();
	Dimension worldDim = new Dimension(900, 800);
	NiSpace world = new NiSpace("Satellite & Balises", this.worldDim);
	GrEther ether = new GrEther();


	public void animation() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				manager.tick();
				manager.refreshCounterDataCollected(labelCounterBalises,labelCounterSatellite,labelCounterAntenne);
				ether.repaint();

			}
		};
		this.animation = new Timer(this.startDelay, taskPerformer);
		this.animation.setRepeats(true);
		this.animation.start();
	}
	
	private JPanel fpsSliderPanel() {		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(" FPS :", JLabel.RIGHT);
		JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);

		framesPerSecond.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int fps = (int) source.getValue();
					int newDelay = 1000 / fps;
					animation.setDelay(newDelay);
					animation.setInitialDelay(newDelay * 10);
				}
			}
		});

		// Turn on labels at major tick marks.
		framesPerSecond.setMajorTickSpacing(50);
		framesPerSecond.setMinorTickSpacing(10);
		framesPerSecond.setPaintTicks(true);
		framesPerSecond.setPaintLabels(false);
		
		panel.add(label);
		panel.add(framesPerSecond);
		return panel;
	}


	public void addBalise(JPanel sea, int memorySize, Point startPos, Deplacement depl) {
		Balise bal = new Balise(memorySize);
		bal.setPosition(startPos);
		bal.setDeplacement(depl);
		manager.addBalise(bal);
		GrBalise grbal = new GrBalise(this.ether);
		grbal.setModel(bal);
		sea.add(grbal);
		this.initializeProgressIndicator(bal, grbal);
	}

	public void addSatelitte(JPanel sky, int memorySize, Point startPos, int vitesse) {
		Satellite sat = new Satellite(memorySize);
		sat.setPosition(startPos);
		sat.setDeplacement(new DeplSatellite(-10, 1000, vitesse));
		manager.addSatellite(sat);
		GrSatellite grSat = new GrSatellite(this.ether);
		grSat.setModel(sat);
		sky.add(grSat);
		this.initializeProgressIndicator(sat, grSat);
	}

	public void addAntenne(JPanel sky, int memorySize, Point startPos, int vitesse) {
		Antenne ant = new Antenne(memorySize);
		ant.setPosition(startPos);
		ant.setDeplacement(new DeplSyncAntenne(new DeplHorizontal(-2, 2)));
		manager.addAntenne(ant);
		GrAntenne grAnt = new GrAntenne(this.ether);
		grAnt.setModel(ant);
		sky.add(grAnt);
		this.initializeProgressIndicator(ant, grAnt);
	}

	public void initializeProgressIndicator(ElementMobile balise, GrElementMobile grbal) {
		GrProgressIndicator initialProgress = new GrProgressIndicator(grbal);
		balise.progressIndicator = initialProgress;
	}

	public void launch() {
		JLayeredPane main = new JLayeredPane();
		main.setOpaque(true);
		main.setSize(this.worldDim);

		this.ether.setBorder(null);
		this.ether.setBackground(Color.gray);
		this.ether.setOpaque(false);
		this.ether.setDimension(this.worldDim);

		NiRectangle sky = new NiRectangle();
		sky.setBackground(Color.white);
		sky.setDimension(new Dimension(this.worldDim.width, (this.worldDim.height-100) / 2));
		NiRectangle sea = new NiRectangle();
		sea.setBackground(Color.blue);
		sea.setDimension(new Dimension(this.worldDim.width, (this.worldDim.height-100) / 2));
		sea.setLocation(new Point(0, 700 / 2));

		this.addScoreBoard(main);

		this.addSatelitte(sky, 200, new Point(10, 50), 2);
		this.addSatelitte(sky, 200, new Point(100, 10), 1);
		this.addSatelitte(sky, 200, new Point(400, 90), 3);
		this.addSatelitte(sky, 200, new Point(500, 140), 4);
		this.addSatelitte(sky, 200, new Point(600, 10), 1);
		this.addBalise(sea, 150, new Point(400, 200), new DeplHorizontal(50, 750));
		this.addBalise(sea, 100, new Point(100, 100), new DeplVertical(50, 200));
		this.addBalise(sea, 400, new Point(0, 160), new DeplHorizontal(0, 800));
		this.addBalise(sea, 70, new Point(200, 100), new DeplVertical(130, 270));
		this.addBalise(sea, 160, new Point(300, 100), new DeplHorizontal(200, 600));
		this.addAntenne(sky, 700, new Point(750, 260), 1);
		this.addAntenne(sky, 700, new Point(40, 260), 1);

		main.add(sky, JLayeredPane.DEFAULT_LAYER);
		main.add(sea, JLayeredPane.DEFAULT_LAYER);
		main.add(this.ether, JLayeredPane.POPUP_LAYER);
		
		this.world.setLayout(new BoxLayout(this.world, BoxLayout.Y_AXIS));
		this.world.add(main);
		this.world.add(this.fpsSliderPanel());
		this.world.openInWindow();
		this.animation();
	}

	public void addScoreBoard(JLayeredPane main) {
		//ScoreBoard
		NiRectangle score = new NiRectangle();
		score.setBackground(Color.orange);
		score.setDimension(new Dimension(this.worldDim.width, 100));
		score.setLocation(new Point(0, (this.worldDim.height-100)));

		//labelCounterBalises
		labelCounterBalises = new NiLabel("[Balises] Total données:0");
		labelCounterBalises.setForeground(Color.black);
		labelCounterBalises.setFont(new Font("Arial", Font.BOLD, 20));
		score.add(labelCounterBalises);

		//labelCounterSatellite
		labelCounterSatellite = new NiLabel("[Satellites] Total données:0");
		labelCounterSatellite.setForeground(Color.black);
		labelCounterSatellite.setFont(new Font("Arial", Font.BOLD, 20));
		labelCounterSatellite.setLocation(new Point(0, 20));
		score.add(labelCounterSatellite);

		//LabelAntennes
		labelCounterAntenne = new NiLabel("[Antennes] Total données:0");
		labelCounterAntenne.setForeground(Color.black);
		labelCounterAntenne.setFont(new Font("Arial", Font.BOLD, 20));
		labelCounterAntenne.setLocation(new Point(0, 40));
		score.add(labelCounterAntenne);

		main.add(score);
	}

	public static void main(String[] args) {
		new Simulation().launch();
	}

}
