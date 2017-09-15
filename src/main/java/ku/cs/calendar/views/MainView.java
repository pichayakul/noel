package ku.cs.calendar.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ku.cs.calendar.controllers.MainController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class MainView {

	private JFrame frmCalendar;
	public JButton[] btnNewButtonList;
	public JComboBox monthBox;
	public JComboBox yearBox;
	public GregorianCalendar calenda;
	public MainController controller;
	public ArrayList<String> dayList;
	public String[] months;
	public String[] years;
	public MainView(MainController controller) {
		this.controller = controller;
		initialize();
	}
	public JFrame getFrame()
	{
		return this.frmCalendar;
	}
	public void initialize() {
		frmCalendar = new JFrame();
		frmCalendar.setTitle("Calendar");
		frmCalendar.setBounds(100, 100, 549, 406);
		frmCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalendar.setVisible(true);
		frmCalendar.setResizable(false);
		
		JPanel panel = new JPanel();
		frmCalendar.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Appointment List");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.getAppointView().updateUI();
				controller.getAppointView().display(true);
			}
		});
		
		panel.add(btnNewButton, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		Date date = new Date();
		calenda = new GregorianCalendar(2017, 7, 1);
		
		int year = Integer.parseInt(date.toString().split(" ")[5])-5;
		years = new String[11];
		for (int i=0;i<=10;i++)
		{
			years[i] = year+i+"";
		}
		
		months = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		monthBox = new JComboBox(months);
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		
		panel_2.add(monthBox);
		yearBox = new JComboBox(years);
		yearBox.setSelectedItem(date.toString().split(" ")[5]);
		yearBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		panel_2.add(yearBox);
		
		JPanel panel_1 = new JPanel();
		frmCalendar.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(7, 7, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Sunday");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel_2 = new JLabel("Monday");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Tuesday");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Wednesday");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Thursday");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Friday");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Saturday");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_6);
		
		
		panel_1.add(lblNewLabel_7);
		
		
		btnNewButtonList = new JButton[42];
		for (int i=0;i<42;i++)
		{
			btnNewButtonList[i] = new JButton();
			btnNewButtonList[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clickedButton = (JButton) e.getSource();
					String date = clickedButton.getText() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem();
					controller.getFillAppointmentView().setDate(date);
					controller.getFillAppointmentView().addOKBtn();
					controller.getFillAppointmentView().hideEditBtn();
					controller.getFillAppointmentView().display();
					controller.getFillAppointmentView().getDetailField().setText("");
					controller.getFillAppointmentView().getHoursComboBox().setSelectedIndex(0);
					controller.getFillAppointmentView().getMinutesComboBox().setSelectedIndex(0);
					controller.getFillAppointmentView().getTitleField().setText("");
				}
			});
			panel_1.add(btnNewButtonList[i]);
		}
		this.updateUI();
	}
	public ArrayList<String> getDayList()
	{
		return dayList;
	}
	public void markAppointment(JButton button)
	{
		button.setBackground(Color.RED);
	}
	public void updateUI()
	{
		calenda.set(Integer.parseInt(yearBox.getSelectedItem()+""), monthBox.getSelectedIndex(), 1);
		String[] dateForm = (calenda.getTime()+"").split(" ");
		int day=1;
		boolean start = false;
		String firstDay = dateForm[0];
		dayList = new ArrayList<>();
		dayList.add("Sun");
		dayList.add("Mon");
		dayList.add("Tue");
		dayList.add("Wed");
		dayList.add("Thu");
		dayList.add("Fri");
		dayList.add("Sat");
		int maxDayMonth= calenda.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i=0;i<42;i++)
		{
		
			if (i==dayList.indexOf(firstDay))
			{
				start = true;
			}
			if (start)
			{
				btnNewButtonList[i].setText(day+"");
				String date = day+" "+this.monthBox.getSelectedItem()+" "+this.getYearBox().getSelectedItem();
				if (controller.getCalendar().canMarkDay(date))
				{
					btnNewButtonList[i].setBackground(Color.RED);
				}
				else
				{
					btnNewButtonList[i].setBackground(null);
				}
				btnNewButtonList[i].setEnabled(true);
				day++;
				if (day>maxDayMonth)
				{
					start = false;
				}
			}
			else
			{
				btnNewButtonList[i].setBackground(null);
				btnNewButtonList[i].setEnabled(false);
				btnNewButtonList[i].setText("");
			}
			
		}
	}
	
	public JComboBox getYearBox()
	{
		return this.yearBox;
	}
	public JComboBox getMonthBox()
	{
		return this.monthBox;
	}

}
