package MainWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Props.DataKeep;
import Props.Mahjong;

public class FightScene extends JPanel {
	private LinkedList<Mahjong> tilesMountain = Mahjong.tilesMountaion();

	public LinkedList<Mahjong> handTiles = new LinkedList<>();// 東家手牌，本家
	private LinkedList<Mahjong> lostSpace1 = new LinkedList<>(); // 東家第一排
	private LinkedList<Mahjong> lostSpace2 = new LinkedList<>();
	private LinkedList<Mahjong> lostSpace3 = new LinkedList<>();
	private LinkedList<Mahjong> myMelds = new LinkedList<>();
	public Mahjong lostTile;

	private LinkedList<Mahjong> handTilesAfter = new LinkedList<>(); // 南家牌
	private LinkedList<Mahjong> lostSpace4 = new LinkedList<>(); // 東家第一排
	private LinkedList<Mahjong> lostSpace5 = new LinkedList<>();
	private LinkedList<Mahjong> lostSpace6 = new LinkedList<>();

	private LinkedList<Mahjong> handTilesWindow = new LinkedList<>(); // 西家牌
	private LinkedList<Mahjong> lostSpace7 = new LinkedList<>(); // 東家第一排
	private LinkedList<Mahjong> lostSpace8 = new LinkedList<>();
	private LinkedList<Mahjong> lostSpace9 = new LinkedList<>();

	private LinkedList<Mahjong> handTilesBefore = new LinkedList<>(); // 北家牌
	private LinkedList<Mahjong> lostSpace10 = new LinkedList<>(); // 東家第一排
	private LinkedList<Mahjong> lostSpace11 = new LinkedList<>();
	private LinkedList<Mahjong> lostSpace12 = new LinkedList<>();

	private Mahjong temp; // 手牌暫存區
	private int getTile; // 預設0
	// private int page = 0 ; //紀錄現在到第幾步
	private boolean watch = true; // 本家該不該看到牌
	private boolean take = true;
	private boolean blook = false;
	private BufferedImage bg;

	public FightScene() {

		setBackground(Color.gray);
		try {bg = ImageIO.read(new File("dir2/tilesImage/mainBG.jpeg"));} catch (IOException e) {}
		// -------手牌14張
		int openGet, openStart;// ----發送張數
		// ----本家手牌
		for (int i = 0; i < 13; i++) {
			handTiles.add(tilesMountain.get(i));
			getTile++;
		} // 13張
		LinkedList<Integer> myTile = new LinkedList<>();// 創立數字list
		// 把牌號紀錄進去
		for (int i = 0; i < handTiles.size(); i++) {
			myTile.add(handTiles.get(i).tilesCode);
		}
		Collections.sort(myTile);// 對數字list進行升序排序
		// 更新原本的清單
		for (int i = 0; i < handTiles.size(); i++) {
			handTiles.set(i, new Mahjong(myTile.get(i)));
		}
		openStart = getTile;
		openGet = openStart + 13;

		// 下家
		for (int i = openStart; i < openGet; i++) {
			handTilesAfter.add(tilesMountain.get(i));
			handTilesAfter.get(i - openStart).tileInitPlayerLeft();
			getTile++;
		}
		openStart = getTile;
		openGet = openStart + 13;
		// 對家
		for (int i = openStart; i < openGet; i++) {
			handTilesWindow.add(tilesMountain.get(i));
			handTilesWindow.get(i - openStart).tileInitPlayerOn();
			getTile++;
		}
		openStart = getTile;
		openGet = openStart + 13;
		// 上家
		for (int i = openStart; i < openGet; i++) {
			handTilesBefore.add(tilesMountain.get(i));
			handTilesBefore.get(i - openStart).tileInitPlayerRight();
			getTile++;
		}

		// ---東家拿牌
		temp = tilesMountain.get(getTile);
		getTile++;
		watch = true;

		// --------監聽事件 丟牌
		addMouseListener(new MyMouseListener(1));
		addMouseListener(new MyMouseListener(2));
		addMouseListener(new MyMouseListener(3));
		addMouseListener(new MyMouseListener(4));
		addMouseListener(new MyMouseListener(5));
		addMouseListener(new MyMouseListener(6));
		addMouseListener(new MyMouseListener(7));
		addMouseListener(new MyMouseListener(8));
		addMouseListener(new MyMouseListener(9));
		addMouseListener(new MyMouseListener(10));
		addMouseListener(new MyMouseListener(11));
		addMouseListener(new MyMouseListener(12));
		addMouseListener(new MyMouseListener(13));
		addMouseListener(new MyMouseListener(14));
		// ---------監聽事件範圍結尾

	}

