package MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Props.DataKeep;

public class Main extends JFrame {

	private JButton jb1 ,jb2;
	private myPanel jp;

	public Main() {
		
		setLayout(new BorderLayout());
		jp = new myPanel();
		jb1 = new JButton("開始遊戲");
		jp.add(jb1);
		jb1.setBounds(600-100, 1040/2-150, 200, 100);
		jb1.setFont(new Font("標楷體", Font.PLAIN, 30));
		
		jb2 = new JButton("上回紀錄");
		jp.add(jb2);
		jb2.setBounds(600-100, 1040/2, 200, 100);
		jb2.setFont(new Font("標楷體", Font.PLAIN, 30));
		
		add(jp, BorderLayout.CENTER);
		



		setSize(1200, 1040);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
		setLocation(300, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BattleTable();
				dispose();
			}
		});
		
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataKeep d = new DataKeep();
				d.DataRead();
				new Review(d.getread());
				dispose();
			}
		});

	}

}

class myPanel extends JPanel{
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
		
	}
	
}


