package ku.cs.calendar.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ku.cs.calendar.controllers.MainController;
import ku.cs.calendar.models.Appointment;
import ku.cs.calendar.test.models.AppointmentTest;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class AppointmentView {

	private JFrame frmAppointmentList;
	private JPanel panel_1;
	public JButton delButton;
	public static AppointmentView window;
	public MainController controller;
	public JPanel panel_2;
	public JScrollPane scroll;
	public ArrayList<Appointment> appointmentList;
	public int index;
	public JLabel lblAppointmentList;
	public int selectedButtonIndex;
	private JPanel panel_3;
	private JButton searchButton;
	private JComboBox monthComboBox;
	private JComboBox dayComboBox;
	private JComboBox yearComboBox;
	public AppointmentView(MainController controller) {
		this.controller = controller;
		initialize();
	}
	public void display(boolean s)
	{
		frmAppointmentList.setVisible(s);
	}
	public JFrame getFrame()
	{
		return this.frmAppointmentList;
	}
	private void initialize() {
		frmAppointmentList = new JFrame();
		frmAppointmentList.setTitle("Appointment List");
		frmAppointmentList.setBounds(700, 100, 300, 300);
//		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppointmentList.getContentPane().setLayout(new BorderLayout(0, 0));
		frmAppointmentList.setVisible(false);
		frmAppointmentList.setResizable(false);
		JPanel panel = new JPanel();
		frmAppointmentList.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblAppointmentList = new JLabel("Appointment List");
		lblAppointmentList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppointmentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblAppointmentList, BorderLayout.CENTER);
		
		delButton = new JButton("Edit");
		delButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (delButton.getText().equals("Edit"))
				{
					getLblAppointmentList().setText("Choose the box");
					delButton.setText("Delete");
				}
				else
				{
					getLblAppointmentList().setText("Appointment List");
					delButton.setText("Edit");
				}
			}
		});
		panel.add(delButton, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		monthComboBox = new JComboBox();
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchAppointment();
				updateUI();
			}
		});
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		panel_3.add(monthComboBox);
		
		dayComboBox = new JComboBox();
		dayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAppointment();
				updateUI();
			}
		});
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel_3.add(dayComboBox);
		Date date = new Date();
		int year = Integer.parseInt(date.toString().split(" ")[5])-5;
		String[] years = new String[11];
		for (int i=0;i<=10;i++)
		{
			years[i] = year+i+"";
		}
		yearComboBox = new JComboBox(years);
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAppointment();
				updateUI();
			}
		});
		panel_3.add(yearComboBox);