	// ----滑鼠監聽事件判斷
	private class MyMouseListener extends MouseAdapter {
		private int tile;
		private StopTask s1, s2, s3;
		private PlayerRight pr;
		private PlayerOn po;
		private PlayerLeft pl;

		public MyMouseListener(int tile) {
			this.tile = tile;
		}

		public void mousePressed(MouseEvent e) {
			Timer timer1 = new Timer();
			Timer timer2 = new Timer();
			Timer timer3 = new Timer();
			Mahjong lostTemp;
			if ((handTiles.size() + 1 < tile)) {
				removeMouseListener(this);
			}

			if (handTiles.size() >= 1) { // -----當列表長度達到或大於該位置，才會啟動監聽事件
				int beginX = 145;
				int Y = 850;
				int tileWidth = 65;
				int tileHeight = 110;
				int witchTile = tile - 1;
				int mouseX = e.getX();
				int mouseY = e.getY();
				int positionX = beginX + witchTile * 65;
				int positionY = Y;
				
				if (tile == handTiles.size() + 1) {
					positionX += 10;
				}
				int endX = positionX + tileWidth;
				int endY = positionY + tileHeight;
				// ------如滑鼠點擊座標在範圍內則給予該位置物件變化
				if (((mouseX > positionX) && (mouseY > positionY)) && ((mouseX < endX) && (mouseY < endY))) {
					// ----------自己打牌後執行的作業
					if (tile != handTiles.size() + 1) {
						// --------打出去的牌處理方法
						lostTemp = null;
						watch = false;
						int aaa = handTiles.get(witchTile).tilesCode;// 取得該張牌的牌號用於改變外型
						handTiles.get(witchTile).tileSwitch(aaa); // 將該張牌改變外型
						// 選擇將該張牌加入哪個棄牌區
						if (lostSpace2.size() > 5 && lostSpace3.size() < 10) {
							lostSpace3.add(handTiles.get(witchTile));
						}
						if (lostSpace1.size() > 5 && lostSpace2.size() < 6) {
							lostSpace2.add(handTiles.get(witchTile));
						}
						if (lostSpace1.size() < 6) {
							lostSpace1.add(handTiles.get(witchTile));
						}
						handTiles.remove(witchTile);// 將該張牌自手牌中刪除
						if (take) {
							handTiles.add(temp);
						} // 如果沒有吃碰槓，把拿到的牌加入手牌

						// -------------------------------------------------------------
					} else {
						int aaa = temp.tilesCode;// 取得該張牌的牌號用於改變外型
						Mahjong lost = new Mahjong(aaa);
						lost.tileSwitch(aaa); // 將該張牌改變外型
						// 不加入手牌直接送入棄牌區
						// 選擇將該張牌加入哪個棄牌區
						if (lostSpace2.size() > 5 && lostSpace3.size() < 15) {
							lostSpace3.add(lost);
						}
						if (lostSpace1.size() > 5 && lostSpace2.size() < 6) {
							lostSpace2.add(lost);
						}
						if (lostSpace1.size() < 6) {
							lostSpace1.add(lost);
						}
						// -------------------------------------------------------------
					}

					// --------其他三家進行動作 牌先+的次數3次
					// 給出一個數字決定要停幾秒
					// 下家拿牌
					int bb = 1;

					// 下家
					int loseT = (int) (Math.random() * 12 + 1);
					lostTemp = handTilesAfter.get(loseT);
					pr = new PlayerRight(loseT);
					s1 = new StopTask(timer1);
					timer1.schedule(pr, bb * 1000);

					// ---判斷碰槓

					// 對家拿牌
					bb++;
					loseT = (int) (Math.random() * 12 + 1);
					lostTemp = handTilesWindow.get(loseT);
					po = new PlayerOn(loseT);
					s2 = new StopTask(timer2);
					timer2.schedule(po, bb * 1000);
					// ---判斷碰槓

					// 上家拿牌
					// 上家
					bb++;
					loseT = (int) (Math.random() * 12 + 1);
					lostTemp = handTilesBefore.get(loseT);
					pl = new PlayerLeft(loseT);
					s3 = new StopTask(timer3);
					timer3.schedule(pl, bb * 1000);
					// ---判斷吃碰槓

					// 都沒有
					// 本家拿牌
					++getTile;
					if(getTile >= 135-14) {
						LinkedList<Integer> handTileCodeList = new LinkedList<>();
						for(int i = 0 ; i<handTiles.size();i++) {
							handTileCodeList.add(handTiles.get(i).tilesCode);
						}
						DataKeep keep = new DataKeep(handTileCodeList);
						keep.toDataBase();
						blook = true;
					}
					temp = tilesMountain.get(getTile); // 取得新手牌

					// --------手牌排序處理
					LinkedList<Integer> tileNum = new LinkedList<>();// 創立數字list
					// 把牌號紀錄進去
					for (int i = 0; i < handTiles.size(); i++) {
						tileNum.add(handTiles.get(i).tilesCode);
					}
					Collections.sort(tileNum);// 對數字list進行升序排序
					// 更新原本的清單
					for (int i = 0; i < handTiles.size(); i++) {
						handTiles.set(i, new Mahjong(tileNum.get(i)));
					}

				}
			}
			repaint();
			// 14手排 13長度
			if ((handTiles.size() + 1 < tile)) {
				removeMouseListener(this);
			}

		}
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// ---------------試繪--------------------------
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(bg,-450,-280, bg.getWidth()+560, bg.getHeight()+500,null);
		g2d.fill3DRect(475, 360, 250, 230, true);
		
		

