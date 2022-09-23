package Props;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Mahjong implements Serializable{
	//數字
	private static final int ONE = 1;private static final int TWO = 2;
	private static final int THREE = 3;private static final int FOUR = 4;
	private static final int FIVE = 5;private static final int SIX = 6;
	private static final int SEVEN = 7;private static final int EIGHT = 8;
	private static final int NINE = 9;
	//萬餅索
	private static final int Character = 1;
	private static final int Circle = 2;
	private static final int Bamboo = 3;
	//是哪張
	private static final int EAST = 1;private static final int SOUTH = 2;
	private static final int WEST = 3;private static final int NORTH = 4;
	private static final int RED = 1;private static final int WHITE = 2;
	private static final int GREEN = 3;
	//字役
	private static final int WIND = 4;
	private static final int TEXT = 5;
	
	//牌的狀態
	public boolean isNumTiles; //是否為數字牌
	public int tilesType; //牌是萬餅索
	public int typeNumber; //牌的數字是多少
	public int tilesCode; //牌的編號
	public int[] tileCodeArray; //牌的陣列編號
	private static final boolean isUp = true; //掀開
	private static final boolean isDown = false; //蓋著
	public boolean upOrDown;
	private static final boolean isStand = true; //正放
	private static final boolean isBed = false; //側放
	public boolean StandOrBed;
	private static final boolean isLeftOrUp = true;//朝上左
	private static final boolean isRightOrDown = false;//朝下右
	public boolean leftUpOrRightDown;
	public BufferedImage tilesImage;
	
	
	
	
	public Mahjong(int tilesCode) {
		this.isNumTiles = isNumTiles(tilesCode); //是數牌字牌
		this.tilesType = whatType(tilesCode); //牌是萬餅索風役
		this.typeNumber = whatNum(tilesCode); //是幾號牌
		this.tilesCode = tilesCode; //牌編碼
		this.tileCodeArray = tilesArray(this.isNumTiles,this.typeNumber ,this.tilesType , tilesCode);//牌陣列編碼
		this.upOrDown = isDown;
		this.StandOrBed = isStand;
		this.leftUpOrRightDown = isLeftOrUp;
		this.tilesImage = tileImage(tilesCode);
	}
	
	
	public void tileImageByeBye() {
		tilesImage = null;
	}
	
	
	//初始化使用哪一張圖片
	public BufferedImage tileImage(int tileCode) {
		String url; String tileString = tileCode+"";
		BufferedImage img;
		if(tileCode<10) {
			url = String.format("dir2/tilesImage/handTile/handTile0%d.png", tileCode);
		}else {
			url = String.format("dir2/tilesImage/handTile/handTile%d.png", tileCode);
		}
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {img = null;}
		return img;
	}
	
	// 上邊玩家手牌展現
	public void tileInitPlayerOn() {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("dir2/tilesImage/handTile/handTile35.png"));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	// 右邊玩家手牌展現
	public void tileInitPlayerRight() {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("dir2/tilesImage/handTile/handTile36.png"));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	// 左邊玩家手牌展現
	public void tileInitPlayerLeft() {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("dir2/tilesImage/handTile/handTile37.png"));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	
	//本家丟牌
	public void tileSwitch(int tileCode) {
		String url; String tileString = tileCode+"";
		BufferedImage img;
		if(tileCode<10) {
			url = String.format("dir2/tilesImage/frontTile/frontTile0%d.png", tileCode);
		}else {
			url = String.format("dir2/tilesImage/frontTile/frontTile%d.png", tileCode);
		}
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	
	//下家丟牌
	public void tileSwitchRight(int tileCode) {
		String url; String tileString = tileCode+"";
		BufferedImage img;
		if(tileCode<10) {
			url = String.format("dir2/tilesImage/sideTile/sideTile0%d.png", tileCode);
		}else {
			url = String.format("dir2/tilesImage/sideTile/sideTile%d.png", tileCode);
		}
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	
	//對家丟牌
	public void tileSwitchOn(int tileCode) {
		String url; String tileString = tileCode+"";
		BufferedImage img;
		if(tileCode<10) {
			url = String.format("dir2/tilesImage/frontTile/frontTile0%d.png", tileCode);
		}else {
			url = String.format("dir2/tilesImage/frontTile/frontTile%d.png", tileCode);
		}
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	
	//上家丟牌
	public void tileSwitchLeft(int tileCode) {
		String url; String tileString = tileCode+"";
		BufferedImage img;
		if(tileCode<10) {
			url = String.format("dir2/tilesImage/sideTile/sideTile0%d.png", tileCode);
		}else {
			url = String.format("dir2/tilesImage/sideTile/sideTile%d.png", tileCode);
		}
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {img = null;}
		tilesImage = img;
	}
	
	
	//判斷是否為數字牌
	public boolean isNumTiles(int tilesCode) {
		if(tilesCode < 27) {return true;}else {return false;}
	}
	
	//該張牌的陣列編號
	public int[] tilesArray(boolean isNumTiles , int tilesNum , int tilesType , int tilesCode) {
		//---陣列內容數字意涵: 0是不是數字牌 1該牌是該種數字牌的幾號牌 2該牌是哪種牌 3該牌的編號
		int[] tilesArray = new int[4];
		if(isNumTiles) {tilesArray[0] = 1;}else {tilesArray[0] = 0;}
		tilesArray[1] = tilesNum; tilesArray[2] = tilesType; tilesArray[3] = tilesCode;
		return tilesArray;
	}
	//透過該張牌的編號定義該張牌是什麼牌
	public int whatType(int tilesCode) {
		if( tilesCode > 31) { return TEXT;}
		if( tilesCode > 27) { return WIND;}
		int type = tilesCode / NINE + 1;
		if( tilesCode % NINE == 0) { type -= 1;}
		return type;
	}
	
	public int whatNum(int tilesCode) {
		if(tilesCode > 27) {
		int num2 = tilesCode % 27 ;
		if( num2 > 4 ) { return num2-4;}else{ return num2;}}
		int num = tilesCode % NINE ;
		if( tilesCode % NINE == 0) { num = 9;}
		return num;
		
	}
	
	//判斷牌目前處於蓋著，站立等狀態。
	public boolean[] tilesNowIs() {
		boolean[] nowIs = new boolean[3];
		nowIs[0] = upOrDown; nowIs[1] = StandOrBed; nowIs[2] = leftUpOrRightDown;
		return nowIs;
	}
	
	//-----洗好的牌
	public static LinkedList<Mahjong> tilesMountaion(){
		//建立牌山框架
			LinkedList<Mahjong> list = new LinkedList<>();
			int[] wash = new int[136];
			for( int i = 0 ; i < 34 ; i++) {
				int j = i+1;
				wash[i*4+0]=j;wash[i*4+1]=j;wash[i*4+2]=j;wash[i*4+3]=j;
			}
			//洗牌
			for (int i = wash.length -1; i > 0; i--) {
				int rand = (int)(Math.random()*(i+1));
				int temp = wash[rand];
				wash[rand] = wash[i];
				wash[i] = temp;
			}
			//加入麻將牌
			for( int i = 0 ; i< wash.length ; i++) {
				if(wash[i]>27) {list.add( new Mahjong( wash[i]));}
				else {list.add( new Mahjong( wash[i]));}
			}
		return list;
	}
	

	public static void main(String[] args) {
		LinkedList<Mahjong> list = tilesMountaion();
		
		
		//驗證
		int aa = 0 ;
		for(Mahjong e : list) {
			++aa;
			System.out.print(e.tileCodeArray[0] + " ");
			if(aa%34 == 0) {System.out.println();}
		}
		
	}

}







