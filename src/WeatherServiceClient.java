// WeatherServiceClient.java
// WeatherServiceClient uses the WeatherService remote object
// to retrieve weather information.
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class WeatherServiceClient extends JFrame
{
   private static List<List> weatherInformation;

   private static int numberOfSections = 4;

   private void getAllSectionWeatherInfo(String server, int port)
   {
      if (weatherInformation == null || weatherInformation.size() <= 0)
      {
         try
         {
            Registry registry;

            if(port == 1099)
               registry = LocateRegistry.getRegistry(server);
            else
               registry = LocateRegistry.getRegistry(server, port);

            WeatherService stub = (WeatherService) registry.lookup("WeatherService");

            weatherInformation = new ArrayList(stub.getWeatherInformation() );
         }
         catch (ConnectException connectionException )
         {
            System.err.println( "Connection to server failed. Server may be temporarily unavailable." );
            connectionException.printStackTrace();
         }
         catch ( Exception exception )
         {
            exception.printStackTrace();
         }
      }
   }

   public WeatherServiceClient(String server, int port, int sectionID)
   {
      super( "RMI WeatherService Client" );

      getAllSectionWeatherInfo(server, port);

      JPanel frame = new JPanel(new BorderLayout()); //PREFERRED!
      frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));

      // create WeatherListModel for weather information
      ListModel weatherListModel = new WeatherListModel( weatherInformation.get(sectionID) );
      // create JList, set ListCellRenderer and add to layout
      JList weatherJList = new JList( weatherListModel );
      weatherJList.setCellRenderer( new WeatherCellRenderer() );
      JScrollPane scrollPane = new JScrollPane( weatherJList );
      scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));

      frame.add(new WeatherSectionHeader((WeatherBean) weatherInformation.get(sectionID).get(0)));
      frame.add(scrollPane);

      getContentPane().add(frame);
   }

   public static void main( String args[] )
   {
      if (args.length!=2)
      {
         System.err.println("USAGE: java -Djava.security.policy=<Policy File> WeatherServiceClient <Host Name> <Port Number>");
         System.exit(1);
      }
      WeatherServiceClient[] client = new WeatherServiceClient[numberOfSections];
      System.setSecurityManager (new SecurityManager());

      for (int i = 0; i < numberOfSections; i++)
      {
         client[i] = new WeatherServiceClient(args[0], Integer.parseInt(args[1]), i);

         // configure and display application window
         client[i].setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
         client[i].pack();
         client[i].setResizable( true );
         client[i].setVisible( true );
      }
   }
}