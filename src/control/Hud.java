package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument.Content;

import model.Cellule;
import model.IleModel;
import model.Player;

public class Hud extends JPanel {
	private IleModel model;
	Player[] playerArray;
	private Font font;
	private Color textColor;

	public Hud(IleModel model) {
		this.model = model;
		this.playerArray = model.playerArray;
		Dimension dim = new Dimension(65, model.LARGEUR * 32);
		this.setPreferredSize(dim);

		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
	}

	public void update() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.repaint();
		paint(g, 0, 10 * 32);

	}

	private void paint(Graphics g, int x, int y) {
		Image img;
		Image img1;
		Image zero = new ImageIcon(getClass().getClassLoader().getResource("zero.gif")).getImage();
		Image un = new ImageIcon(getClass().getClassLoader().getResource("un.gif")).getImage();
		Image deux = new ImageIcon(getClass().getClassLoader().getResource("deux.gif")).getImage();
		Image trois = new ImageIcon(getClass().getClassLoader().getResource("trois.gif")).getImage();
		Image quatre = new ImageIcon(getClass().getClassLoader().getResource("quatre.gif")).getImage();
		Image slash = new ImageIcon(getClass().getClassLoader().getResource("slash.gif")).getImage();
		Image artifact = new ImageIcon(getClass().getClassLoader().getResource("artifact.png")).getImage();
		Image sandbag = new ImageIcon(getClass().getClassLoader().getResource("sandbag.png")).getImage();
        Image teleport = new ImageIcon(getClass().getClassLoader().getResource("teleport.png")).getImage();
		g.drawImage(slash, x + 13, y + 130, 17, 17, null);
		g.drawImage(un, x + 23, y + 130, 17, 17, null);
		g.drawImage(sandbag, x + 37, y + 125, 25, 25, null);

		g.drawImage(slash, x + 13, y + 160, 17, 17, null);
		g.drawImage(un, x + 23, y + 160, 17, 17, null);
		g.drawImage(teleport, x + 37, y + 155, 25, 25, null);

		if (this.model.playerArray[model.index].teleport == 0) {
			g.drawImage(zero, x, y + 160, 17, 17, null);
		} else {
			g.drawImage(un, x, y + 160, 17, 17, null);
		}

		if (this.model.playerArray[model.index].SacdeSable == 0) {
			g.drawImage(zero, x, y + 130, 17, 17, null);
		} else {
			g.drawImage(un, x, y + 130, 17, 17, null);
		}

		g.drawImage(slash, x + 13, y + 100, 17, 17, null);
		g.drawImage(quatre, x + 23, y + 100, 17, 17, null);

		g.drawImage(slash, x + 13, y + 70, 17, 17, null);
		g.drawImage(quatre, x + 23, y + 70, 17, 17, null);
		g.drawImage(artifact, x + 40, y + 100, 17, 17, null);
		if (this.model.playerArray[model.index].Artifact() == 0) {
			g.drawImage(zero, x, y + 100, 17, 17, null);
		} else if (this.model.playerArray[model.index].Artifact() == 1) {
			g.drawImage(un, x, y + 100, 17, 17, null);
		} else if (this.model.playerArray[model.index].Artifact() == 2) {
			g.drawImage(deux, x, y + 100, 17, 17, null);
		} else if (this.model.playerArray[model.index].Artifact() == 3) {
			g.drawImage(trois, x, y + 100, 17, 17, null);
		} else if (this.model.playerArray[model.index].Artifact() == 4) {
			g.drawImage(quatre, x, y + 100, 17, 17, null);
		}

		if (this.model.playerArray[model.index].keycount() == 0) {
			g.drawImage(zero, x, y + 70, 17, 17, null);
		} else if (this.model.playerArray[model.index].keycount() == 1) {
			g.drawImage(un, x, y + 70, 17, 17, null);
		} else if (this.model.playerArray[model.index].keycount() == 2) {
			g.drawImage(deux, x, y + 70, 17, 17, null);
		} else if (this.model.playerArray[model.index].keycount() == 3) {
			g.drawImage(trois, x, y + 70, 17, 17, null);
		} else {
			g.drawImage(quatre, x, y + 70, 17, 17, null);
		}

		if (this.model.index == 0) {
			img = new ImageIcon(".//res//redplayer.png").getImage();
			img1 = new ImageIcon(".//res//key.png").getImage();
			Graphics2D g2d = (Graphics2D) g;
			g.drawImage(img, x + 14, y + 20, 32, 32, null);
			g.drawImage(img1, x + 33, y + 64, 32, 32, null);

		} else if (this.model.index == 1) {
			img = new ImageIcon(".//res//blackplayer.png").getImage();
			img1 = new ImageIcon(".//res//key.png").getImage();
			Graphics2D g2d = (Graphics2D) g;
			g.drawImage(img, x + 14, y + 20, 32, 32, null);
			g.drawImage(img1, x + 33, y + 64, 32, 32, null);
		} else if (this.model.index == 2) {
			img = new ImageIcon(".//res//mibounplayer.png").getImage();
			img1 = new ImageIcon(".//res//key.png").getImage();
			Graphics2D g2d = (Graphics2D) g;
			g.drawImage(img, x + 14, y + 20, 32, 32, null);
			g.drawImage(img1, x + 33, y + 64, 32, 32, null);
		} else if (this.model.index == 3) {
			img = new ImageIcon(".//res//greenplayer.png").getImage();
			img1 = new ImageIcon(".//res//key.png").getImage();
			Graphics2D g2d = (Graphics2D) g;
			g.drawImage(img, x + 14, y + 20, 32, 32, null);
			g.drawImage(img1, x + 33, y + 64, 32, 32, null);
		}
	}
}