		// -----誰家拿牌
		// ---------------手牌(東家)--------------------------
		for (int i = 0; i < handTiles.size(); i++) {
			int original = 145;
			int up = i * 65;
			g2d.drawImage(handTiles.get(i).tilesImage, original + up, 850, 65, 110, null);
		}
		int mytileX = 145 + handTiles.size() * 65 + 10;// ----我的手牌x座標
		int mytileY = 850;// ----我的手牌y座標
		if(getTile%4==1) {
		g2d.drawImage(temp.tilesImage, mytileX, mytileY, 65, 110, null);}

		// 吃碰槓
		// 拿到的清單是已經吃碰槓的清單，清單: 1面子 2面子 3面子
		for (int i = 0; i < myMelds.size(); i++) {
			int original = 1200 - 65;
			int back = i * 52 + (88 - 52) * (i / 3);
			if (i % 3 == 2) {
				myMelds.get(i).tileSwitchLeft(myMelds.get(i).tilesCode);
				g2d.drawImage(myMelds.get(i).tilesImage, original - back - (66 - 52), 850 + 22 + (88 - 65), 66, 65,
						null);
			} else {
				myMelds.get(i).tileSwitchOn(myMelds.get(i).tilesCode);
				g2d.drawImage(myMelds.get(i).tilesImage, original - back, 850 + 22, 52, 88, null);
			}
		}

		// ----------------南家--------------------------------
		for (int i = 0; i < 13; i++) {
			int original = 155;
			int up = i * 40;
			g2d.drawImage(handTilesAfter.get(i).tilesImage, 0, original + up, 40, 110, null);
		}

		// ----------------西家--------------------------------
		for (int i = 0; i < 13; i++) {
			int original = 236;
			int up = i * 52;
			g2d.drawImage(handTilesWindow.get(i).tilesImage, original + up, 5, 52, 88, null);
		}

		// ----------------北家--------------------------------
		for (int i = 0; i < 13; i++) {
			int original = 155;
			int up = i * 40;
			g2d.drawImage(handTilesBefore.get(i).tilesImage, 1145, original + up, 40, 110, null);
		}

