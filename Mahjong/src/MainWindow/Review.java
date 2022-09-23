package MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Props.DataKeep;
import Props.Mahjong;

public class Review extends JFrame {

	private myPanel jp;
	DataKeep dp;
	LinkedList<Integer> list;
	LinkedList<Mahjong> Mlist;

	public Review(LinkedList<Integer> list) {
		
		this.list = list;
		Mlist = new LinkedList<>();
		setLayout(new BorderLayout());
		jp = new myPanel();
		add(jp, BorderLayout.CENTER);
//		System.out.println(list.get(1));
		for (int i = 0 ; i<list.size();i++) {
			int aa = list.get(i);
			Mlist.add(new Mahjong(aa));
		}



		setSize(1200, 1040);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
		setLocation(300, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				new Main();
				dispose();
			}
			
		});
		
		

	}

	private class myPanel extends JPanel{
		BufferedImage img;
		
		public myPanel() {
			setLayout(null);
			
			try {
				img = ImageIO.read(new File("dir2/tilesImage/mainBG.jpeg"));
			} catch (IOException e) {e.toString();}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(img, -150,0, img.getWidth(), img.getHeight(),null);
			
			for (int i = 0; i < Mlist.size(); i++) {
				int original = 145;
				int up = i * 65;
				g2d.drawImage(Mlist.get(i).tilesImage, original + up+50, 500, 65, 110, null);
			}
			
		}
		
	}
	
	
}


