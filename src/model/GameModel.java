package model;

import java.awt.Point;

/**
 * 棋局模型
 * @author Administrator
 *
 */
public class GameModel {
	private int[][] model = new int[8][8];//真正记录棋局数据的数组
	private Point checkPoint; //落子点
	private int blackCount = 0; //黑棋数
	private int whiteCount = 0; //白棋数
	
	public GameModel(boolean beginGame){
		if(beginGame){ //初始化棋局
			model[3][3] = 1; //black 
			model[4][4] = 1;
			model[3][4] = -1; //white
			model[4][3] = -1;
			blackCount = 2;
			whiteCount = 2;
			checkPoint = new Point(4,3);
		}
		//beginGame == false时，只构造空对象，不初始化数据
	}
	
	/**
	 * copy构造器，用于复制对象，而不是引用
	 * @param gm
	 */
	public GameModel(GameModel gm){
		for(int i=0;i<model.length;i++){ //复制数组
			for(int j=0;j<model[i].length;j++){
				model[i][j] = gm.model[i][j];
			}
		}
		this.checkPoint = new Point(gm.checkPoint.x,gm.checkPoint.y);//复制落子点
		this.blackCount = gm.blackCount;
		this.whiteCount = gm.whiteCount;
	}
	
	public int[][] getModel() {
		return model;
	}

	/**
	 * wOrB颜色的棋落在（x，y）点上的变化
	 * @param x 坐标x
	 * @param y 坐标y
	 * @param wOrB 棋子颜色
	 * @return 返回落子是否成功
	 */
	public boolean put(int x, int y, int wOrB) {
		
		if (!canBePut(x, y, wOrB)) //该点不可落wOrB颜色的子，返回false
			return false;
		
		for (int i = 0; i <= 7; i++) {//同样要依次检测8个方向，对合法的方向进行变换
			boolean thisOk = false;// mark if this direction is legal
			
			checkPoint = new Point(x, y);//起始检测点
			do{
				if(!changeCheckPoint(checkPoint, i)) {
					thisOk = false; //pass
					break;
				}
				if(model[checkPoint.x][checkPoint.y] + wOrB == 0){
					thisOk = true;
				}else {
					break;
				}
			}while(true);
			
			if(thisOk && model[checkPoint.x][checkPoint.y] == wOrB){
				checkPoint = new Point(x,y);
				do{	//把这个方向上对手的棋都变成自己的			
					model[checkPoint.x][checkPoint.y] = wOrB;
					changeCheckPoint(checkPoint, i);				
				}while(model[checkPoint.x][checkPoint.y] + wOrB == 0);
			}		
			
		}
		countWhiteAndBlack();//重新计算黑棋白棋数
		checkPoint = new Point(x,y); //保存落子点
		return true;
	}
	
	/**
	 * 检测wOrB颜色的棋子是否可以落在（x，y）处
	 * @param x
	 * @param y
	 * @param wOrB 落子颜色
	 * @return 成功与否
	 */
	public boolean canBePut(int x, int y,int wOrB) {
		boolean canBeDown = false;
		
		if (model[x][y] == 1 || model[x][y] == -1){//该点已有子，则不能再落子
			return false;
		}else {//没有子，就恢复成0（之前有可能被暂时标记为2，在此处恢复为0）
			model[x][y] = 0;
		}
		
		for (int i = 0; i <= 7; i++) {//check 8 directions one by one
			boolean thisOk = false;//标记这个方向是否可以
			
			Point checkP = new Point(x, y);
			do{
				if(!changeCheckPoint(checkP, i)) {//这条路出了棋盘，不可以
					thisOk = false; //pass
					break;
				}
				if(model[checkP.x][checkP.y] + wOrB == 0){//中间遇到对手的棋，标记为有可能
					thisOk = true;
				}else {//遇到自己的棋，不用再往前查了
					break;
				}
			}while(true);
			
			if(thisOk && model[checkP.x][checkP.y] == wOrB){//如果曾被标记为有可能，并且现在遇到自己的棋，证明这个方向可以
				canBeDown = true; //标记该点可以落子
				model[x][y] = 2; //can be put now
			}
			
		}

		return canBeDown;
	}
	
	/**
	 * 计算黑棋和白棋数
	 */
	public void countWhiteAndBlack(){
		blackCount = 0;
		whiteCount = 0;
		for(int i = 0;i < model.length;i++){
			for(int j = 0;j < model[i].length; j++){
				if(model[i][j] == 1) blackCount ++;
				else if(model[i][j] == -1) whiteCount ++;
			}
		}
	}
	public int getBlackCount() {
		return blackCount;
	}


	public int getWhiteCount() {
		return whiteCount;
	}

	/**
	 * 当前点沿着direction方向前进一步
	 * @param checkPoint 当前点
	 * @param direction 方向
	 * @return 前进一步是否成功
	 */
	public boolean changeCheckPoint(Point checkPoint, int direction) {
		switch (direction) {
		case 0://表示向正上方走
			checkPoint.y--;
			break;
		case 1://表示向右上方走
			checkPoint.x++;
			checkPoint.y--;
			break;
		case 2://表示向正右方走
			checkPoint.x++;
			break;
		case 3://依次顺时针
			checkPoint.x++;
			checkPoint.y++;
			break;
		case 4:
			checkPoint.y++;
			break;
		case 5:
			checkPoint.x--;
			checkPoint.y++;
			break;
		case 6:
			checkPoint.x--;
			break;
		case 7:
			checkPoint.x--;
			checkPoint.y--;
			break;
		}
		if(checkPoint.x < 0 || checkPoint.x > 7 || checkPoint.y < 0 || checkPoint.y > 7){
			//前进一步后，如果坐标已经出了棋局范围，则前进无效，返回false
			return false;
		}
		return true;//否则返回true
	}
	
	/**
	 * 得到当前棋局的落子方，落子点的颜色即落子方的颜色
	 * @return 落子方颜色
	 */
	public int getWhoPut(){
		return model[checkPoint.x][checkPoint.y];
	}
	
	/**
	 * 重写父类的toString方法，返回落子信息
	 */
	@Override
	public String toString() {
		if(checkPoint == null){
			return "";
		}
		return ((model[checkPoint.x][checkPoint.y] == 1)?"黑" : "白") + (char)(checkPoint.x + 'A') + String.valueOf(checkPoint.y + 1);
	}

	
}
