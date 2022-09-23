package Rules;

import java.util.LinkedList;

import Props.Mahjong;

public class TilesCheck {
	//抓出第幾張牌
	private static int whitchDoorReturn;
	private static boolean checkPass = false;
	static int what; //如果是順子是三種狀況下哪一種
	
	//----------檢查手牌內是否有四張牌一樣 暗槓
	public static LinkedList<Mahjong> fourTilesInHand(LinkedList<Mahjong> handTiles) {
		whitchDoorReturn = 0;
		checkPass = false;
		LinkedList<Mahjong> myMeld = new LinkedList<>(); //組成面子的列表
		//-----檢查手牌內的牌是否能槓
		for( int i = 0 ; i < handTiles.size() ; i++ ) {
			if(i>handTiles.size()-4) {break;}
			boolean check1 = handTiles.get(i).tilesCode == handTiles.get(i+1).tilesCode;
			boolean check2 = handTiles.get(i).tilesCode == handTiles.get(i+2).tilesCode;
			boolean check3 = handTiles.get(i).tilesCode == handTiles.get(i+3).tilesCode;
			if( check1 && check2 && check3 ) {
				myMeld.add(handTiles.get(i));
				myMeld.add(handTiles.get(i+1));
				myMeld.add(handTiles.get(i+2));
				myMeld.add(handTiles.get(i+3));
				whitchDoorReturn = i;
				checkPass = true;
				break;
			}
		}
//		//新增後回傳一個面子清單
		return myMeld;
	}

	
	public int FTIDoorRemove() {
		return whitchDoorReturn;
	}
	
	//----------檢查手牌與拿到的牌是否有四張牌一樣 暗槓
	public static LinkedList<Mahjong> tempKongInHand(LinkedList<Mahjong> handTiles,Mahjong temp){
		whitchDoorReturn = 0;
		checkPass = false;
		LinkedList<Mahjong> myMeld = new LinkedList<>(); //組成面子的列表
		//-----檢查手牌內的牌是否能槓
		for( int i = 0 ; i < handTiles.size() ; i++ ) {
			if(i>handTiles.size()-3) {break;}
			boolean check1 = temp.tilesCode == handTiles.get(i).tilesCode;
			boolean check2 = temp.tilesCode == handTiles.get(i+1).tilesCode;
			boolean check3 = temp.tilesCode == handTiles.get(i+2).tilesCode;
			if( check1 && check2 && check3 ) {
				temp.tileInitPlayerLeft();
				myMeld.add(temp);
				myMeld.add(handTiles.get(i));
				myMeld.add(handTiles.get(i+1));
				myMeld.add(handTiles.get(i+2));
				whitchDoorReturn = i;
				checkPass = true;
				break;
			}
		}
//		//新增後回傳一個面子清單
		return myMeld;
	}

	public int TKIDoorRemove() {
		return whitchDoorReturn;
	}
	
	
	//----------檢查手牌與其他人打出的牌是不是共有四張一樣 明槓
	
	//----------檢查手牌與拿到的牌是否有四張牌一樣 明槓
	public static LinkedList<Mahjong> lightKong(LinkedList<Mahjong> handTiles, Mahjong lostTile){
		whitchDoorReturn = 0;
		checkPass = false;
		LinkedList<Mahjong> myMeld = new LinkedList<>(); //組成面子的列表
		//-----檢查手牌內的牌是否能槓
		for( int i = 0 ; i < handTiles.size() ; i++ ) {
			if(i>handTiles.size()-3) {break;}
			boolean check1 = lostTile.tilesCode == handTiles.get(i).tilesCode;
			boolean check2 = lostTile.tilesCode == handTiles.get(i+1).tilesCode;
			boolean check3 = lostTile.tilesCode == handTiles.get(i+2).tilesCode;
			if( check1 && check2 && check3 ) {
				lostTile.tileSwitchLeft(lostTile.tilesCode);
				myMeld.add(lostTile);
				myMeld.add(handTiles.get(i));
				myMeld.add(handTiles.get(i+1));
				myMeld.add(handTiles.get(i+2));
				whitchDoorReturn = i;
				checkPass = true;
				break;
			}
		}
//		//新增後回傳一個面子清單
		return myMeld;
	}
	
	//----返還lightKong方法檢查是否通過
	public boolean LKB() {
		return checkPass;
	}
	
