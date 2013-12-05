package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.GameModel;
import view.*;

/**
 * 游戏控制器
 * @author Administrator
 *
 */
public class GameController {
	
	private MainFrame mainFrame;//游戏主界面
	private GameModel gameModel;//储存棋局的数据模型
	public boolean gameOver; //标志游戏状态
	public Stack<GameModel> stepStack = new Stack<GameModel>();//保存棋局模型的栈
	ArrayList<Point> pointList = null;//临时存储可落子的点列表
	
	public Robot robot = null;//机器人
	
	private int whosTurn;
	/**
	 * @param args
	 */
	public static void main(String[] args) {//程序入口
		
		GameController gameController = new GameController();//构造游戏控制器
		gameController.showFrame();
		
	}
	/**
	 * 显示主界面
	 */
	private void showFrame(){

		gameModel = new GameModel(false);//先构造一个空的棋局模型
		gameOver = true;
		
		mainFrame = new MainFrame(this);//构造主界面
		mainFrame.setVisible(true);//显示
			
	}
	/**
	 * 开始新游戏，由设置界面调用
	 * @param createRobot 是否构造机器人
	 */
	public void newGame(boolean createRobot){
		if(createRobot){ //构造机器人
			robot = new Robot(this, -1, 1);//
			
			new Thread(new Runnable() {//开启新线程
				public void run() {
					while(!gameOver){//游戏没完就一直循环
						if(whosTurn == robot.color){//判断是否轮到机器人
							robot.play(pointList);
						}else{
							try {
								Thread.sleep(50);//休息50毫秒
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}					
				}
			}).start();
		}
		gameOver = false;
		whosTurn = 1;//黑棋先走
		gameModel = new GameModel(true);//初始化棋局模型
		stepStack.clear();//清空棋局记录
		stepStack.add(new GameModel(gameModel));
		mainFrame.updateComponents();//主界面更新
	}
	
	/**
	 * deal the click action
	 * @param x  x coordinate on board
	 * @param y  y coordinate on board
	 * @param fromWho  source of click action,if not robot,fromWho is null
	 * @return if this click legal
	 */
	public void dealClick(int x,int y,Robot fromWho){
		if(fromWho != robot && whosTurn == robot.color){//避免异常		
			return;
		}
		if(gameModel.put(x, y, whosTurn)){//当前玩家在坐标（x，y）落子是否成功
			
			if(gameModel.getBlackCount() + gameModel.getWhiteCount() == 64){//棋盘满了游戏结束
				gameOver = true;
			}
			whosTurn *= -1;	//更换玩家
			if((pointList = markAllCanPutPosition()).size() == 0){//check if next color have place to put
				//if no,current color go on
				whosTurn *= -1;
				pointList = markAllCanPutPosition();
			}
			
			stepStack.add(new GameModel(gameModel));//当前棋局入栈
			
			mainFrame.updateComponents();//notify view to update

		}
		
	}
	
	/**
	 * 标记当前玩家可以落子的所有坐标
	 * @return 返回坐标的数组
	 */
	public ArrayList<Point> markAllCanPutPosition(){
		ArrayList<Point> pointList = new ArrayList<Point>();
		int model[][] = gameModel.getModel();//获得保存棋局数据的二维数组
		
		for(int i=0;i<model.length;i++){
			for(int j=0;j<model[i].length;j++){
				if(gameModel.canBePut(i, j, whosTurn)){//该点可以落子
					pointList.add(new Point(i,j));//把该点加入数组
				}
			}
		}
		return pointList;
	}
	/**
	 * 悔棋到第index步
	 * @param index 
	 */
	public void backToStep(int index){
		gameModel = new GameModel(stepStack.get(index));
		for(int i=stepStack.size()-1;i>index;i--){//把第index步之后的棋局数据都清除
			stepStack.pop();
		}
		whosTurn = gameModel.getWhoPut() * -1;//确定下一步轮谁走
	}
	
	public GameModel getGameModel(){		
		return gameModel;
	}
	
	/**
	 * 获得棋局信息
	 * @return 返回信息的字符串
	 */
	public String getGameInfo(){
		String info = null;
		if(!gameOver){
			if(whosTurn == 1){
				info = "黑方落子";
			}else if(whosTurn == -1){
				info = "白方落子";
			}
		}else {
			info = "游戏结束";
		}
		return info;
	}
}
