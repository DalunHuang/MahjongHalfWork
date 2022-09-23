package Rules;

public final class PointCheck {
	// 計算總分 收集前兩個類別給的總分
	// 接收勝負資訊並 計算贏家獲得點數 計算輸家支付點數
	private static int TurnNums = 0; // 翻數
	private static int Fu = 0;// 符數
	private static int WinnerPoint = 0;// 贏得點數
	private static int BasicPoint = 0;// 基本點
	static int BasicManGon = 2000;// 滿貫基本點
	static int BasicUpManGon = 2000;// 切上滿貫基本點
	static int BasicJump = 3000;// 跳滿基本點
	static int BasicTwoJump = 4000;// 倍滿基本點
	static int BasicThreeJump = 6000;// 三倍滿基本點
	static int BasicFinalJump = 8000;// 役滿基本點
	static int Total = 0;// 計算結果

	// 建構式
	public PointCheck(boolean WinnerOrLoser) {
		if (WinnerOrLoser) {
			// 贏家點數計算
			Total = HowMuchWin(true, 13, 0);
		} else {
			// 輸家點數計算
			Total = HowMuchSholdPay(true, true, 0);
		}
	}

	// 模組
	// 回覆計算結果
	static int getPoint() {
		return Total;
	}

	// 贏多少
	// 需要的資訊: 贏的是莊家還是閒家 翻數 符數
	// 回復:贏的分數
	int HowMuchWin(boolean BigOrSmail, int TurnNumsGet, int FuGet) {
		if (BigOrSmail) { // 如果是莊家
			switch (TurnNumsGet) {
			case 1:
				switch (FuGet) {
				case 30:WinnerPoint = 1500;break;case 40:WinnerPoint = 2000;break;
				case 50:WinnerPoint = 2400;break;case 60:WinnerPoint = 2900;break;
				case 70:WinnerPoint = 3400;break;case 80:WinnerPoint = 3900;break;
				case 90:WinnerPoint = 4400;break;case 100:WinnerPoint = 4800;break;
				case 110:WinnerPoint = 5300;break;default:break;}break;
			case 2:
				switch (FuGet) {
				case 20:WinnerPoint = 2100;break;case 25:WinnerPoint = 2400;break;
				case 30:WinnerPoint = 2900;break;case 40:WinnerPoint = 3900;break;
				case 50:WinnerPoint = 4800;break;case 60:WinnerPoint = 5800;break;
				case 70:WinnerPoint = 6800;break;case 80:WinnerPoint = 7700;break;
				case 90:WinnerPoint = 8700;break;case 100:WinnerPoint = 9600;break;
				case 110:WinnerPoint = 10600;break;default:break;}break;
			case 3:
				switch (FuGet) {
				case 20:WinnerPoint = 3900;break;case 25:WinnerPoint = 4800;break;
				case 30:WinnerPoint = 5800;break;case 40:WinnerPoint = 7700;break;
				case 50:WinnerPoint = 9600;break;case 60:WinnerPoint = 11600;break;
				default:if (FuGet > 60) {WinnerPoint = 12000;}break;}break;
			case 4:
				switch (FuGet) {
				case 20:WinnerPoint = 7800;break;case 25:WinnerPoint = 9600;break;
				case 30:WinnerPoint = 11600;break;
				default:if (FuGet > 30) {WinnerPoint = 12000;}break;}break;
			case 5:WinnerPoint = 12000;break;case 6:WinnerPoint = 18000;break;
			case 7:WinnerPoint = 18000;break;case 8:WinnerPoint = 24000;break;
			case 9:WinnerPoint = 24000;break;case 10:WinnerPoint = 24000;break;
			case 11:WinnerPoint = 36000;break;case 12:WinnerPoint = 36000;break;
			case 13:WinnerPoint = 48000;break;default:break;}
		} else { // 如果是閒家
			switch (TurnNumsGet) {
			case 1:
				switch (FuGet) {
				case 30:WinnerPoint = 1000;break;case 40:WinnerPoint = 1300;break;
				case 50:WinnerPoint = 1600;break;case 60:WinnerPoint = 2000;break;
				case 70:WinnerPoint = 2300;break;case 80:WinnerPoint = 2600;break;
				case 90:WinnerPoint = 2900;break;case 100:WinnerPoint = 3200;break;
				case 110:WinnerPoint = 3600;break;default:break;}break;
			case 2:
				switch (FuGet) {
				case 20:WinnerPoint = 1500;break;case 25:WinnerPoint = 1600;break;
				case 30:WinnerPoint = 2000;break;case 40:WinnerPoint = 2600;break;
				case 50:WinnerPoint = 3200;break;case 60:WinnerPoint = 3900;break;
				case 70:WinnerPoint = 4500;break;case 80:WinnerPoint = 5200;break;
				case 90:WinnerPoint = 5800;break;case 100:WinnerPoint = 6400;break;
				case 110:WinnerPoint = 7100;break;default:break;}break;
			case 3:
				switch (FuGet) {
				case 20:WinnerPoint = 2800;break;case 25:WinnerPoint = 3200;break;
				case 30:WinnerPoint = 3900;break;case 40:WinnerPoint = 5200;break;
				case 50:WinnerPoint = 6400;break;case 60:WinnerPoint = 7700;break;
				default:if (FuGet > 60) {WinnerPoint = 8000;}break;}break;
			case 4:
				switch (FuGet) {
				case 20:WinnerPoint = 5200;break;case 25:WinnerPoint = 6400;break;
				case 30:WinnerPoint = 7700;break;
				default:if (FuGet > 30) {WinnerPoint = 8000;}break;}break;
			case 5:WinnerPoint = 8000;break;case 6:WinnerPoint = 12000;break;
			case 7:WinnerPoint = 12000;break;case 8:WinnerPoint = 16000;break;
			case 9:WinnerPoint = 16000;break;case 10:WinnerPoint = 16000;break;
			case 11:WinnerPoint = 24000;break;case 12:WinnerPoint = 24000;break;
			case 13:WinnerPoint = 32000;break;default:break;}
		}
		return WinnerPoint;
	}