		// ---------------棄牌區
		// ------東家下
		int positionX = 480;// ----第一張牌的x座標
		int positionY = 590;// ----第一張牌的y座標
		int tW = 39;// ----此區牌的寬度
		int tH = 66;// ----此區牌的高度
		int howMuch1 = lostSpace1.size();// 棄牌區長度，東家1
		int rows1 = howMuch1 / 6 + 1;// ----在第幾列
		int howMuch2 = lostSpace2.size();// 棄牌區長度，東家2
		int rows2 = howMuch2 / 6 + 1;// ----在第幾列
		int howMuch3 = lostSpace3.size();// 棄牌區長度，東家3
		int rows3 = howMuch3 / 6 + 1;// ----在第幾列
		// ----東家1
		if (howMuch1 != 0) {
			for (int i = 0; i < howMuch1; i++) {
				if (i <= 6 || rows1 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = 0; a < howMuch1; a++) {
						int px = positionX + tW * a + 3;
						int door = a;
						g2d.drawImage(lostSpace1.get(door).tilesImage, px, positionY, tW, tH, null);
					}
				}
			}
		}
		// ----東家2
		if (howMuch2 != 0) {
			for (int i = 0; i < howMuch2; i++) {
				if (i <= 6 || rows2 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = 0; a < howMuch2; a++) {
						int px = positionX + tW * a + 3;
						int py = positionY + tH - 10;
						int door = a;
						g2d.drawImage(lostSpace2.get(door).tilesImage, px, py, tW, tH, null);
					}
				}
			}
		}
		// ----東家3
		if (howMuch3 != 0) {
			for (int i = 0; i < howMuch3; i++) {
				if (i <= 6 || rows3 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = 0; a < howMuch3; a++) {
						int px = positionX + tW * a + 3;
						int py = positionY + tH * 2 - 20;
						int door = a;
						g2d.drawImage(lostSpace3.get(door).tilesImage, px, py, tW, tH, null);
					}
				}
			}
		}

		// ------西家上
		int positionOnX = 480 + 39 * 6 + 5;// ----第一張牌的x座標
		int positionOnY = 185;// ----第一張牌的y座標
		int onTW = 39;// ----此區牌的寬度
		int onTH = 66;// ----此區牌的高度
		int onHowMuch1 = lostSpace7.size();// 棄牌區長度，西家1
		int onRows1 = onHowMuch1 / 6 + 1;// ----在第幾列
		int onHowMuch2 = lostSpace8.size();// 棄牌區長度，西家2
		int onRows2 = onHowMuch2 / 6 + 1;// ----在第幾列
		int onHowMuch3 = lostSpace9.size();// 棄牌區長度，西家3
		int onRows3 = onHowMuch3 / 6 + 1;// ----在第幾列

		// ----西家3
		if (onHowMuch3 != 0) {
			for (int i = 0; i < onHowMuch3; i++) {
				if (i <= 6 || onRows3 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = onHowMuch3; a > 0; a--) {
						int px = positionOnX - onTW * a - 3;
						int door = a - 1;
						g2d.drawImage(lostSpace9.get(door).tilesImage, px, positionOnY, onTW, onTH, null);
					}
				}
			}
		}
		// ----西家2
		if (onHowMuch2 != 0) {
			for (int i = 0; i < onHowMuch2; i++) {
				if (i <= 6 || onRows2 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = onHowMuch2; a > 0; a--) {
						int px = positionOnX - onTW * a - 3;
						int py = positionOnY + onTH - 10;
						int door = a - 1;
						g2d.drawImage(lostSpace8.get(door).tilesImage, px, py, onTW, onTH, null);
					}
				}
			}
		}
		// ----西家1
		if (onHowMuch1 != 0) {
			for (int i = 0; i < onHowMuch1; i++) {
				if (i <= 6 || onRows1 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = onHowMuch1; a > 0; a--) {
						int px = positionOnX - onTW * a - 3;
						int py = positionOnY + onTH * 2 - 20;
						int door = a - 1;
						g2d.drawImage(lostSpace7.get(door).tilesImage, px, py, onTW, onTH, null);
					}
				}
			}
		}

		// ------北家左
		int positionLeftX = 420;// ----第一張牌的x座標
		int positionLeftY = 365;// ----第一張牌的y座標
		int LeftTW = 55;// ----此區牌的寬度
		int LeftTH = 45;// ----此區牌的高度
		int LeftHowMuch1 = lostSpace10.size();// 棄牌區長度，北家1
		int LeftRows1 = LeftHowMuch1 / 6 + 1;// ----在第幾列
		int LeftHowMuch2 = lostSpace11.size();// 棄牌區長度，北家2
		int LeftRows2 = LeftHowMuch2 / 6 + 1;// ----在第幾列
		int LeftHowMuch3 = lostSpace12.size();// 棄牌區長度，北家3
		int LeftRows3 = LeftHowMuch3 / 6 + 1;// ----在第幾列
		// ----北家1
		if (LeftHowMuch1 != 0) {
			for (int i = 0; i < LeftHowMuch1; i++) {
				if (i <= 6 || LeftRows1 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = 0; a < LeftHowMuch1; a++) {
						int py = positionLeftY + LeftTH * a - a * 10;
						int door = a;
						g2d.drawImage(lostSpace10.get(door).tilesImage, positionLeftX, py, LeftTW, LeftTH, null);
					}
				}
			}
		}
		// ----北家2
		if (LeftHowMuch2 != 0) {
			for (int i = 0; i < LeftHowMuch2; i++) {
				if (i <= 6 || LeftRows2 > 0) { // rows=1 最後一次=2
					for (int a = 0; a < LeftHowMuch2; a++) {
						int py = positionLeftY + LeftTH * a - a * 10;
						int px = positionLeftX - LeftTW;
						int door = a;
						g2d.drawImage(lostSpace11.get(door).tilesImage, px, py, LeftTW, LeftTH, null);
					}
				}
			}
		}
		// ----北家3
		if (LeftHowMuch3 != 0) {
			for (int i = 0; i < LeftHowMuch3; i++) {
				if (i <= 6 || LeftRows3 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = 0; a < LeftHowMuch3; a++) {
						int py = positionLeftY + LeftTH * a - a * 10;
						int px = positionLeftX - LeftTW * 2;
						int door = a;
						g2d.drawImage(lostSpace12.get(door).tilesImage, px, py, LeftTW, LeftTH, null);
					}
				}
			}
		}

		// ------南家右
		int positionRightX = 725;// ----第一張牌的x座標
		int positionRightY = 365 + 45 * 5 - 15;// ----第一張牌的y座標
		int RightTW = 55;// ----此區牌的寬度
		int RightTH = 45;// ----此區牌的高度
		int RightHowMuch1 = lostSpace4.size();// 棄牌區長度，北家1
		int RightRows1 = RightHowMuch1 / 6 + 1;// ----在第幾列
		int RightHowMuch2 = lostSpace5.size();// 棄牌區長度，北家2
		int RightRows2 = RightHowMuch2 / 6 + 1;// ----在第幾列
		int RightHowMuch3 = lostSpace6.size();// 棄牌區長度，北家3
		int RightRows3 = RightHowMuch3 / 6 + 1;// ----在第幾列
		// ----南家1
		if (RightHowMuch1 != 0) {
			for (int i = 0; i < RightHowMuch1; i++) {
				if (i <= 6 || RightRows1 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = RightHowMuch1; a > 0; a--) {
						int py = positionRightY - RightTH * a + a * 10;
						int door = a - 1;
						g2d.drawImage(lostSpace4.get(door).tilesImage, positionRightX, py, RightTW, RightTH, null);
					}
				}
			}
		}
		// ----南家2
		if (RightHowMuch2 != 0) {
			for (int i = 0; i < RightHowMuch2; i++) {
				if (i <= 6 || RightRows2 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = RightHowMuch2; a > 0; a--) {
						int py = positionRightY - RightTH * a + a * 10;
						int px = positionRightX + RightTW;
						int door = a - 1;
						g2d.drawImage(lostSpace5.get(door).tilesImage, px, py, RightTW, RightTH, null);
					}
				}
			}
		}
		// ----南家3
		if (RightHowMuch3 != 0) {
			for (int i = 0; i < RightHowMuch3; i++) {
				if (i <= 6 || RightRows3 > 0) { // rows=1 最後一次=2
					// ---畫在這行畫幾個
					for (int a = RightHowMuch3; a > 0; a--) {
						int py = positionRightY - RightTH * a + a * 10;
						int px = positionRightX + RightTW * 2;
						int door = a - 1;
						g2d.drawImage(lostSpace6.get(door).tilesImage, px, py, RightTW, RightTH, null);
					}
				}
			}
		}
		if(blook) {g2d.fill3DRect(2000, 2000, 0, 0, true);}
	}

	public class PlayerRight extends TimerTask {
		int rand;

		PlayerRight(int rand) {
			this.rand = rand;
		}

		@Override
		public void run() {
			watch = false;
			++getTile;
			temp.tileInitPlayerLeft();
			handTilesAfter.add(temp);
			temp = tilesMountain.get(getTile); // 取得新手牌
			int bbb = handTilesAfter.get(rand).tilesCode;// 取得該張牌的牌號用於改變外型
			handTilesAfter.get(rand).tileSwitchRight(bbb); // 將該張牌改變外型

			// 選擇將該張牌加入哪個棄牌區
			if (lostSpace5.size() > 5 && lostSpace6.size() < 10) {
				lostSpace6.add(handTilesAfter.get(rand));
			}
			if (lostSpace4.size() > 5 && lostSpace5.size() < 6) {
				lostSpace5.add(handTilesAfter.get(rand));
			}
			if (lostSpace4.size() < 6) {
				lostSpace4.add(handTilesAfter.get(rand));
			}
			handTilesAfter.remove(rand);// 將該張牌自手牌中刪除

			repaint();
		}
	}

	public class PlayerOn extends TimerTask {
		int rand;

		PlayerOn(int rand) {
			this.rand = rand;
		}

		@Override
		public void run() {
			watch = false;
			++getTile;
			temp.tileInitPlayerOn();
			handTilesWindow.add(temp);
			temp = tilesMountain.get(getTile); // 取得新手牌
			int ccc = handTilesWindow.get(rand).tilesCode;// 取得該張牌的牌號用於改變外型
			handTilesWindow.get(rand).tileSwitchOn(ccc); // 將該張牌改變外型
			// 選擇將該張牌加入哪個棄牌區
			if (lostSpace8.size() > 5 && lostSpace9.size() < 10) {
				lostSpace9.add(handTilesWindow.get(rand));
			}
			if (lostSpace7.size() > 5 && lostSpace8.size() < 6) {
				lostSpace8.add(handTilesWindow.get(rand));
			}
			if (lostSpace7.size() < 6) {
				lostSpace7.add(handTilesWindow.get(rand));
			}
			handTilesWindow.remove(rand);// 將該張牌自手牌中刪除
			repaint();
		}

	}

	public class PlayerLeft extends TimerTask {
		int rand;

		PlayerLeft(int rand) {
			this.rand = rand;
		}

		@Override
		public void run() {
			watch = false;
			++getTile;
			temp.tileInitPlayerRight();
			handTilesBefore.add(temp);
			temp = tilesMountain.get(getTile); // 取得新手牌
			int ddd = handTilesBefore.get(rand).tilesCode;// 取得該張牌的牌號用於改變外型
			handTilesBefore.get(rand).tileSwitchLeft(ddd); // 將該張牌改變外型
			// 選擇將該張牌加入哪個棄牌區
			if (lostSpace11.size() > 5 && lostSpace12.size() < 10) {
				lostSpace12.add(handTilesBefore.get(rand));
			}
			if (lostSpace10.size() > 5 && lostSpace11.size() < 6) {
				lostSpace11.add(handTilesBefore.get(rand));
			}
			if (lostSpace10.size() < 6) {
				lostSpace10.add(handTilesBefore.get(rand));
			}
			handTilesBefore.remove(rand);// 將該張牌自手牌中刪除
			repaint();
		}

	}

	public class tempVisible extends TimerTask {

		@Override
		public void run() {
			watch = true;

		}

	}

	private class StopTask extends TimerTask {
		private Timer timer;

		StopTask(Timer timer) {
			this.timer = timer;
		}

		@Override
		public void run() {
			timer.cancel();
		}
	}

	public void handT(){
		LinkedList<Integer> handTileCodeList = new LinkedList<>();
		for(int i = 0 ; i<handTiles.size();i++) {
			handTileCodeList.add(handTiles.get(i).tilesCode);
		}
		DataKeep keep = new DataKeep(handTileCodeList);
		keep.toDataBase();
	}
}





