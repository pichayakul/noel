package ku.cs.calendar.models;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import ku.cs.calendar.controllers.MainController;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class Appointment {

	String date;
	String detail;
	String title;
	String time;
	Timer timer;
	String type;
	MainController controller;
//	@SuppressWarnings("deprecation")
	public Appointment(String date,String time,String detail,String title,String type,MainController controller) {
		this.date = date;
		System.out.println(date);
		this.time = time;
		this.type = type;
		this.detail = detail;
		this.title = title;
		this.timer = new Timer();
		this.controller = controller;
		Date appointmentTime = new Date();
		appointmentTime.setHours(Integer.parseInt(this.time.split(":")[0]));
		appointmentTime.setMinutes(Integer.parseInt(this.time.split(":")[1]));
		appointmentTime.setDate(Integer.parseInt(this.date.split(" ")[0]));
		appointmentTime.setMonth((controller.getCalendar().getMonths().indexOf(this.date.split(" ")[1])));
		int year = (Integer.parseInt(date.split(" ")[2]));
		appointmentTime.setYear(year-1900);
		appointmentTime.setSeconds(0);
//		System.out.println(appointmentTime);
//		timer.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "You have an appointment.!!\n"+"Title : "+getTitle()+"\nDetail : \n    "+getDetail());
//			}
//		}, appointmentTime);
	}
	public String getDay()
	{
		return this.date.split(" ")[0];
	}
	public String getMonth()
	{
		return this.date.split(" ")[1];
	}
	public String getYear()
	{
		return this.date.split(" ")[2];
	}
	public String getType() 
	{
		return this.type;
	}
	public String getTime()
	{
		return this.time;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}
	public String getDetail()
	{
		return this.detail;
	}
	public String getDate()
	{
		return this.date;
	}
}