	//----返還lightKong方法檢查出的門是哪個，供外部處理手牌
	public int LKDoorRemove() {
		return whitchDoorReturn-2;
	}
	
	
	
	
	
	//----------檢查手牌與其他人打出的牌是不是共有三張一樣 刻子
	public static LinkedList<Mahjong> pong(LinkedList<Mahjong> handTiles ,Mahjong lostTile){
		whitchDoorReturn = 0;
		checkPass = false;
		LinkedList<Mahjong> myMeld = new LinkedList<>(); //組成面子的列表
		//-----檢查手牌內的牌是否能槓
		for( int i = 0 ; i < handTiles.size() ; i++ ) {
			if(i>handTiles.size()-3) {break;}
			boolean check1 = lostTile.tilesCode == handTiles.get(i).tilesCode;
			boolean check2 = lostTile.tilesCode == handTiles.get(i+1).tilesCode;
			if( check1 && check2 ) {
				lostTile.tileSwitchLeft(lostTile.tilesCode);
				myMeld.add(lostTile);
				myMeld.add(handTiles.get(i));
				myMeld.add(handTiles.get(i+1));
				whitchDoorReturn = i;
				checkPass = true;
				break;
			}
		}
//		//新增後回傳一個面子清單
		return myMeld;
	}
	
	
	//----返還pong方法檢查是否通過
	public boolean PB() {
		return checkPass;
	}
	
	//----返還pong方法檢查出的門是哪個，供外部處理手牌
	public int PDoorRemove() {
		return whitchDoorReturn-2;
	}
	
	
	
	
	
	//----------檢查上家打出的牌是否能與手牌內的牌組成順子
	public static LinkedList<Mahjong> chow(LinkedList<Mahjong> handTiles ,Mahjong lostTile){
		whitchDoorReturn = 0;
		checkPass = false;
		LinkedList<Mahjong> myMeld = new LinkedList<>(); //組成面子的列表
		//-----檢查手牌內的牌
		for( int i = 0 ; i < handTiles.size() ; i++ ) {
			if(i>handTiles.size()-3) {break;}
			if(lostTile.tilesCode >= 25) {break;}
			if(handTiles.get(i).tilesCode >= 25) {continue;}
			//--口口
			boolean eightOrNine = lostTile.tileCodeArray[1]==8 || lostTile.tileCodeArray[1]==9;
			boolean check1 = lostTile.tilesCode+1 == handTiles.get(i).tilesCode;
			boolean check2 = lostTile.tilesCode+2 == handTiles.get(i+1).tilesCode;
			//口--口
			boolean oneOrNine = lostTile.tileCodeArray[1]==1 || lostTile.tileCodeArray[1]==9;
			boolean check3 = lostTile.tilesCode-1 == handTiles.get(i).tilesCode;
			boolean check4 = lostTile.tilesCode+1 == handTiles.get(i+1).tilesCode;
			//口口--
			boolean oneOrTwo = lostTile.tileCodeArray[1]==1 || lostTile.tileCodeArray[1]==2;
			boolean check5 = lostTile.tilesCode-2 == handTiles.get(i).tilesCode;
			boolean check6 = lostTile.tilesCode-1 == handTiles.get(i+1).tilesCode;
			
			if( (check1 && check2 && !eightOrNine) || (check3 && check4 && !oneOrNine ) || (check5 && check6 && !oneOrTwo ) ) {
				lostTile.tileSwitchLeft(lostTile.tilesCode);
				myMeld.add(lostTile);
				myMeld.add(handTiles.get(i));
				myMeld.add(handTiles.get(i+1));
				whitchDoorReturn = i;
				checkPass = true;
				break;
			}
		}
//		//新增後回傳一個面子清單
		return myMeld;
	}
	
	//----返還chow方法檢查是否通過
	public boolean CB() {
		return checkPass;
	}
	
	//----返還chow方法檢查出的門是哪個，供外部處理手牌
	
	
	public int DoorRemove() {
		return whitchDoorReturn;
	}
	public int TypeIs() {
		return what;
	}
	public boolean Pass() {
		return checkPass;
	}
	
	
	public static boolean ron(LinkedList<Mahjong> handTiles ,Mahjong lostTile) {
		boolean pass = false;
		return pass;
	}
	

}





