import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.ArrayList;


public class Window extends JFrame{

    private Panel p;
    private boolean startPressed = false;
    private int x = 200;
    private int y = 200;
    private int z = 0;
    private ArrayList<Point> points = new ArrayList<>();

    public Window(){
        JPanel bp = new JPanel(new FlowLayout());
        JButton start = new JButton("Start");
        System.out.println("Here");
        bp.add(start);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("btn");
                startPressed = true;
                p.repaint();
                //requestFocus(); // change the focus to JFrame to receive KeyEvent
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

        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (z < 10000 && startPressed) {
                    update();
                    repaint();
                    z++;
                    
                }
            
            }
        };
        System.out.println("Here");
        
        new Timer(0, updateTask).start();// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
        
    }

    public void update(){
        //250,10
        //50,400
        //450, 400    
        int vert = (int) (Math.random()*3) + 1;
        x = newX(x, vert);
        y = newY(y, vert);
        points.add(new Point(x, y));
    }

    public int newX(int x, int vert){
        if(vert == 1){
            return (x+50)/2;
        } else if (vert == 3) {
            return (x+450)/2;
        } else {
            return (x+250)/2;
        }
    }

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
    
        public void doDrawing(Graphics g){
    
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawLine(x, y, x, y);

    
            //g2d.drawLine(50,400,250,10);
            //g2d.drawLine(50,400,450,400);
            //g2d.drawLine(250,10,450,400);
    
    
        }
    
    
        @Override
        public void paintComponent(Graphics g){
            
            super.paintComponent(g);
            doDrawing(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            for(Point p: points){
                g2d.drawLine(p.x, p.y, p.x, p.y);
            }
            if(startPressed){
                g2d.drawOval(points.get(points.size()-1).x, points.get(points.size()-1).y, 3, 3);
                g2d.setColor(Color.RED);
                g2d.fillOval(points.get(points.size()-1).x, points.get(points.size()-1).y, 3, 3);
            }
            
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
    