import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {
    public Window(){
        initUI();
    }

    public void initUI(){

        add(new Panel());
        setTitle("Window");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Window w = new Window();
                w.setVisible(true);
            }
        });
    }
}
    