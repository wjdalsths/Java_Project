package run;
import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    public void paint(Graphics q){
        q.fillRect(100,100,400,400);
        for(int i=100;i<=400;i+=100){
            for(int j=100;j<=400;j+=100){
                q.clearRect(i,j,50,50);
            }
        }
        for(int i=150;i<=450;i+=100){
            for(int j=150;j<=450;j+=100){
                q.clearRect(i,j,50,50);
            }
        }
    }
    public Board(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        new Board();
    }
}
