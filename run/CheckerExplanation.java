package run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckerExplanation extends JFrame {
    JPanel page2 = new JPanel() {
        Image Checkermain = new ImageIcon(Main.class.getResource("/image/checkerExplanation.png")).getImage();
        public void paint(Graphics g) {
            g.drawImage(Checkermain, 0, 0, null);
        }
    };
        public CheckerExplanation(){
            checker();
        }
        public void checker(){
            JButton btn2 = new JButton("되돌아가기");
            setTitle("ChessExplanation");
            setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            page2.setLayout(null);
            page2.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
            btn2.setBounds(1100, 5, 100, 50);
            add(btn2);
            add(page2);
            setBackground(Color.white);
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    new mainmenu();
                }
            });
        }

    public static void main(String[] args){
            new CheckerExplanation();
        }
}
