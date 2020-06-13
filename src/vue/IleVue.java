package vue;


import java.util.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import control.ButtonEnd;
import control.Hud;
import model.*;

class IleVue extends JFrame {

	private VueIle grille;
	private Hud hud;
	// creation de fenetre de graphic du jeu
	public IleVue(IleModel model) {
		
		this.setTitle("jeu de ile interdite");
		this.setLayout(new FlowLayout());
		
		grille = new VueIle(model);
		hud = new Hud(model);
	
		ButtonEnd b = new ButtonEnd(model);
	    
        this.add(grille);
        this.add(hud,BorderLayout.NORTH);
        this.add(b);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	
}
