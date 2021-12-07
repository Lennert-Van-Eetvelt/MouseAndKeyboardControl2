import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class MyPanel{
    public static JLabel jcomp1;
    public static JLabel jcomp2;
    public static JTextArea serverLog;

    public static JScrollPane MyPanel() {
        //construct components
        serverLog = new JTextArea(SystemOutCatcher.systemOut);
        JScrollPane scroll = new JScrollPane(serverLog);
        serverLog.setEditable(false);
        scroll.setPreferredSize (new Dimension (800, 400));
        serverLog.setFont(new Font("Roboto", Font.BOLD,12));

        serverLog.setMargin(new Insets(10,10,10,10));
        return scroll;/*

        serverLog = new JTextArea("hiii");
        JScrollPane out = new JScrollPane(serverLog);
        serverLog.setEditable(false);
        serverLog.setLineWrap(true);
        serverLog.setWrapStyleWord(true);

        //adjust size and set layout
        out.setPreferredSize (new Dimension (800, 400));
        out.setLayout (null);

        //add components
        out.add (jcomp2);
        out.add (jcomp1);
        out.add(serverLog);

        //set component bounds (only needed by Absolute Positioning)

        return out;*/
    }

    public static void setServerLog(String value){
        if (!value.equals(serverLog.getText()))
        serverLog.setText(value);
    }


    public static void main (String[] args) throws Exception {
        System.out.println("?");
        SystemOutCatcher systemOutCatcher = new SystemOutCatcher();
        JScrollPane myPanel = MyPanel();
        myPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame ("Mouse and Keyboard control");
        ImageIcon img = new ImageIcon("D:\\Users\\lenne\\Documents\\Projects\\MouseAndKeyboardControl2\\src\\resources\\icon.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (myPanel);
        frame.pack();
        frame.setVisible (true);
//        ImageIcon img = new ImageIcon("/resources/icon.png");
        new JavaServer().main(new String[]{});

        while(true) {
            sleep(100);
            setServerLog(systemOutCatcher.getSystemOut());
        }
    }
    public static void sleep(long millis){
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        }catch (Exception e){
        }
    }
}