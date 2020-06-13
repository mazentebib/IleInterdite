package vue;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import control.Direction;
import model.Cellule;
import model.IleModel;
import model.Player;

public class VueIle extends JPanel implements Observer, KeyListener, MouseListener {
	
	private IleModel model;
	private int pixelByCase = 32;
	Player[] playerArray;
	//Constructeur pour la vue de l'ile qui contient les cellules qui construisent notre map et les joueurs
	public VueIle(IleModel model) {
		this.model = model;
		this.playerArray = model.playerArray;
		// modele.addObserver(this);
		Dimension dim = new Dimension(pixelByCase * IleModel.LARGEUR, pixelByCase * IleModel.HAUTEUR);
		this.setPreferredSize(dim);
		this.setFocusable(true);
		this.addKeyListener(this);
		addMouseListener(this);

	}

	public void update() {
		repaint();
	}
	//la methode paintComponent qui celon la nature de la cellule effectue les print des joueurs et des cellules convenablement
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.repaint();

		for (int i = 0; i < IleModel.LARGEUR; i++) {
			for (int j = 0; j < IleModel.HAUTEUR; j++) {
				paint(g, model.getCellule(i, j), (i) * pixelByCase, pixelByCase * j);
			}
		}
		for (int i = 0; i < playerArray.length; i++) {
			Cellule pos = model.playerArray[i].getPos();
			playerArray[i].paintPlayer((Graphics2D) g, pos.x() * pixelByCase, pos.y() * pixelByCase);
		}
		TakeArti();
	}
	//la methode paint test la nature de la cellule et puis affiche cette cellule à l'endroit precisé.
    //la cellule peut etre une decoration ou bien une zone traversable
	private void paint(Graphics g, Cellule c, int x, int y) {

		Image img;
		Graphics2D g2d = (Graphics2D) g;
		if (c.estTraversable() && c.fire == 1 && c.ice != 1 && c.sand != 1 && c.artifact() == 0) {
			img = new ImageIcon(getClass().getClassLoader().getResource("fire.png")).getImage();
			if(x/32==1) {
				img = new ImageIcon(getClass().getClassLoader().getResource("fireborderL.png")).getImage();
				if (y/32==1){
					img = new ImageIcon(getClass().getClassLoader().getResource("firel0c0.png")).getImage();
				}else if(y/32==11) {
					img = new ImageIcon(getClass().getClassLoader().getResource("firel60c0.png")).getImage();
				}
			}else if (x/32==11) {
				img = new ImageIcon(getClass().getClassLoader().getResource("fireborderR.png")).getImage();
				if(y/32==1) {
					img = new ImageIcon(getClass().getClassLoader().getResource("firel0c60.png")).getImage();
				}else if(y/32==11) {
					img = new ImageIcon(getClass().getClassLoader().getResource("firel60c60.png")).getImage();
				}
			}else if (y/32==1) {
				img = new ImageIcon(getClass().getClassLoader().getResource("fireborderU.png")).getImage();
			}else if (y/32==11) {
				img = new ImageIcon(getClass().getClassLoader().getResource("fireborderD.png")).getImage();
			}
			
			c.paintCel(g2d, img, x, y);
			if((y/32==5 && x/32==9) || (y/32==7 && x/32==2) || (y/32==9 && x/32==9)) {
				c.setEtat(3);
				c.setImg("fireVolc.png");
			}
			if((y/32==3 && x/32==6) || (y/32==8 && x/32==4)) {
				c.setEtat(3);
				c.setImg("fireberg.png");
			}
			c.paintCel(g2d, img, x, y);
		} else if (c.estTraversable() && c.ice == 1 && c.fire != 1 && c.sand != 1 && c.artifact() == 0) {
			img = new ImageIcon(getClass().getClassLoader().getResource("ice.png")).getImage();
			//if (i <= 11 && j > 11 && j < HAUTEUR + 2)
			if(x/32==1) {
				img = new ImageIcon(getClass().getClassLoader().getResource("iceborderL.png")).getImage();
				if (y/32==12){
					img = new ImageIcon(getClass().getClassLoader().getResource("icel0c0.png")).getImage();
				}else if(y/32==model.HAUTEUR-2) {
					img = new ImageIcon(getClass().getClassLoader().getResource("icel60c0.png")).getImage();
				}
			}else if (x/32==11) {
				img = new ImageIcon(getClass().getClassLoader().getResource("iceborderR.png")).getImage();
				if(y/32==12) {
					img = new ImageIcon(getClass().getClassLoader().getResource("icel0c60.png")).getImage();
				}else if(y/32==model.HAUTEUR-2) {
					img = new ImageIcon(getClass().getClassLoader().getResource("icel60c60.png")).getImage();
				}
			}else if (y/32==12) {
				img = new ImageIcon(getClass().getClassLoader().getResource("iceborderU.png")).getImage();
			}else if (y/32==model.HAUTEUR-2) {
				img = new ImageIcon(getClass().getClassLoader().getResource("iceborderD.png")).getImage();
			}
			
			c.paintCel(g2d, img, x, y);
			if((y/32==15 && x/32==8) || (y/32==17 && x/32==2) || (y/32==19 && x/32==9)) {
				c.setEtat(3);
				c.setImg("iceberg.png");
			}
			if((y/32==13 && x/32==6) || (y/32==18 && x/32==4)) {
				c.setEtat(3);
				c.setImg("iceberg2.png");
			}
			c.paintCel(g2d, img, x, y);
		} else if (c.estTraversable() && c.ice == 0 && c.fire != 1 && c.sand == 1 && c.artifact() == 0) {
			img = new ImageIcon(getClass().getClassLoader().getResource("sand.png")).getImage();
			if(x/32==12) {
				img = new ImageIcon(getClass().getClassLoader().getResource("sandborderL.png")).getImage();
				if (y/32==1){
					img = new ImageIcon(getClass().getClassLoader().getResource("sandl0c0.png")).getImage();
				}else if(y/32==11) {
					img = new ImageIcon(getClass().getClassLoader().getResource("sandl60c0.png")).getImage();
				}
			}else if (x/32==model.LARGEUR-2) {
				img = new ImageIcon(getClass().getClassLoader().getResource("sandborderR.png")).getImage();
				if(y/32==1) {
					img = new ImageIcon(getClass().getClassLoader().getResource("sandl0c60.png")).getImage();
				}else if(y/32==11) {
					img = new ImageIcon(getClass().getClassLoader().getResource("sandl60c60.png")).getImage();
				}
			}else if (y/32==1) {
				img = new ImageIcon(getClass().getClassLoader().getResource("sandborderU.png")).getImage();
			}else if (y/32==11) {
				img = new ImageIcon(getClass().getClassLoader().getResource("sandborderD.png")).getImage();
			}
			
			c.paintCel(g2d, img, x, y);
			if((y/32==6 && x/32==15) || (y/32==10 && x/32==13) || (y/32==9 && x/32==19)) {
				c.setEtat(3);
				c.setImg("sandS.png");
			}
			if((y/32==3 && x/32==15) || (y/32==5 && x/32==21) ) {
				c.setEtat(3);
				c.setImg("na5la.png");
			}
			c.paintCel(g2d, img, x, y);
		} else if (c.estTraversable() && c.fire != 1 && c.ice != 1 && c.sand != 1 && c.artifact() == 0) {
			img = new ImageIcon(getClass().getClassLoader().getResource("chey7a.png")).getImage();
			if(y/32==12 && x/32==12) {
				img =new ImageIcon(getClass().getClassLoader().getResource("HeliDeck.png")).getImage(); // to paint the helicopter
			}
			c.paintCel(g2d, img, x, y);
			if((y/32==15 && x/32==18) || (y/32==17 && x/32==12) || (y/32==19 && x/32==19)) {
				c.setEtat(3);
				c.setImg("chajra.png");
			}
			if((y/32==13 && x/32==16) || (y/32==18 && x/32==14)) {
				c.setEtat(3);
				c.setImg("chajra2.png");
			}
			c.paintCel(g2d, img, x, y);
		} else if (c.estInnodee()) {
			img = new ImageIcon(getClass().getClassLoader().getResource("nonsubmerge.png")).getImage();
			
			c.paintCel(g2d, img, x, y);
		} else if (c.estSubmergee()) {
			img = new ImageIcon(getClass().getClassLoader().getResource("sea.png")).getImage();
			
			c.paintCel(g2d, img, x, y);
		}else if (c.estBloquee()) {
				
				c.paintCel(g2d, c.getImg(), x, y);
		}else if(c.wasInnodee()) {
				
				c.paintCel(g2d, c.getImg(), x, y);
			}
		 else if (c.estTraversable() && c.artifact() == 1 && c.fire != 1 && c.ice != 1 && c.sand != 1) {
			if (c.hasarti == 1) {
				img = new ImageIcon(getClass().getClassLoader().getResource("chestclosed.jpg")).getImage();
			} else {
				img = new ImageIcon(getClass().getClassLoader().getResource("chestopen.jpg")).getImage();
			}
			
			g.drawImage(img, x, y, 32, 32, null);
		}
		 else {
			 c.paintCel(g2d, c.getImg(), x, y);
		 }

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	//methode keypressed a effectué lors d'une interaction avec les flèches du clavier
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			playerArray[model.index].move(Direction.up);
			break;

		case KeyEvent.VK_DOWN:
			playerArray[model.index].move(Direction.down);
			break;

		case KeyEvent.VK_LEFT:
			playerArray[model.index].move(Direction.left);
			break;

		case KeyEvent.VK_RIGHT:
			playerArray[model.index].move(Direction.right);
			break;
		}

	}
	//teste si la cellule passée en paramtre est innondée
	public boolean AdjInnondee(Cellule c) {
		ArrayList<Cellule> adj = model.celluleAdj(model.playerArray[model.index]);
		if (c.estInnodee() && adj.contains(c)) {
			return true;
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	//cette methode verifie si un artifact a été recuperer ou pas et ajuste le modele convenablement :
	//enleve un key au joueur qui a ramassé l'artifact et ejoute ce dernier aux artifacts de l'equipe
	public void TakeArti() {
		ArrayList<Cellule> chests = this.model.Chest();
		for (Cellule c : chests) {

			if (c.x() == this.model.playerArray[model.index].getPos().x()
					&& c.y() == this.model.playerArray[model.index].getPos().y()) {

				if (this.model.playerArray[model.index].keycount() >= 1) {
					this.model.playerArray[model.index].removekey();
					this.model.playerArray[model.index].AddArti();
					this.model.cellules[this.model.playerArray[model.index].getPos()
							.x()][this.model.playerArray[model.index].getPos().y()].hasarti = 0;
				}
			}
		}

	}
	//methode qui intervient lors d'un clique qe la sourie:
    //pour un clique avec le bouton gauche on peut assecher une zone adjacente au joueur 
    //pour un clique avec le bouton droit on peut utiliser une action speciale : sac de sable 
    //pour un clique avec le bouton au milieu de la souris on peut utiliser l'action special teleport
	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (this.model.playerArray[model.index].getCpt() <= 2) {
				int a = (int) (e.getX() + e.getComponent().getLocation().getX()) / 32;
				int b = (int) (e.getY() + e.getComponent().getLocation().getY()) / 32;
				if (AdjInnondee(model.cellules[a][b]) || model.playerArray[model.index].getPos().x() == a
						&& model.playerArray[model.index].getPos().y() == b) {
					model.cellules[a][b].setEtat(0);
				}
				this.model.playerArray[model.index].incrementCpt();
			}
		}

		if (SwingUtilities.isRightMouseButton(e)) {
			ArrayList<Cellule> adj = this.model.celluleAdj(model.playerArray[this.model.index]);
			if (this.model.playerArray[model.index].SacdeSable != 0) {
				int a = (int) (e.getX() + e.getComponent().getLocation().getX()) / 32;
				int b = (int) (e.getY() + e.getComponent().getLocation().getY()) / 32;
				if (adj.contains(this.model.cellules[a][b]) == false && this.model.cellules[a][b].getEtat() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to use your sand bag?", "Action",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						model.cellules[a][b].setEtat(0);
						this.model.playerArray[model.index].SacdeSable = 0;
					} else if (result == JOptionPane.NO_OPTION) {
					}

				}

			}
		}

		if (SwingUtilities.isMiddleMouseButton(e)) {
			int a = (int) (e.getX() + e.getComponent().getLocation().getX()) / 32;
			int b = (int) (e.getY() + e.getComponent().getLocation().getY()) / 32;
			ArrayList<Cellule> adj = this.model.celluleAdj(model.playerArray[this.model.index]);

			if (this.model.playerArray[model.index].teleport != 0) {
				if (adj.contains(this.model.cellules[a][b]) == false && this.model.cellules[a][b].getEtat() != 2
						&& this.model.cellules[a][b].getEtat() != 3) {
					int x = this.model.playerArray[model.index].getPos().x();
					int y = this.model.playerArray[model.index].getPos().y();
					int res = JOptionPane.showConfirmDialog(null, "Sure? You want to tp there ?", "Action",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (res == JOptionPane.YES_OPTION) {
						this.teleport(this.model.playerArray[model.index], this.model.cellules[a][b]);
						this.model.playerArray[model.index].teleport = 0;
					} else if (res == JOptionPane.NO_OPTION) {

					}

					for (int i = 0; i < 4; i++) {
						if (i != this.model.index) {
							if (x == this.model.playerArray[i].getPos().x()
									&& y == this.model.playerArray[i].getPos().y()) {
								int result = JOptionPane.showConfirmDialog(null,
										"Do You want to take " + model.playerArray[i].getName() + " with you ?",
										"Action", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (result == JOptionPane.YES_OPTION) {
									this.teleport(model.playerArray[i], this.model.cellules[a][b]);
								} else if (result == JOptionPane.NO_OPTION) {

								}

							}
						}
					}
				}

			}
		}

	}
	// teleportation d'un joueur
	public void teleport(Player p, Cellule c) {
		Cellule pos = new Cellule(c.x(), c.y());
		p.setPos(pos);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}