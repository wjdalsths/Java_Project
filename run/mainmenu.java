package run;

import run.Chess.ChessMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class mainmenu extends JFrame{
    private Image screenImage;
    private Graphics screenGraphics;

    private ImageIcon exitButtonEnterdImage = new ImageIcon(Main.class.getResource ("/image/exitButtonEnterd.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource ("/image/exitButtonBasic.png"));

    private ImageIcon startButtonEnterdImage = new ImageIcon(Main.class.getResource ("/image/startButtonEnterd.png"));
    private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource ("/image/startButtonBasic.png"));

    private ImageIcon quitButtonEnterdImage = new ImageIcon(Main.class.getResource ("/image/quitButtonEnterd.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource ("/image/quitButtonBasic.png"));

    private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource ("/image/leftButton.png"));
    private ImageIcon leftButtonEnterd = new ImageIcon(Main.class.getResource ("/image/leftButtonEnterd.png"));

    private ImageIcon rightButtonBasicImage =new ImageIcon(Main.class.getResource ("/image/rightButton.png"));
    private ImageIcon rightButtonEnterd = new ImageIcon(Main.class.getResource ("/image/rightButtonEnterd.png"));

    private ImageIcon ExplanationButtonBasicImage = new ImageIcon(Main.class.getResource("/image/ExplanationBasic.png"));
    private ImageIcon ExplanationButtonEnterd = new ImageIcon(Main.class.getResource("/image/ExplanationEnter.png"));

    private ImageIcon GameStartButtonBasicImage = new ImageIcon(Main.class.getResource("/image/gameStartBasic.png"));
    private ImageIcon GameStartButtonEnterd = new ImageIcon(Main.class.getResource("/image/gameStartEnter.png"));

    /*introBackground = new ImageIcon(Main.class.getResource("/image/??????.png")).getImage();*/ //?????? : 1879 ?????? : 1100
    /*introBackground = new ImageIcon(Main.class.getResource("/image/??????2.png")).getImage();*/ //?????? : 600 ?????? : 350
    private Image background =new ImageIcon(Main.class.getResource ("/image/??????1.png")).getImage();

    private  JLabel menuBar =new JLabel(new ImageIcon(Main.class.getResource ("/image/menuBar.png")));

    private  JButton exitButton=new JButton(exitButtonBasicImage);
    private  JButton startButton=new JButton(startButtonBasicImage);
    private  JButton quitButton=new JButton(quitButtonBasicImage);
    private  JButton leftButton=new JButton(leftButtonBasicImage);
    private  JButton rightButton=new JButton(rightButtonBasicImage);
    private  JButton ExplanationButton=new JButton(ExplanationButtonBasicImage);
    private  JButton GameStartButton=new JButton(GameStartButtonBasicImage);

    /*?????? ????????????  (x)???(y)??? ????????? ????????????*/
    private int mouseX,mouseY;

    private boolean isMainScreen=false;

    ArrayList<Track> trackList=new ArrayList<Track>();
    private Image titleImage;
    private  Image selectedimage;
    private Music selectedMusic;
    //?????? ?????? ??? ????????? ??????
    private int nowSelected=0;

    public mainmenu(){
        setUndecorated(true);
        setTitle("Dynamic Beat");
        /*?????? ????????? ????????? 1280*720?????? ????????? ?????? ??????*/
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        /*??? ??? ???????????? ???????????? ???????????? ??????????????? ???????????? ?????? ??? ??? ??????*/
        setResizable(false);
        /*???????????? ??? ????????? ?????? ?????? ??????*/
        setLocationRelativeTo(null);
        /*?????? ??? ????????? ???????????? ?????? ??????*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*?????? ?????? ??????????????? ????????? ??????????????? ???*/
        setVisible(true);
        setBackground(new Color(0,0,0,0));
        setLayout(null);

        Music intromusic=new Music("bgm.mp3",true);
        intromusic.start();

        trackList.add(new Track("ChessTitle.png","ChessStart.png","sunsetgameimage.png","sunset.mp3"));
        trackList.add(new Track("CheckerTitle.png","CheckerStart.png","IoliteSkygameimage.png","IoliteSky.mp3"));
        trackList.add(new Track("OthelloTitle.png","OthelloStart.png","Achluoiasgameimage.png","achluoias.mp3"));

        exitButton.setBounds(1245,0,30,30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnterdImage);
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(exitButtonBasicImage);
            }
            @Override
            public void mousePressed(MouseEvent e){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        startButton.setBounds(525,550,200,50);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnterdImage);
            }
            @Override
            public void mouseExited(MouseEvent e){
                startButton.setIcon(startButtonBasicImage);
            }
            @Override
            public void mousePressed(MouseEvent e){
                intromusic.close();
                selectTrack(0);
                //????????? ????????? ??? ??? ?????? ?????????
                startButton.setVisible(false);
                quitButton.setVisible(false);
                leftButton.setVisible(true);
                rightButton.setVisible(true);
                ExplanationButton.setVisible(true);
                GameStartButton.setVisible(true);
                background =new ImageIcon(Main.class.getResource("/image/mainBackground.png")).getImage();
                isMainScreen = true;
            }
        });
        add(startButton);

        quitButton.setBounds(525,625,200,50);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnterdImage);
            }
            @Override
            public void mouseExited(MouseEvent e){
                quitButton.setIcon(quitButtonBasicImage);
            }
            @Override
            public void mousePressed(MouseEvent e){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(quitButton);

        //?????? ???????????? ????????? ?????????
        leftButton.setVisible(false);
        leftButton.setBounds(140,310,60,60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnterd);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                leftButton.setIcon(leftButtonBasicImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                // ?????? ?????? ?????????
                selectleft();
            }
        });
        add(leftButton);

        //??? ?????? ????????? ????????? ?????????
        rightButton.setVisible(false);
        rightButton.setBounds(1080,310,60,60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnterd);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                rightButton.setIcon(rightButtonBasicImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                // ????????? ?????? ?????????
                selectright();
            }
        });
        add(rightButton);

        ExplanationButton.setVisible(false);
        ExplanationButton.setBounds(350,540,250,67);
        ExplanationButton.setBorderPainted(false);
        ExplanationButton.setContentAreaFilled(false);
        ExplanationButton.setFocusPainted(false);
        ExplanationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ExplanationButton.setIcon(ExplanationButtonEnterd);
                ExplanationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                ExplanationButton.setIcon(ExplanationButtonBasicImage);
                ExplanationButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                gameStart(nowSelected,"Explanation");
                dispose();
                if(nowSelected==0){
                    new ChessExplanation();
                }else if(nowSelected==1){
                    new CheckerExplanation();
                }else if(nowSelected==2){
                    new OthelloExplanation();
                }
            }
        });
        add(ExplanationButton);

        GameStartButton.setVisible(false);
        GameStartButton.setBounds(680,540,250,67);
        GameStartButton.setBorderPainted(false);
        GameStartButton.setContentAreaFilled(false);
        GameStartButton.setFocusPainted(false);
        GameStartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                GameStartButton.setIcon(GameStartButtonEnterd);
                GameStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                GameStartButton.setIcon(GameStartButtonBasicImage);
                GameStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                gameStart(nowSelected,"GameStart");
                dispose();
                if(nowSelected==0){
                    new ChessMain();
                }else if(nowSelected==1){
                    new Checkers();
                }else if(nowSelected==2){

                }
            }
        });
        add(GameStartButton);

        menuBar.setBounds(0,0,1280,30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX=e.getX();
                mouseY=e.getY();
            }
        });

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                int x=e.getXOnScreen();
                int y=e.getYOnScreen();
                setLocation(x-mouseX,y-mouseY);
            }
        });
        add(menuBar);
    }
    public void paint(Graphics g)
    {
        screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        screenGraphics = screenImage.getGraphics();
        screenDraw(screenGraphics);
        g.drawImage(screenImage,0,0,null);
    }
    public void screenDraw(Graphics g) {
        //g.drawImage : ????????? ????????? ??????
        g.drawImage(background, 0, 0, null);
        //?????? ?????? ??? isMainScreen ????????? true?????? 340?????? 100??? ????????? ????????????
        if(isMainScreen){
            g.drawImage(selectedimage,340,100,null);
            g.drawImage(titleImage,340,100,null);
        }
        // add(~)??? paintComponents??? ?????? ?????????
        paintComponents(g);
        this.repaint();
    }
    public void selectTrack(int nowSelected){
        if(selectedMusic!=null)
            selectedMusic.close();
        titleImage=new ImageIcon(Main.class.getResource("/image/" +trackList.get(nowSelected).getTitileImage())).getImage();
        selectedimage=new ImageIcon(Main.class.getResource("/image/" +trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic=new Music(trackList.get(nowSelected).getStartMusic(),true);
        selectedMusic.start();
    }
    public void selectleft(){
        if(nowSelected==0){
            nowSelected=trackList.size()-1;
        }else{
            nowSelected--;
        }
        selectTrack(nowSelected);
    }
    public void selectright(){
        if(nowSelected==trackList.size()-1){
            nowSelected=0;
        }else{
            nowSelected++;
        }
        selectTrack(nowSelected);
    }
    public void gameStart(int nowSelected,String difficulty){
        if(selectedMusic!=null){
            selectedMusic.close();
        }
        isMainScreen=false;
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        ExplanationButton.setVisible(false);
        GameStartButton.setVisible(false);
        //??????
        //?????? ????????? ????????? ????????? ??????
    }
}
