import java.awt.*;
import javax.swing.*;


public class Panel extends JPanel{
    
    public void doDrawing(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        int x = 200;
        int y = 200;

        //250,10
        //50,400
        //450, 400

        for(int i=0; i<100000; i++){
            int vert = (int) (Math.random()*3) + 1;
            x = newX(x, vert);
            y = newY(y, vert);
            g2d.drawLine(x,y,x,y);

        }

        g2d.drawLine(50,400,250,10);
        g2d.drawLine(50,400,450,400);
        g2d.drawLine(250,10,450,400);


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
            return (y+400)/2;
        } else if (vert == 3) {
            return (y+400)/2;
        } else {
            return (y+10)/2;
        }
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);

    }
}
