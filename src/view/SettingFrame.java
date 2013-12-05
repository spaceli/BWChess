/*
 * SettingFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import controller.GameController;

/**
 * 设置界面
 * @author  __USER__
 */
public class SettingFrame extends javax.swing.JFrame {
	private GameController gameController;
	
	public SettingFrame(GameController gc) {
		initComponents();
		gameController = gc;
		this.setLocation(300,200);
	}

	
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jRadioButton1 = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		buttonGroup1.add(jRadioButton1);
		jRadioButton1.setText("\u4eba\u4eba\u5bf9\u6218");

		buttonGroup1.add(jRadioButton2);
		jRadioButton2.setText("\u4eba\u673a\u5bf9\u6218");

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("\u53d6\u6d88");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				48,
																				48,
																				48)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jRadioButton2)
																						.addComponent(
																								jRadioButton1))
																		.addGap(
																				56,
																				56,
																				56))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				okButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				cancelButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addGap(10, 10, 10)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(19, 19, 19)
										.addComponent(jRadioButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jRadioButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												200, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(okButton)
														.addComponent(
																cancelButton))
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	
	/**
	 * cancelButton action
	 */
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();//直接关闭界面
	}

	/**
	 * okButton action
	 */
	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(jRadioButton1.isSelected()){
			//two men player
			gameController.newGame(false);
		}else if(jRadioButton2.isSelected()){
			// a robot
			gameController.newGame(true);
		}
		dispose();//关闭界面
	}

	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton cancelButton;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

}