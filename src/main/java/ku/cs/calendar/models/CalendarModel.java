package ku.cs.calendar.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ku.cs.calendar.controllers.MainController;

/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class CalendarModel {

	ArrayList<Appointment> appointmentList;
	ArrayList<String> months = new ArrayList<>();
	ArrayList<Appointment> newArray;
	MainController controller;
	GregorianCalendar calenda;
	public CalendarModel(MainController controller)
	{
		appointmentList = new ArrayList<>();
		newArray = new ArrayList<>();
		calenda = new GregorianCalendar();
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
	public void addAppointment(String date, String time,String detail,String type, String title)
	{
		appointmentList.add(new Appointment(date,time, detail, title,type,this.controller));
		if (this.controller.getDatabase()!=null)
		{
			this.controller.getDatabase().addDatabase(date, time, title, type,detail);
		}
		
	}
	public void editAppointment(Appointment appointment ,String time,String detail, String title,String type,String titleOld)
	{
		appointment.setTime(time);
		appointment.setDetail(detail);
		appointment.setTitle(title);
		appointment.setType(type);
		this.controller.getDatabase().editDatabase(time, title, detail,type,titleOld);
		
	}
	public void deleteAppointment(int index)
	{
		Appointment a = this.appointmentList.remove(index);
		this.getNewArray().remove(a);
		this.controller.getDatabase().deleteDatabase(a.getTitle());
	}
	public ArrayList<Appointment> getAppointmentList()
	{
		return appointmentList;
	}
	public boolean isSameTitle(String title)
	{
//		System.out.println(title);
//		title = title.split(" ")[1];
		for (int i=0;i<this.getAppointmentList().size();i++)
		{
			if (this.getAppointmentList().get(i).getTitle().equals(title))
			{
				return true;
			}
		}
		return false;
	}
	public void matchAppointment(String date) {
		newArray.clear();
		for (int i=0;i<this.getAppointmentList().size();i++)
		{
			if (this.getAppointmentList().get(i).getType().equals("Normal")) {
				if (this.getAppointmentList().get(i).getDate().equals(date))
				{
					newArray.add(this.getAppointmentList().get(i));
				}
			}
			else if (this.getAppointmentList().get(i).getType().equals("Daily")) 
			{
				newArray.add(this.getAppointmentList().get(i));
				//special
			}	
			else if (this.getAppointmentList().get(i).getType().equals("Monthly"))
			{
				if (this.getAppointmentList().get(i).getDate().split(" ")[0].equals(date.split(" ")[0]))
				{
					newArray.add(this.getAppointmentList().get(i));
				}
			}
			else if (this.getAppointmentList().get(i).getType().equals("Weekly"))
			{
				
				int year = Integer.parseInt(date.split(" ")[2]);
				int day = (Integer.parseInt(date.split(" ")[0]));
				int month = getIndexMonth(date.split(" ")[1]);
				int year2 = Integer.parseInt(this.getAppointmentList().get(i).getYear());
				int day2 = Integer.parseInt(this.getAppointmentList().get(i).getDay());
				int month2 = getIndexMonth(this.getAppointmentList().get(i).getMonth());
				
				calenda.set(year, month, day);
				Integer dayOfWeek = calenda.get(Calendar.DAY_OF_WEEK);
				calenda.set(year2, month2, day2);
				Integer dayOfWeek2 = calenda.get(Calendar.DAY_OF_WEEK);
				if (dayOfWeek.equals(dayOfWeek2))
				{
					newArray.add(this.getAppointmentList().get(i));
				}
			}
		}
	}
	public ArrayList<Appointment> getNewArray() {
		// TODO Auto-generated method stub
		return this.newArray;
	}
	public boolean canMarkDay(String date)
	{
		for (int i=0;i<this.getAppointmentList().size();i++)
		{
			if (this.getAppointmentList().get(i).getDate().equals(date))
			{
				return true;
			}
			if (this.getAppointmentList().get(i).getType().equals("Daily"))
			{
				return true;
			}
			if (this.getAppointmentList().get(i).getType().equals("Weekly"))
			{
//				System.out.println("Pin");
				int year = Integer.parseInt(date.split(" ")[2]);
				int day = (Integer.parseInt(date.split(" ")[0]));
				int month = getIndexMonth(date.split(" ")[1]);
				int year2 = Integer.parseInt(this.getAppointmentList().get(i).getYear());
				int day2 = Integer.parseInt(this.getAppointmentList().get(i).getDay());
				int month2 = getIndexMonth(this.getAppointmentList().get(i).getMonth());
				
				calenda.set(year, month, day);
				Integer dayOfWeek = calenda.get(Calendar.DAY_OF_WEEK);
				calenda.set(year2, month2, day2);
				Integer dayOfWeek2 = calenda.get(Calendar.DAY_OF_WEEK);
				if (dayOfWeek.equals(dayOfWeek2))
				{
					return true;
				}
			}
			if (this.getAppointmentList().get(i).getType().equals("Monthly") && controller.getCalendar().getAppointmentList().get(i).getDate().split(" ")[0].equals(date.split(" ")[0]))
			{
				return true;
			}
			
		}
		return false;
	}
	public int getIndexMonth(String month)
	{
		int numMonth=-99;
		if (month.equals("January"))
		{
			numMonth = Calendar.JANUARY;
		}
		if (month.equals("February"))
		{
			numMonth = Calendar.FEBRUARY;
		}
		if (month.equals("March"))
		{
			numMonth = Calendar.MARCH;
		}
		if (month.equals("April"))
		{
			numMonth =Calendar.APRIL;
		}
		if (month.equals("May"))
		{
			numMonth =Calendar.MAY;
		}
		if (month.equals("June"))
		{
			numMonth =Calendar.JUNE;
		}
		if (month.equals("July"))
		{
			numMonth =Calendar.JULY;
		}
		if (month.equals("August"))
		{
			numMonth =Calendar.AUGUST;
		}
		if (month.equals("September"))
		{
			numMonth =Calendar.SEPTEMBER;
		}
		if (month.equals("October"))
		{
			numMonth =Calendar.OCTOBER;
		}
		if (month.equals("November"))
		{
			numMonth =Calendar.NOVEMBER;
		}
		if (month.equals("December"))
		{
			numMonth =Calendar.DECEMBER;
		}
		return numMonth;
	}
	
}
