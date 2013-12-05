package controller;

import java.awt.Point;
import java.util.ArrayList;

import model.GameModel;

/**
 * 机器人类
 * @author Administrator
 *
 */
public class Robot{
	private GameController gameController;//游戏控制器
	public int color;//机器人的颜色
	public int level;//机器人的等级
	
	public Robot(GameController gc,int color,int level){
		this.gameController = gc;
		this.color = color;
		this.level = level;
	}
	
	/**
	 * 机器人下棋
	 * @param pointList
	 */
	public void play(ArrayList<Point> pointList){
		GameModel nextModel;//下完一步后的棋局模型
		GameModel nextBestModel = null;//下完一步后得到的最好的棋局模型
		int bestIndex = 0;//得到最好棋局模型的落子点在pointList中的索引
		
		for(int i=0;i<pointList.size();i++){//遍历所有可落子点
			Point p = pointList.get(i);
			nextModel = thinkNextModel(new GameModel(gameController.getGameModel()), p);//思考在p点落子后变成的棋局
			if(nextBestModel == null) nextBestModel = nextModel;
			else{
				switch(this.color){//根据机器人的颜色，判断是否是最好的棋局
				case 1: //机器人执黑子，则黑棋数越多越好
					if(nextBestModel.getBlackCount() < nextModel.getBlackCount()){
						nextBestModel = nextModel;
						bestIndex = i;
					}
					break;
				case -1://机器人执白子，则白棋数越多越好
					if(nextBestModel.getWhiteCount() < nextModel.getWhiteCount()){
						nextBestModel = nextModel;
						bestIndex = i;
					}
					break;
					
				}
			}
		}
		Point p = pointList.get(bestIndex); //遍历完所有的之后，取出最好的这个点
		gameController.dealClick(p.x, p.y, this);//调用游戏控制器来处理点击，把自己传进去，用来表示是机器人下的棋

	}
	
	/**
	 * 思考落子后得到的棋局模型
	 * @param nowModel 现在的模型
	 * @param clickPoint 落子点
	 * @return 返回落子后的棋局模型
	 */
	public GameModel thinkNextModel(GameModel nowModel,Point clickPoint){
		nowModel.put(clickPoint.x, clickPoint.y, this.color);
		
		return nowModel;
	}
}
