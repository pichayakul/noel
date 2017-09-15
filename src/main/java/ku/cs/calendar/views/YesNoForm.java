package ku.cs.calendar.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ku.cs.calendar.controllers.MainController;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class YesNoForm {

	private JFrame frame;
	public MainController controller;
	public boolean answer;
	public int selectedObjectIndex;
	public YesNoForm(MainController controller) {
		this.controller = controller;
		initialize();
	}
	public void show()
	{
		this.frame.setVisible(true);
	}
	public void hide()
	{
		this.frame.setVisible(false);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(this.controller.getAppointView().getFrame().getX(),this.controller.getAppointView().getFrame().getY(), 200, 120);
		frame.setVisible(false);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getCalendar().deleteAppointment(getSelectedObjectIndex());
				controller.getMainView().updateUI();
				controller.getAppointView().updateUI();
				hide();
			}
		});
		btnNewButton.setBackground(Color.RED);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Delete appointment Sure!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}
	public void setSelectedObjectIndex(int index)
	{
		selectedObjectIndex = index;
		
	}
	public int getSelectedObjectIndex()
	{
		return this.selectedObjectIndex;
	}
}