	// 基本點，基本點是「閒家自摸時別的閒家支付的點數」。利用基本點，可以計算各種情況下各家的支付點數
	int BasicPointUpDate(int HowMuchTurn, int HowMuchFu) {
		Fu = HowMuchFu;
		TurnNums = HowMuchTurn;
		// 基本點 = 符數+2^(翻數+2)
		BasicPoint = Fu + (int) Math.pow(2, TurnNums + 2);
		// 滿貫：5台，或者4台但算出的基本點大於2000時，基本點固定為2000。
		if (TurnNums == 5 || (BasicPoint > 2000 || BasicPoint < 3000)) {
			BasicPoint = BasicManGon;
		}
		// 切上滿貫：對於30符4台或者60符3台的情況，基本點是1920點，閒家7700點・莊家11600點。這和滿貫的8000點・12000點非常接近，所以有時候按照滿貫處理。
		if ((Fu == 30 && TurnNums == 4) || (Fu == 60 && TurnNums == 3)) {
			BasicPoint = BasicManGon;
		}
		// 跳滿：6～7台，基本點3000。
		if (TurnNums == 6 || TurnNums == 7) {
			BasicPoint = BasicJump;
		}
		// 倍滿： 8～10台，基本點4000。
		if (TurnNums == 8 || TurnNums == 9 || TurnNums == 10) {
			BasicPoint = BasicTwoJump;
		}
		// 三倍滿：11～12台，基本點6000。
		if (TurnNums == 11 || TurnNums == 12) {
			BasicPoint = BasicThreeJump;
		}
		// 役滿：一些高難度的役叫做役滿，在達到13台以上也是役滿，基本點8000。
		if (TurnNums >= 13) {
			BasicPoint = BasicFinalJump;
		}
		return BasicPoint;
	}

	// 各自負擔額，胡了時其他玩家支付的點數如下決定。另外，不夠100點的部分進到100點。
	// 需要的資訊 : 胡牌的是莊家還是閒家 是不是自摸 基本點是多少
	// 回復的資訊 : 要支付多少
	int HowMuchSholdPay(boolean isBigHome, boolean isTakeAll, int WhatBasicPoint) {
		int PointUp = WhatBasicPoint;
		if (isBigHome) { // 是莊家
			if (isTakeAll) { // 是，莊家的自摸胡了：其餘三人每人支付2倍基本點。
				PointUp *= 2;
			} else { // 不是，莊家的榮胡：點炮者支付6倍基本點。
				PointUp *= 6;
			}
		} else { // 是閒家
			if (isTakeAll) { // 閒家的自摸胡了
				if (isBigHome) { // 莊家支付2倍基本點，剩下兩名閒家每人支付基本點
					PointUp *= 2;
				}
			} else { // 閒家的榮胡：點炮者支付4倍基本點。
				PointUp *= 4;
			}
		}
		return PointUp;
	}

	// 換算加分基準，接收的東西: 面子 將牌 胡牌時的

