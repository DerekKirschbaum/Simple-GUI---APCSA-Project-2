import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;


public class Window extends JFrame{

    private Panel p;
    private boolean startPressed = false;
    private int x = 200;
    private int y = 200;
    private int numIterations = 0;
    private ArrayList<Point> points = new ArrayList<>();
    private int speed = 1;
    private Timer t;

    //constructor
    public Window(){
        JPanel bp = new JPanel(new FlowLayout());
        JButton start = new JButton("Start");
        JSlider js = new JSlider(1, 23, 1);
        JLabel l = new JLabel("     Speed: " + js.getValue());
        JLabel l2 = new JLabel("     Points: " + points.size());
        bp.add(start);
        bp.add(l);
        bp.add(js);
        bp.add(l2);
        
        //slider bar change listener
        js.addChangeListener(new ChangeListener () {
            public void stateChanged(ChangeEvent e) {
                l.setText("     Speed : " + ((JSlider)e.getSource()).getValue());
                speed = ((JSlider)e.getSource()).getValue();
            }
        });

        //start button action listener
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startPressed = true;
                p.repaint();
            }
        });

        p = new Panel();
        p.setPreferredSize(new Dimension(500, 500));
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p, BorderLayout.CENTER);
        cp.add(bp, BorderLayout.SOUTH);

        setTitle("Sierpinski's Triangle Animator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();

        //updateTask runs every 'delay' msec based on timer
        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (numIterations < 10000 && startPressed) {
                    update();
                    repaint();
                    numIterations++;
                    l2.setText("     Points: " + points.size());
                }
            
            }
        };
        
        t = new Timer(((int)(1000/(Math.pow((double)speed, 2.0)))), updateTask);
        t.start(); // Allocate a Timer to run updateTask's actionPerformed() after every delay msec
        
    }

    //adds new point to animation
    public void update(){
        //Triangle vertices: (250,60), (50,450) (450, 450)
        int vert = (int) (Math.random()*3) + 1;
        x = newX(x, vert);
        y = newY(y, vert);
        points.add(new Point(x, y));
    }

    //helper method
    public int newX(int x, int vert){
        if(vert == 1){
            return (x+50)/2;
        } else if (vert == 3) {
            return (x+450)/2;
        } else {
            return (x+250)/2;
        }
    }
    //helper method
    public int newY(int y, int vert){
        if(vert == 1){
            return (y+450)/2;
        } else if (vert == 3) {
            return (y+450)/2;
        } else {
            return (y+60)/2;
        }
    }

    
    class Panel extends JPanel{
        //Paints points onto JPanel
        @Override
        public void paintComponent(Graphics g){
            
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            for(Point p: points){
                g2d.drawLine(p.x, p.y, p.x, p.y);
            }
            if(startPressed && numIterations<9999 && points.size()>1){
                g2d.drawOval(points.get(points.size()-1).x, points.get(points.size()-1).y, 3, 3);
                g2d.setColor(Color.RED);
                g2d.fillOval(points.get(points.size()-1).x, points.get(points.size()-1).y, 3, 3);
            }
            
            int a = (int)(1000/(Math.pow((double)speed, 2.0)));
            t.setDelay(a);
            
        }
    }
    

    public static void main(String[] args){
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
    