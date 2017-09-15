package ku.cs.calendar.test.models;

import java.util.ArrayList;

import ku.cs.calendar.test.MainController;


/*
 * pichayakul jenpoomjai 5810450903 sec200
 */

public class CalendarTest {

	ArrayList<AppointmentTest> appointmentList;
	public ArrayList<String> months = new ArrayList<>();
	public MainController controller;
	public CalendarTest(MainController controller)
	{
		appointmentList = new ArrayList<>();
		this.controller = controller;
		String[] list = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		for (String i : list )
		{
			months.add(i);
		}
	}
	public ArrayList<String> getMonths()
	{
		return this.months;
	}
	public void addAppointment(String date, String time,String detail, String title,String type)
	{
//		System.out.println(controller);
		appointmentList.add(new AppointmentTest(date, time, detail, title,type, controller));
//		if (this.controller.getDatabase()!=null)
//		{
//			this.controller.getDatabase().addDatabase(date, time, title, detail);
//		}
		
	}
	public void editAppointment(AppointmentTest appointment ,String time,String detail, String title,String titleOld)
	{
		appointment.setTime(time);
		appointment.setDetail(detail);
		appointment.setTitle(title);
//		this.controller.getDatabase().editDatabase(time, title, detail,titleOld);
		
	}
	public void deleteAppointment(int index)
	{
		AppointmentTest a = this.appointmentList.remove(index);
		
//		this.controller.getDatabase().deleteDatabase(a.getTitle());
	}
	public ArrayList<AppointmentTest> getAppointmentList()
	{
		return appointmentList;
	}
	public boolean isSameTitle(String title)
	{
		title = title.split(" ")[0];
		for (int i=0;i<this.getAppointmentList().size();i++)
		{
			if (this.getAppointmentList().get(i).getTitle().equals(title))
			{
				return true;
			}
		}
		return false;
	}
	
}
