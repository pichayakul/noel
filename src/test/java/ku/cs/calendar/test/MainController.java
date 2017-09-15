package ku.cs.calendar.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ku.cs.calendar.test.models.CalendarTest;
//import ku.cs.calendar.test.models.DatabaseTest;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class MainController {

	public CalendarTest calendar;
//	public AppointmentView appointmentView;
//	public FillAppointmentView filAppointmentView;
//	public MainView view;
//	public DatabaseTest database;
//	public YesNoForm yesNoForm;
	public void startApplication()
	{
		calendar = new CalendarTest(this);
//		Date date = new Date();
//		database = new DatabaseTest(this);
		
//		calendar.addAppointment("01 Jan 2018","8:00", "hello my name is noel ", "Introduction", date.toString());
//		view = new MainView(this);
//		appointmentView = new AppointmentView(this);
//		filAppointmentView = new FillAppointmentView(this); 
//		yesNoForm = new YesNoForm(this); 
	}
//	public YesNoForm getYesNoForm()
//	{
//		return this.yesNoForm;
//	}
//	public DatabaseTest getDatabase()
//	{
//		return this.database;
//	}
//	public MainView getMainView()
//	{
//		return view;
//	}
//	public FillAppointmentView getFillAppointmentView()
//	{
//		return filAppointmentView;
//	}
	public CalendarTest getCalendar()
	{
		return calendar;
	}
	public MainController getMainController()
	{
		return this;
	}
//	public AppointmentView getAppointView() {
//		// TODO Auto-generated method stub
//		return appointmentView;
//	}
}
