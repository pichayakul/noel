package ku.cs.calendar;

import ku.cs.calendar.controllers.MainController;
/*
 * pichayakul jenpoomjai 5810450903 sec200
 */
public class StartApp 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        MainController controller = new MainController();
        controller.startApplication();
    }
}