//		searchButton = new JButton("search");
//		searchButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
////				String date = 
//				String month = getMonthComboBox().getSelectedItem()+"";
//				String day = getDayComboBox().getSelectedItem()+"";
//				String year = getYearComboBox().getSelectedItem()+"";
//				String date = day + " "+month+" "+year;
////				System.out.println(date);
//				controller.getCalendar().matchAppointment(date);
//				updateUI();
//			}
//		});
//		panel_3.add(searchButton);
		
		panel_1 = new JPanel();
		frmAppointmentList.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout());		 
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0,0));
		this.createButton();
	}
	public void searchAppointment()
	{
		String month = getMonthComboBox().getSelectedItem()+"";
		String day = getDayComboBox().getSelectedItem()+"";
		String year = getYearComboBox().getSelectedItem()+"";
		String date = day + " "+month+" "+year;
		System.out.println(date);
		controller.getCalendar().matchAppointment(date);
	}
	public JComboBox getMonthComboBox(){
		return this.monthComboBox;
	}
	public JComboBox getDayComboBox(){
		return this.dayComboBox;
	}
	public JComboBox getYearComboBox(){
		return this.yearComboBox;
	}
	public JComboBox getTypeSearchComboBox()
	{
		return this.monthComboBox;
	}
	public JButton getSearchButton()
	{
		return this.searchButton;
	}
	public JButton getDelButton()
	{
		return this.delButton;
	}
	public JLabel getLblAppointmentList()
	{
		return this.lblAppointmentList;
	}
	public void createButton()
	{
		appointmentList = controller.getCalendar().getAppointmentList();
		Appointment a;
		JButton button;
		
		if (appointmentList.size()<=4)
		{
			for (int i=0;i<4;i++)
			{
				if (i < appointmentList.size()) {
					a = appointmentList.get(i);
					button = new JButton("*"+a.getType()+"* "+a.getTitle()+" -----"+a.getDate());
					button.setPreferredSize(new Dimension(frmAppointmentList.getWidth(), 40));
					panel_2.add(button);
				}
				else
				{
					button = new JButton("");
					button.setPreferredSize(new Dimension(frmAppointmentList.getWidth(), 40));
					button.setEnabled(false);
					panel_2.add(button);
				}
				
				
			}
		}
		else
		{
			for (int i=0;i<appointmentList.size();i++)
			{
				a = appointmentList.get(i);
				button = new JButton("*"+a.getType()+"* "+a.getTitle()+" -----"+a.getDate());
				button.setPreferredSize(new Dimension(frmAppointmentList.getWidth(), 40));
				System.out.println(i);
				panel_2.add(button);
			}
		}
//		controller.getCalendar().matchAppointment("GetAll", "");
		scroll = new JScrollPane(panel_2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scroll);
	}
	public JPanel getPanel_2()
	{
		return this.panel_2;
	}
	public void updateUI() {
//		appointmentList = controller.getCalendar().getAppointmentList();
		appointmentList = controller.getCalendar().getNewArray();
		Appointment a;
		Component[] list = panel_2.getComponents();
		if (appointmentList.size()<=4)
		{
			JButton c;
			String date = this.getDayComboBox().getSelectedItem()+" "+this.getMonthComboBox().getSelectedItem()+" "+this.getYearComboBox().getSelectedItem();
			for (int i=0;i<4;i++)
			{
				if (i < appointmentList.size()) {
					a = appointmentList.get(i);
					
					c = (JButton) list[i];
					
					c.setText("*"+a.getType()+"* "+a.getTitle()+" -----"+date);
					c.setEnabled(true);
					for (ActionListener n : c.getActionListeners())
					{
						c.removeActionListener(n);
					}
					c.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (getDelButton().getText().equals("Delete"))
							{
								ArrayList<Appointment> appointment = controller.getCalendar().getAppointmentList();
//								ArrayList<Appointment> appointment = controller.getCalendar().getNewArray();
								JButton button = (JButton) e.getSource();
								
								for (int i=0;i<appointment.size();i++)
								{
									if (appointment.get(i).getTitle().equals(button.getText().split(" ")[1]))
									{
										controller.getYesNoForm().show();
										selectedButtonIndex = i;
										controller.getYesNoForm().setSelectedObjectIndex(selectedButtonIndex);
//										if (controller.getYesNoForm().checkYesNo())
//										{
//											controller.getCalendar().deleteAppointment(i);
//										}

										break;
									}
								}
								
								updateUI();
							}
							else
							{
							JButton button = (JButton) e.getSource();
							getForm(button);

							}
							
						}
					});
				}
				
			}
			for (int i=0;i<this.getPanel_2().getComponentCount();i++)
			{
				System.out.println("CountPanel :"+this.getPanel_2().getComponentCount());
				System.out.println("CountList :"+appointmentList.size());
				if (i >= appointmentList.size())
				{
					list = panel_2.getComponents();
					c = (JButton) list[i];
					c.setText("");
					c.setEnabled(false);
					if (getPanel_2().getComponentCount()>4)
					{
						getPanel_2().remove(c);
					}
				}
			}
		}
		else
		{
			JButton c;
			String date = this.getDayComboBox().getSelectedItem()+" "+this.getMonthComboBox().getSelectedItem()+" "+this.getYearComboBox().getSelectedItem();
			for (int i=0;i<appointmentList.size();i++)
			{
					a = appointmentList.get(i);

					if (i>=list.length-1)
					{
						
						JButton button = new JButton("*"+a.getType()+"* "+a.getTitle()+" -----"+date);
						button.setPreferredSize(new Dimension(frmAppointmentList.getWidth(), 40));
						panel_2.add(button);
					}
					list = panel_2.getComponents();
					c = (JButton) list[i];
					c.setText("*"+a.getType()+"* "+a.getTitle()+" -----"+date);
					c.setEnabled(true);
					for (ActionListener n : c.getActionListeners())
					{
						c.removeActionListener(n);
					}
					c.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (getDelButton().getText().equals("Delete"))
							{
								ArrayList<Appointment> appointment = controller.getCalendar().getAppointmentList();
//								ArrayList<Appointment> appointment = controller.getCalendar().getNewArray();
								JButton button = (JButton) e.getSource();
								for (int i=0;i<appointment.size();i++)
								{
									if (appointment.get(i).getTitle().equals(button.getText().split(" ")[1]))
									{
										controller.getYesNoForm().show();
										selectedButtonIndex = i;
										controller.getYesNoForm().setSelectedObjectIndex(selectedButtonIndex);
//										if (controller.getYesNoForm().checkYesNo())
//										{
//											controller.getCalendar().deleteAppointment(i);
//										}
										break;
									}
								}
								
								updateUI();
								
							}
							else
							{
							JButton button = (JButton) e.getSource();
							getForm(button);
							}
						}
					});
			}
			for (int i=0;i<this.getPanel_2().getComponentCount();i++)
			{
				System.out.println("CountPanel :"+this.getPanel_2().getComponentCount());
				System.out.println("CountList :"+appointmentList.size());
				if (i >= appointmentList.size())
				{
					list = panel_2.getComponents();
					c = (JButton) list[i];
					c.setText("");
					c.setEnabled(false);
	//				if (getPanel_2().getComponentCount()>4)
	//				{
						getPanel_2().remove(c);
	//				}
				}
			}
		}	
	}
	
	public void getForm(JButton button)
	{
		index=-99;
		
		for (int i=0;i<controller.getCalendar().getAppointmentList().size();i++)
		{
//			System.out.println(controller.getCalendar().getAppointmentList().get(i).getTitle());
//			System.out.println(button.getText().split(" ")[1]);
			if (controller.getCalendar().getAppointmentList().get(i).getTitle().equals(button.getText().split(" ")[1]))
			{
				index = i;
				break;
			}
		}
		Appointment appointment = controller.getCalendar().getAppointmentList().get(index);
		controller.getFillAppointmentView().getTitleField().setText(appointment.getTitle());
//		controller.getFillAppointmentView().getDateField().setText(appointment.getDate());
		String date = this.getDayComboBox().getSelectedItem()+" "+this.getMonthComboBox().getSelectedItem()+" "+this.getYearComboBox().getSelectedItem();
		controller.getFillAppointmentView().getDateField().setText(date);
		controller.getFillAppointmentView().getDetailField().setText(appointment.getDetail());
		controller.getFillAppointmentView().getTypeField().setSelectedItem(appointment.getType());
		controller.getFillAppointmentView().getTypeField().setEnabled(false);
		controller.getFillAppointmentView().getHoursComboBox().setSelectedItem(appointment.getTime().split(":")[0]);
		controller.getFillAppointmentView().getMinutesComboBox().setSelectedItem(appointment.getTime().split(":")[1]);
		controller.getFillAppointmentView().showEditBtn();
		controller.getFillAppointmentView().hideOKBtn();
		controller.getFillAppointmentView().display();
		controller.getFillAppointmentView().selectedButtonName(appointment.getTitle());
		
	}
	public int getIndex()
	{
		return this.index = index;
	}

}
