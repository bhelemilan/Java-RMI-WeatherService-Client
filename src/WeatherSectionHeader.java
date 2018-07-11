import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WeatherSectionHeader extends JPanel
{
    private static final long serialVersionUID = 1;
    private static ImageIcon backgroundImage;
    private WeatherBean weatherBean;  // weather information

    static
    {
        // get URL for background image
        URL url = WeatherItem.class.getResource( "images/header_back.jpg" );

        // background image for each city's weather info
        backgroundImage = new ImageIcon( url );
    }

    public WeatherSectionHeader( WeatherBean bean )
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
        g.drawString( "The Weather Fetcher", 360, 19 );

        g.setColor( Color.blue );
        g.drawString( "CITY", 10, 70 );

        g.drawString( weatherBean.getYesterday().getDate()+" [ "+weatherBean.getYesterday().getDay()+" ]", 170, 53 );
        g.drawString( "HI / LO", 130, 70 );
        g.drawString( "PCPN", 200, 70 );
        g.drawString( "WEATHER", 253, 70 );

        g.drawString( weatherBean.getToday().getDate()+" [ "+weatherBean.getToday().getDay()+" ]", 390, 53 );
        g.drawString( "HI / LO", 350, 70 );
        g.drawString( "PCPN", 420, 70 );
        g.drawString( "WEATHER", 473, 70 );

        g.drawString( weatherBean.getTomorrow().getDate()+" [ "+weatherBean.getTomorrow().getDay()+" ]", 610, 53 );
        g.drawString( "HI / LO", 570, 70 );
        g.drawString( "PCPN", 640, 70 );
        g.drawString( "WEATHER", 693, 70 );
    }

    // make WeatherItem's preferred size the width and height of the background image
    public Dimension getPreferredSize()
    {
        return new Dimension( backgroundImage.getIconWidth(), backgroundImage.getIconHeight() );
    }
}