	int HowMuchFu(String[] one, String[] two, String[] three, String[] four, String[] five) {
		int FuMath = Fu;
		int MianZe = 0;
		int Johntiles = 0;
		boolean tempTrue = true;
		boolean tempFalse = false;
		boolean SevenTwince = true;
		boolean OpenFlower = true;
		boolean OneTwoThreeRon = true;
		boolean GamePassIsNotOK = false;
		FuMath += 20; // 符底 20符
		if (tempTrue) { // 門前清 10符
			FuMath += 10;
		}
		if (tempTrue) {// 自摸2符 可得到2符。平胡和嶺上開花是否計符需要在遊戲前聲明
			FuMath += 2;
			if (SevenTwince) { // 七對子不計自摸的2符
				FuMath -= 2;
			}
			if ((OpenFlower || OneTwoThreeRon) && GamePassIsNotOK) {
				FuMath -= 2;
			}
		}
		// 聽牌加分基準
		// 面或將最後一個完成的，取得清單最後一個狀態以及最後摸到的牌
		// 最後的牌
		int Listlenth; // 和牌的長度
		String LastCard = null; // 暫代最後一隻牌
		String[] LastString = { null, null, null, null }; // 暫代最後一組牌和牌前的樣子
		boolean OneIsThis = true;
		// 判斷是哪一種聽牌

		FuMath += 0;// 兩面聽牌 雙碰聽牌 0符
		// 兩面: X三萬四萬X 等二萬五萬
		// 雙碰: 六索六索X 八筒八筒X 等六索八筒

		// 嵌張聽牌 邊獨聽牌 單騎聽牌 2符
		if (LastString[1] == LastCard && LastString[3] == null) { // 嵌張: 六筒x八筒 等七筒
			FuMath += 2;
		}
		if ((LastString[0] == LastCard || LastString[2] == LastCard) && LastString[3] == null) {
			// 邊獨: x八萬九萬
			FuMath += 2;
		}
		if (LastString[0] == LastCard && LastString[3] == null && LastString[2] == null) {
			// 單騎: 三索x 三索
			FuMath += 2;
		}

		// 面子累計
		// 將牌累計

		// 將牌加分基準
		String[] testUse = { null, null, null, null }; //
		String Master = "東"; // 莊家的風
		String MyWind = "南"; // 我的風
		String Wind = "東"; // 場風
		// 自風牌 場風牌 三元牌 2符 數牌 客風牌 0符
		if (testUse[0] == MyWind) { // 自風牌
			FuMath += 2;
		}
		if (testUse[0] == Wind) { // 場風牌
			FuMath += 2;
		}
		if (testUse[0] == "紅" || testUse[0] == "白" || testUse[0] == "青") { // 三元牌
			FuMath += 2;
		}
		if (MyWind == Wind && testUse[0] == MyWind) {
			FuMath += 4;// 連風牌 4符(東風場東家) 或 者2符(東風場南家)
		} else {
			FuMath += 2;
		}
		// 面子加分基準
		if (SwiteCover(testUse)) {
			// 順子 0符
			FuMath += 0;
		}
		if (PonCover(testUse)) {
			if (testUse[0].substring(0) == "一" || testUse[0].substring(0) == "九") {
				// 刻槓 中張*1 么九*2
				FuMath += 2;
			} else if (testUse[0].substring(0) != "東" && testUse[0].substring(0) != "南"
					&& testUse[0].substring(0) != "西" && testUse[0].substring(0) != "北"
					&& testUse[0].substring(0) != "紅" && testUse[0].substring(0) != "白"
					&& testUse[0].substring(0) != "青") {
				FuMath += 1;
			} else {
				FuMath += 0;
			}
		}

		boolean TilesIsLight = true;
		boolean TilesNotLight = false;
		// 明刻子 2符
		if (PonCover(testUse) && TilesIsLight) {
			FuMath += 2;
		}
		// 暗刻子 4符
		if (PonCover(testUse) && TilesNotLight) {
			FuMath += 4;
		}
		// 明槓子 8符
		if (GonCover(testUse) && TilesIsLight) {
			FuMath += 8;
		}
		// 暗槓子 16符
		if (GonCover(testUse) && TilesNotLight) {
			FuMath += 16;
		}

		// 計算結果
		return FuMath;
	}

	boolean SwiteCover(String[] aa) {
		// "判斷順子";
		return true;
	}

	boolean PonCover(String[] aa) {
		// "判斷碰";
		return true;
	}

	boolean GonCover(String[] aa) {
		// "判斷槓";
		return true;
	}

	boolean JianCover(String[] aa) {
		// "判斷將";
		return true;
	}

}



