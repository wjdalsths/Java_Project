package run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloExplanation extends JFrame {
    static JPanel page3 = new JPanel(){
      Image Othellomain = new ImageIcon(Main.class.getResource("/image/othelloExplanation.png")).getImage();
      public void paint(Graphics g){
          g.drawImage(Othellomain,0,0,null);
      }
    };
    public OthelloExplanation(){
        othello();
    }
    public void othello(){
        JButton btn3 = new JButton("되돌아가기");
        setTitle("ChessExplanation");
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        page3.setLayout(null);
        page3.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        btn3.setBounds(1100, 5, 100, 50);
        add(btn3);
        add(page3);
        setBackground(Color.white);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainmenu();
            }
        });
    }
    public static void main(String[] args){
        new OthelloExplanation();
    }
}
