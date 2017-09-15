package ku.cs.calendar.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TypeTest {

	@Test
	public void test() {
		MainController test = new MainController();
		test.startApplication();
		
		Date date = new Date();
		String dateForm = date.getDay()+" "+date.getMonth()+" "+(date.getYear()+1900);
		test.getCalendar().addAppointment(dateForm, "20:00", "my name is KU","Introduction","Daily");
		
		assertEquals("Daily", test.getCalendar().getAppointmentList().get(0).getType());
test.getCalendar().addAppointment(dateForm, "20:00", "my name is KU","Introduction","Weekly");
		
		assertEquals("Weekly", test.getCalendar().getAppointmentList().get(1).getType());
	}

}
