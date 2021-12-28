package run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessExplanation extends JFrame{
    static JPanel page1=new JPanel(){
        Image Chessmain = new ImageIcon(Main.class.getResource("/image/chessExplanation.png")).getImage();
        public void paint(Graphics g){
            g.drawImage(Chessmain,0,0,null);
        }
    };
    public ChessExplanation(){
        chess();
    }
    public void chess(){
        JButton btn1 = new JButton("되돌아가기");
        setTitle("ChessExplanation");
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        page1.setLayout(null);
        page1.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        btn1.setBounds(1100, 5, 100, 50);
        add(btn1);
        add(page1);
        setBackground(Color.white);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainmenu();
            }
        });
    }
    public static void main(String[] args){
        new ChessExplanation();
    }
}
