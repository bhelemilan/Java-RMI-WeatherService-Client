// WeatherCellRenderer.java
// WeatherCellRenderer is a custom ListCellRenderer for WeatherBeans in a JList.
import java.awt.*;

import javax.swing.*;

public class WeatherCellRenderer extends DefaultListCellRenderer
{
   // returns a WeatherItem object that displays city's weather
   public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean focus )
   {
      return new WeatherItem( ( WeatherBean ) value );
   }
}