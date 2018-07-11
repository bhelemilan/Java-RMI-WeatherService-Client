// WeatherItem.java
// WeatherItem displays a city's weather information in a JPanel.
import java.awt.*;
import java.net.*;

import javax.swing.*;

public class WeatherItem extends JPanel
{
   private static final long serialVersionUID = 1;
   private WeatherBean weatherBean;  // weather information

   // background ImageIcon
   private static ImageIcon backgroundImage;
   
   // static initializer block loads background image when class
   // WeatherItem is loaded into memory
   static
   {
      // get URL for background image
      URL url = WeatherItem.class.getResource( "images/back.jpg" );

      // background image for each city's weather info
      backgroundImage = new ImageIcon( url );
   }
  
   public WeatherItem( WeatherBean bean )
   {
      weatherBean = bean;
   }

   // display information for city's weather
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );
      
      // draw background
      backgroundImage.paintIcon( this, g, 0, 0 );
      
      // set font and drawing color, then display city name and temperature
      Font font = new Font( "SansSerif", Font.BOLD, 12 );
      g.setFont( font );
      g.setColor( Color.white );
      g.drawString( weatherBean.getCityName(), 10, 19 );
      g.drawString( weatherBean.getYesterday().getHighTemp()+" / "+weatherBean.getYesterday().getLowTemp(), 130, 19 );
      g.drawString((weatherBean.getYesterday().getPrecipitation()>0?Float.toString(weatherBean.getYesterday().getPrecipitation()):""),200,19);

      g.drawString( weatherBean.getToday().getHighTemp()+" / "+weatherBean.getToday().getLowTemp(), 350, 19 );
      g.drawString(weatherBean.getToday().getPrecipitation()>=0.0f?Float.toString(weatherBean.getToday().getPrecipitation()):"",420,70);

      g.drawString( weatherBean.getTomorrow().getHighTemp()+" / "+weatherBean.getTomorrow().getLowTemp(), 570, 19 );
      g.drawString(weatherBean.getTomorrow().getPrecipitation()>=0.0f?Float.toString(weatherBean.getTomorrow().getPrecipitation()):"",640,70);
      // display weather image
      weatherBean.getImageToday().paintIcon( this, g, 473, 1 );
      weatherBean.getImageTomorrow().paintIcon( this, g, 693, 1 );
   }

   // make WeatherItem's preferred size the width and height of the background image
   public Dimension getPreferredSize()
   {
      return new Dimension( backgroundImage.getIconWidth(), backgroundImage.getIconHeight() );
   }
}