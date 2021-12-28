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

    /*introBackground = new ImageIcon(Main.class.getResource("/image/메인.png")).getImage();*/ //넓이 : 1879 높이 : 1100
    /*introBackground = new ImageIcon(Main.class.getResource("/image/메인2.png")).getImage();*/ //넓이 : 600 높이 : 350
    private Image background =new ImageIcon(Main.class.getResource ("/image/메인1.png")).getImage();

    private  JLabel menuBar =new JLabel(new ImageIcon(Main.class.getResource ("/image/menuBar.png")));

    private  JButton exitButton=new JButton(exitButtonBasicImage);
    private  JButton startButton=new JButton(startButtonBasicImage);
    private  JButton quitButton=new JButton(quitButtonBasicImage);
    private  JButton leftButton=new JButton(leftButtonBasicImage);
    private  JButton rightButton=new JButton(rightButtonBasicImage);
    private  JButton ExplanationButton=new JButton(ExplanationButtonBasicImage);
    private  JButton GameStartButton=new JButton(GameStartButtonBasicImage);

    /*현재 마우스의  (x)와(y)의 좌표를 알려준다*/
    private int mouseX,mouseY;

    private boolean isMainScreen=false;

    ArrayList<Track> trackList=new ArrayList<Track>();
    private Image titleImage;
    private  Image selectedimage;
    private Music selectedMusic;
    //현재 선택 된 트랙의 번호
    private int nowSelected=0;

    public mainmenu(){
        setUndecorated(true);
        setTitle("Dynamic Beat");
        /*전체 화면의 크기를 1280*720으로 만들어 주는 함수*/
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        /*한 번 만들어진 화면창은 사용자가 임이적으로 줄이거나 크게 할 수 없다*/
        setResizable(false);
        /*게임창이 정 중앙에 뜨게 하는 함수*/
        setLocationRelativeTo(null);
        /*게임 창 종료시 프로그램 전체 종료*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*게임 창이 정상적으로 화면에 출력해주는 것*/
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
                //버튼을 보이게 할 것 인가 아닌가
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

        //처음 화면에는 보이지 않도록
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
                // 왼쪽 버튼 이벤트
                selectleft();
            }
        });
        add(leftButton);

        //맨 처음 화면은 보이지 않도록
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
                // 오른쪽 버튼 이벤트
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
        //g.drawImage : 단순히 이미지 출력
        g.drawImage(background, 0, 0, null);
        //시작 화면 즉 isMainScreen 변수가 true일때 340컴마 100에 위치에 보여준다
        if(isMainScreen){
            g.drawImage(selectedimage,340,100,null);
            g.drawImage(titleImage,340,100,null);
        }
        // add(~)는 paintComponents에 의해 그려짐
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
        //보류
        //버튼 누를씨 원하는 창으로 이동
    }
}
