package MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BattleTable extends JFrame{
	
	//------------------畫在這上面的東西-----------------
	private FightScene jp = new FightScene();
	private JButton jb1 , jb2;
	
	public BattleTable(){
		setLayout(new BorderLayout());
		JPanel jpp = new JPanel();
		jb1 = new JButton("返回主畫面");
		jb2 = new JButton("記錄及結束遊戲");
		jpp.add(jb1); jpp.add(jb2);
		add(jpp,BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		setSize(1200, 1040);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
		setLocation(300,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();
				dispose();
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jp.handT();
				new Main();
				dispose();
			}
		});
		
	}
}





