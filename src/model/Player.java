package model;


import vue.Observable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import control.Direction;

public class Player extends Observable {

	private IleModel m;
	private Direction theDirection;
	private int keyCount;
	private Cellule pos;
	private int roleCpt;
	private int nbCol;
	private static int artifact;
	public int SacdeSable = 0;
	private String name;
	public int teleport = 0;
	//constructeur de player
	public Player(Cellule pos, int nbCol) {
		this.theDirection = Direction.up;
		this.keyCount = 0;
		this.artifact = 0;
		this.pos = null;
		this.nbCol = nbCol;
		// this.roleCpt = 0;
	}
	// set la position de player
	public void setPos(Cellule c) {
		this.pos = c;
	}

	public void setModel(IleModel m) {
		this.m = m;
	}
	// player prend un clé 
	public void AddKey() {
		this.keyCount++;
	}
	// player obtenu un artifact
	public void AddArti() {
		if (this.artifact <= 4) {
			this.artifact++;
		}
	}
	// player lost key apres avoir un artifact
	public int removekey() {
		return this.keyCount--;
	}
	public int Artifact() {
		return this.artifact;
	}

	public Cellule getPos() {
		return this.pos;
	}
	// get le nombre des actions fait
	public int getCpt() {
		return this.roleCpt;
	}
	// increment +1 si player a fait un action
	public void incrementCpt() {
		this.roleCpt++;
		
	}
	// reset compteur apres avoir faire 3 actions
	public void resetCpt() {
		this.roleCpt = 0;
	}

	public int keycount() {
		return this.keyCount;
		
	}
	// nom de player
	public String getName() {
		return this.name;
	}
	// methode move de player 
	public void move(Direction key) {
		boolean actionPerformed = false;
		if (this.getCpt() <= 2) {
			if (key == Direction.up) {
				if (pos.y > 1)
					if (m.cellules[pos.x][pos.y - 1].estSubmergee() == false && !m.cellules[pos.x][pos.y - 1].estBloquee()) {
						pos.y -= 1;
						actionPerformed = true;
					}
			} else if (key == Direction.down) {
				if (pos.y >= m.HAUTEUR - 2)
					pos.y = pos.y;
				else if (m.cellules[pos.x][pos.y + 1].estSubmergee() == false && !m.cellules[pos.x][pos.y + 1].estBloquee()) {
					pos.y += 1;
					actionPerformed = true;
				}
			} else if (key == Direction.left) {
				if (pos.x < 2) {
					pos.x = pos.x;
				} else {
					if (m.cellules[pos.x - 1][pos.y].estSubmergee() == false && !m.cellules[pos.x - 1][pos.y].estBloquee()) {
						pos.x -= 1;
						actionPerformed = true;
					}
				}
			} else if (key == Direction.right) {
				if (pos.x >= m.LARGEUR - 2) {
					pos.x = pos.x;
				} else {
					if (m.cellules[pos.x + 1][pos.y].estSubmergee() == false && !m.cellules[pos.x + 1][pos.y].estBloquee())
						pos.x += 1;
					actionPerformed = true;
				}
			}
			if (actionPerformed) {
				this.incrementCpt();
			}
		}
	}
	// paint player
	public void paintPlayer(Graphics2D g, int x, int y) {
		Image img;
		if (nbCol == 0) {
			img = new ImageIcon(getClass().getClassLoader().getResource("redplayer.png")).getImage();
			name = "red player";
		} else if (nbCol == 1) {
			img = new ImageIcon(getClass().getClassLoader().getResource("blackplayer.png")).getImage();
			name = "black player";
		} else if (nbCol == 2) {
			img = new ImageIcon(getClass().getClassLoader().getResource("mibounplayer.png")).getImage();
			name = "pink player";
		} else {
			img = new ImageIcon(getClass().getClassLoader().getResource("greenplayer.png")).getImage();
			name = "green player";
		}
		g.drawImage(img, x, y, 32, 32 - 1, null);
	}

}
