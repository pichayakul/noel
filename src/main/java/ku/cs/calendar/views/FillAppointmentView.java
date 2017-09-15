package ku.cs.calendar.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import ku.cs.calendar.controllers.MainController;
import ku.cs.calendar.models.Appointment;
import ku.cs.calendar.test.models.AppointmentTest;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class FillAppointmentView {

	private JFrame frmInformation;
	public JTextArea detailField;
	private JTextField dateField;
	public MainController controller;
	private JTextField titleField;
	public JPanel panel;
	public JButton btnEdit;
	public JButton btnSave;
	public JComboBox hoursComboBox;
	public JComboBox minutesComboBox;
	public JComboBox typeComboBox;
	public String buttonName;
	public FillAppointmentView(MainController controller) {
		this.controller = controller;
		initialize();
	}

	public JComboBox getTypeField() {
		return this.typeComboBox;
	}
	private void initialize() {
		Date date =new Date();
		frmInformation = new JFrame();
		frmInformation.setTitle("Information");
		frmInformation.setBounds(700, 100, 400, 400);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInformation.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInformation.setVisible(false);
		frmInformation.setResizable(false);
		JPanel panel_1 = new JPanel();
		frmInformation.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ArrayList<Appointment> list = controller.getCalendar().getAppointmentList();
				int index = controller.appointmentView.getIndex();
				if (getTitleField().getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "check your time and title");
				}
				Appointment appointment = controller.getCalendar().getAppointmentList().get(index);
				String detail = controller.getFillAppointmentView().getDetailField().getText();
				String hours = controller.getFillAppointmentView().getHoursComboBox().getSelectedItem()+"";
				String minutes = controller.getFillAppointmentView().getMinutesComboBox().getSelectedItem()+"";
				String time =hours+":"+minutes;
				String titleOld = getButtonName();
				String title = controller.getFillAppointmentView().getTitleField().getText();
				String type = controller.getFillAppointmentView().getTypeField().getSelectedItem()+"";
				controller.getCalendar().editAppointment(appointment,time, detail, title,type,titleOld);
				JOptionPane.showMessageDialog(null, "Edit Complete");
				controller.getFillAppointmentView().disappear();
				controller.getAppointView().updateUI();
			}
		});
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.EAST);
		
		JLabel lblTimehhmm = new JLabel("Time : (HH:MM)");
		lblTimehhmm.setFont(lblTimehhmm.getFont().deriveFont(lblTimehhmm.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 11f));
		panel_3.add(lblTimehhmm);
		
		String[] hoursList = new String[25];
		for (int i=0;i<=24;i++)
		{
			hoursList[i] = i+"";
		}
		hoursComboBox = new JComboBox(hoursList);
		
		panel_3.add(hoursComboBox);
		String[] minutesList = new String[60];
		for (int i=0;i<=59;i++)
		{
			minutesList[i] = i+"";
		}
		minutesComboBox = new JComboBox(minutesList);
		panel_3.add(minutesComboBox);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.WEST);
		
		JLabel lblDate = new JLabel("Date : ");
		lblDate.setFont(lblDate.getFont().deriveFont(lblDate.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 11f));
		panel_4.add(lblDate);
		
		dateField = new JTextField();
		panel_4.add(dateField);
		dateField.setColumns(10);
		dateField.setEditable(false);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		JLabel lblTitle = new JLabel("Title :");
		panel_5.add(lblTitle);
		
		titleField = new JTextField();
		panel_5.add(titleField);
		titleField.setColumns(10);
		
		String[] typeList = new String[] {"Normal","Daily","Weekly","Monthly"};
		typeComboBox = new JComboBox(typeList);
		panel_5.add(typeComboBox);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JLabel lblDetail = new JLabel("Detail :");
		panel_6.add(lblDetail);
		
		panel = new JPanel();
		frmInformation.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller.getCalendar().isSameTitle(getTitleField().getText()))
				{
					JOptionPane.showMessageDialog(null,"This title it has already");
				}
				else if (getTitleField().getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "check your time and title");
				}
				else
				{
					controller.getCalendar().addAppointment(getDateField().getText(),getHoursComboBox().getSelectedItem()+":"+getMinutesComboBox().getSelectedItem(), getDetailField().getText(),getTypeField().getSelectedItem()+"", getTitleField().getText());
					disappear();
					JOptionPane.showMessageDialog(null, "Save Complete");
//					controller.getCalendar().matchAppointment("","");
					controller.getMainView().updateUI();
				}
			}
		});
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		detailField = new JTextArea();
		frmInformation.getContentPane().add(detailField, BorderLayout.CENTER);
	}
	public void disappear() {
		// TODO Auto-generated method stub
		frmInformation.setVisible(false);
	}

	public JPanel getPanel() {
		return panel;
	}
	public JTextField getTitleField()
	{
		return titleField;
	}
	public JTextArea getDetailField()
	{
		return detailField;
	}
	public JTextField getDateField()
	{
		return dateField;
	}
	public JComboBox getHoursComboBox()
	{
		return this.hoursComboBox;
	}
	public JComboBox getMinutesComboBox()
	{
		return this.minutesComboBox;
	}

	public void setDate(String date) {
		// TODO Auto-generated method stub
		this.getDateField().setText(date);
		
	}

	public void display() {
		// TODO Auto-generated method stub
		frmInformation.setVisible(true);
	}
	public JButton getBtnEdit()
	{
		return this.btnEdit;
	}
	public JButton getBtnOK()
	{
		return this.btnSave;
	}
	public void showEditBtn() {
		// TODO Auto-generated method stub
		this.getPanel().add(this.getBtnEdit());
	}
	public void hideEditBtn()
	{
		this.getPanel().remove(this.getBtnEdit());
	}
	public void hideOKBtn()
	{
		this.getPanel().remove(this.getBtnOK());
	}
	public void addOKBtn()
	{
		this.getPanel().add(this.getBtnOK());
	}

	public void selectedButtonName(String title) {
		// TODO Auto-generated method stub
		this.buttonName = title;
	}
	public String getButtonName()
	{
		return this.buttonName;
	}
}
