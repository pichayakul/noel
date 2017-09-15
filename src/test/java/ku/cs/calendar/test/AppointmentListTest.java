package ku.cs.calendar.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class AppointmentListTest {

	@Test
	public void test() {
		MainController test = new MainController();
		test.startApplication();
		
		Date date = new Date();
		String dateForm = date.getDay()+" "+date.getMonth()+" "+(date.getYear()+1900);
//		System.out.println(dateForm);
		test.getCalendar().addAppointment(dateForm, "20:00", "my name is KU", "Introduction","Daily");
		
		assertEquals(1, test.getCalendar().getAppointmentList().size());
		//add 1 appointment
		test.getCalendar().addAppointment(dateForm, "20:00", "my name is KU","Introduction2","Daily");
		assertEquals(2, test.getCalendar().getAppointmentList().size());
		//delete 1 appointment
		test.getCalendar().getAppointmentList().remove(0);
		assertEquals(1, test.getCalendar().getAppointmentList().size());
		
		
	}

}
