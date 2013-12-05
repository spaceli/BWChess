/*
 * MainFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import model.GameModel;

import controller.*;

/**
 * 主界面
 * @author 
 */
public class MainFrame extends javax.swing.JFrame {

	public static final int LEFT_TOP_DISTENCE_INLABEL = 35;//棋盘图片左边和上边的空白距离
	public static final int RIGHT_BOTTOM_DISTENCE_INLABEL = 420;//棋盘的宽度和高度
	public static final int WHITH_HEIGHT_OF_GRID = 48;//每个方格的长宽

	private GameController gameController;//游戏控制器，界面点击会传给控制器，由它来处理点击
	private DefaultListModel listModel; //列表框的数据源

	/** Creates new form MainFrame */
	public MainFrame(GameController gc) {
		initComponents();
		gameController = gc;
		this.setLocation(300, 200);
		this.updateComponents(); 
	}

	/**
	 * 更新界面
	 */
	public void updateComponents() {
		this.repaint(); //通知所有组件重画自己
		listModel = new DefaultListModel();//创建一个新的数据源
		stepList.setModel(listModel);//设置列表框的数据源
		for (GameModel g : gameController.stepStack) { //遍历走棋记录的栈
			listModel.addElement(g.toString());//将每一步棋盘信息加入列表框数据源
		}
		
		if(gameController.getGameModel() != null){ //判断是否有棋盘模型
			blackCountLabel.setText(String.valueOf(gameController.getGameModel()
					.getBlackCount()));//更新显示黑棋数标签
			whiteCountLabel.setText(String.valueOf(gameController.getGameModel()
					.getWhiteCount()));//更新显示白棋数标签
			
			infoLabel.setText(gameController.getGameInfo());//更新棋局信息
		}
		
		
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	/**
	 * 初始化游戏界面
	 */
	private void initComponents() {

		boardLabel = new MyLabel();//显示棋盘的Label是JLabel类的子类，重新实现其paint方法。
		jLabel2 = new javax.swing.JLabel();
		blackCountLabel = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		whiteCountLabel = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		stepList = new javax.swing.JList();
		infoLabel = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u9ed1\u767d\u68cb");
		setResizable(false);

		boardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/backgroundImage.jpg"))); // NOI18N
		boardLabel.setText("jLabel1");
		boardLabel.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.LOWERED));
		boardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				boardLabelMousePressed(evt);
			}
		});

		jLabel2.setText("\u9ed1\u68cb\u6570");

		blackCountLabel.setText("0");

		jLabel4.setText("\u767d\u68cb\u6570");

		whiteCountLabel.setText("0");

		stepList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				stepListMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(stepList);

		jMenu1.setText("\u6e38\u620f");

		jMenuItem1.setText("\u65b0\u6e38\u620f");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("\u9009\u9879");
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												boardLabel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												455,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel4))
																		.addGap(
																				36,
																				36,
																				36)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								whiteCountLabel)
																						.addComponent(
																								blackCountLabel)))
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																151,
																Short.MAX_VALUE)
														.addComponent(
																infoLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																boardLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																455,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				381,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				infoLabel)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				32,
																				Short.MAX_VALUE)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								blackCountLabel))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								whiteCountLabel))))
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents
	
	/**
	 * 步骤列表框的鼠标点击响应
	 */
	private void stepListMouseClicked(java.awt.event.MouseEvent evt) {
		if(evt.getClickCount() == 2){//只捕获双击事件
			int index = stepList.getSelectedIndex();//找到双击的索引
			gameController.backToStep(index);//回到那一步
			updateComponents();//更新界面
		}
		
	}

	//GEN-END:initComponents

	/**
	 * 棋盘的鼠标响应
	 */
	private void boardLabelMousePressed(java.awt.event.MouseEvent evt) {
		//获取点击相对于棋盘的坐标
		int x = evt.getX();
		int y = evt.getY();

		if (x < LEFT_TOP_DISTENCE_INLABEL || y < LEFT_TOP_DISTENCE_INLABEL
				|| x > RIGHT_BOTTOM_DISTENCE_INLABEL
				|| y > RIGHT_BOTTOM_DISTENCE_INLABEL) {
			//点击不在格子内，不做响应 
		} else {//转换坐标，调用游戏控制器处理
			gameController.dealClick((x - LEFT_TOP_DISTENCE_INLABEL)
					/ WHITH_HEIGHT_OF_GRID, (y - LEFT_TOP_DISTENCE_INLABEL)
					/ WHITH_HEIGHT_OF_GRID,null);

		}
	}
	
	/**
	 * 设置按钮的点击响应
	 * @param evt
	 */
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		new SettingFrame(gameController).setVisible(true);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel blackCountLabel;
	private javax.swing.JLabel boardLabel;
	private javax.swing.JLabel infoLabel;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList stepList;
	private javax.swing.JLabel whiteCountLabel;

	// End of variables declaration//GEN-END:variables
	/**
	 * JLabel的子类，目的是重写paint方法，让它知道怎么画出棋子
	 */
	private class MyLabel extends JLabel {
		@Override
		public void paint(Graphics g) {//重写父类的paint方法

			super.paint(g);
			
			int m[][] = gameController.getGameModel().getModel();//获取保存棋盘数据的二维数组
			for (int i = 0; i < m.length; i++) {//遍历
				for (int j = 0; j < m[i].length; j++) {
					if (m[i][j] != 0) {
						if (m[i][j] == 1) {//1为黑棋
							g.setColor(Color.black);//设置画笔颜色
						} else if (m[i][j] == -1) {//-1为白色
							g.setColor(Color.WHITE);
						} else if (m[i][j] == 2){//2为可放置的点
							g.setColor(Color.RED);//设置为红色
						}
						//计算绘图原点坐标（相对于Label左上角）
						int dx = (int) (WHITH_HEIGHT_OF_GRID * i)
								+ LEFT_TOP_DISTENCE_INLABEL + 2;
						int dy = (int) (j * WHITH_HEIGHT_OF_GRID)
								+ LEFT_TOP_DISTENCE_INLABEL + 2;
						if(m[i][j] != 2){//绘制棋子，原点为左上角
							g.fillOval(dx, dy, WHITH_HEIGHT_OF_GRID - 4,
								WHITH_HEIGHT_OF_GRID - 4);
						}else {//绘制可落子的点
							g.fillOval(dx, dy, 10, 10);
						}
					}
				}
			}
		}
	}

}