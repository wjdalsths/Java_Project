package run;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;



public class Checkers extends JPanel implements MouseListener{
    public static int width = 800, height = 800;
    public static final int PanSize = width/8; //각 판 길이
    public static final int PanCnt = width/PanSize; //판 개수
    public static int[][] baseGameData = new int[PanCnt][PanCnt]; //판 레이아웃
    public static int[][] gameData = new int[PanCnt][PanCnt]; //게임의 데이터 값저장 배열
    public static final int EMPTY = 0, ORANGE = 1, ORANGE_KING = 2, WHITE = 3, WHITE_KING = 4; //기물들 값

    public static int NowPlayer = 1; //현재 순서(할 플레이어)가 누군지
    public boolean inPlay = false; //이동할 수 있는가
    public static int[][] availablePlays = new int[PanCnt][PanCnt]; //사용가능한 곳은 8x8
    public int storedRow, storedCol;
    public boolean isJump = false; //점프할 수 있는
    static Image Kingimage = null; //킹을 불러오기위한 버퍼사진

    public static void main(String[] args) {

        try {
            Kingimage = ImageIO.read(new File("src/image/king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Checkers();

    }

    public Checkers() {
        set(this);
        Settingboard();
    }

    public void set(Checkers g){
        JFrame f = new JFrame();

        f.setSize(800, 800);
        f.setIconImage(Kingimage);
        f.setLocationRelativeTo(null);
        f.pack(); //내용물에 알맞게 윈도우 크기 조절
        Insets insets = getInsets();
        int frameLeftBorder = insets.left;
        int frameRightBorder = insets.right;
        int frameTopBorder = insets.top;
        int frameBottomBorder = insets.bottom;
        f.setPreferredSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));
        f.setMaximumSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));
        f.setMinimumSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));

        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addMouseListener(this);
        f.requestFocus(); //키 이벤트를 받을 컴포넌트 강제로 설정
        f.setVisible(true);
        f.add(g);
    }

    public boolean gameOver(){ //Wrapper for gameOverInternal
        return gameOverInternal(0, 0, 0, 0);
    }

    public boolean gameOverInternal(int col, int row, int orange, int white){
        if(gameData[col][row] == ORANGE || gameData[col][row] == ORANGE_KING)
            orange += 1;
        if(gameData[col][row] == WHITE || gameData[col][row] == WHITE_KING)
            white += 1;
        if(col == PanCnt-1 && row == PanCnt-1){
            if(orange == 0 || white == 0)
                return true;
            else return false;
        }
        if(col == PanCnt-1){
            row += 1;
            col = -1;
        }
        return gameOverInternal(col+1, row, orange, white);
    }

    public void Settingboard() {//첫음 보드 위치 설정
        for(int i=0; i < PanCnt; i+=2){
            gameData[i][5] = ORANGE;
            gameData[i][7] = ORANGE;
        }
        for(int i=1; i < PanCnt; i+=2)
            gameData[i][6] = ORANGE;
        for(int i=1; i < PanCnt; i+=2){
            gameData[i][0] = WHITE;
            gameData[i][2] = WHITE;
        }
        for(int i=0; i < PanCnt; i+=2)
            gameData[i][1] = WHITE;
    }
    public void DrawCircle(int col, int row, Graphics g, Color color) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //안티알리어싱 적용
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(color);
        g.fillOval((col*PanSize)+2, (row*PanSize)+2, PanSize-4, PanSize-4);
    }
    public void paint(Graphics g){
        //PRINT THE BOARD & PIECES
        super.paintComponent(g);
        for(int row = 0; row < PanCnt; row++){
            for(int col = 0; col < PanCnt; col++){
                if((row%2 == 0 && col%2 == 0) || (row%2 != 0 && col%2 != 0)){
                    baseGameData[col][row] = 0;
                    g.setColor(Color.gray);
                    g.fillRect(col*PanSize, row*PanSize, PanSize, PanSize);
                }

                else{
                    baseGameData[col][row] = 1;
                    g.setColor(Color.darkGray);
                    g.fillRect(col*PanSize, row*PanSize, PanSize, PanSize);
                }

                if(checkTeamPiece(col, row) ==  true){
                    g.setColor(Color.black);
                    g.fillRect(col*PanSize, row*PanSize, PanSize, PanSize);
                }

                if(availablePlays[col][row] == 1){
                    g.setColor(Color.green.darker());
                    g.fillRect(col*PanSize, row*PanSize, PanSize, PanSize);
                }

                if(gameData[col][row] == WHITE)
                    DrawCircle(col, row, g, Color.white);
                else if(gameData[col][row] == WHITE_KING){
                    DrawCircle(col, row, g, Color.white);
                    g.drawImage(Kingimage, (col*PanSize)+6, (row*PanSize)+6, PanSize-12, PanSize-12, null);
                }

                else if(gameData[col][row] == ORANGE)
                    DrawCircle(col, row, g, Color.orange);
                else if(gameData[col][row] == ORANGE_KING){
                    DrawCircle(col, row, g, Color.ORANGE);
                    g.drawImage(Kingimage, (col*PanSize)+6, (row*PanSize)+6, PanSize-12, PanSize-12, null);
                }
            }
        }
        if(gameOver() == true)
            gameOverDisplay(g);
    }
    public void gameOverDisplay(Graphics g) { //메인 게임오버 메세지
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width - metr.stringWidth(msg)) / 2, width / 2);
    }

    public void resetPlay(){
        storedCol = 0;
        storedRow = 0;
        inPlay = false;
        isJump = false;
        for(int row = 0; row < PanCnt; row++){
            for(int col = 0; col < PanCnt; col++){
                availablePlays[col][row] = 0;
            }
        }
        repaint();
    }
    public void swapPlayer(){
        if(NowPlayer == ORANGE)
            NowPlayer = WHITE;
        else NowPlayer = ORANGE;
    }

    public void makeMove(int col, int row, int storedCol, int storedRow){
        int x = gameData[storedCol][storedRow]; //새로운 타일으로 체인지
        gameData[col][row] = x;
        gameData[storedCol][storedRow] = EMPTY; //이전 조각 위치를 비어 있음으로 변경
        checkKing(col, row);
        if(isJump == true)
            removePiece(col, row, storedCol, storedRow);
        resetPlay();
        swapPlayer();
    }
    public void checkKing(int col, int row){
        if(gameData[col][row] == ORANGE && row == 0)
            gameData[col][row] = ORANGE_KING;
        else if(gameData[col][row] == WHITE && row == PanCnt-1)
            gameData[col][row] = WHITE_KING;
        else return;
    }
    public boolean isKing(int col, int row){
        if(gameData[col][row] == ORANGE_KING || gameData[col][row] == WHITE_KING){
            return true;
        }
        else return false;
    }

    public int checkOpponent(int col, int row){
        if(gameData[col][row] == ORANGE || gameData[col][row] ==  ORANGE_KING)
            return WHITE;
        else
            return ORANGE;
    }

    public void checkExtraJumps(int col, int row){
        int opponent = checkOpponent(col, row);
        int opponentKing = checkOpponent(col, row) + 1;
        if(gameData[col-1][row-1] == opponent || gameData[col-1][row-1] == opponentKing){
            availablePlays[col-1][row-1] = 1;
        }
        else if(gameData[col+1][row-1] == opponent || gameData[col+1][row-1] == opponentKing){
            availablePlays[col+1][row-1] = 1;
        }
        else if(gameData[col-1][row+1] == opponent || gameData[col-1][row+1] == opponentKing){
            availablePlays[col-1][row+1] = 1;
        }
        else if(gameData[col+1][row+1] == opponent || gameData[col+1][row+1] == opponentKing){
            availablePlays[col+1][row+1] = 1;
        }
        repaint();
    }

    public void removePiece(int col, int row, int storedCol, int storedRow){
        int pieceRow = -1;
        int pieceCol = -1;
        if(col > storedCol && row > storedRow){
            pieceRow = row-1;
            pieceCol = col-1;
        }
        if(col > storedCol && row < storedRow){
            pieceRow = row+1;
            pieceCol = col-1;
        }
        if(col < storedCol && row > storedRow){
            pieceRow = row-1;
            pieceCol = col+1;
        }
        if(col < storedCol && row < storedRow){
            pieceRow = row+1;
            pieceCol = col+1;
        }
        gameData[pieceCol][pieceRow] = EMPTY;
    }
    public void getAvailablePlays(int col, int row){
        inPlay = true;
        if((checkTeamPiece(col, row) == true)){ //피스가 현재 플레이어에 할당되었는지 확인
            if(gameData[col][row] == ORANGE){
                getUp(col, row);
            }
            if(gameData[col][row] == WHITE){
                getDown(col, row);
            }
            if(gameData[col][row] == ORANGE_KING || gameData[col][row] == WHITE_KING){
                getUp(col, row);
                getDown(col, row);
            }
            repaint();
        }
    }

    public void getUp(int col, int row){
        int rowUp = row-1;
        if(col == 0 && row != 0){
            for(int i = col; i < col+2; i++){
                if(gameData[col][row] != 0 && gameData[i][rowUp] != 0){
                    if(canJump(col, row, i, rowUp) == true){
                        int jumpCol = getJumpPos(col, row, i, rowUp)[0];
                        int jumpRow = getJumpPos(col, row, i, rowUp)[1];
                        availablePlays[jumpCol][jumpRow] = 1;
                    }
                }
                else if(baseGameData[i][rowUp] == 1 && gameData[i][rowUp] == 0)
                    availablePlays[i][rowUp] = 1;
            }
        }
        else if(col == (PanCnt - 1) && row != 0){
            if(gameData[col][row] != 0 && gameData[col-1][rowUp] != 0){
                if(canJump(col, row, col-1, rowUp) == true){
                    int jumpCol = getJumpPos(col, row, col-1, rowUp)[0];
                    int jumpRow = getJumpPos(col, row, col-1, rowUp)[1];
                    availablePlays[jumpCol][jumpRow] = 1;
                }
            }
            else if(baseGameData[col-1][rowUp] == 1 && gameData[col-1][rowUp] == 0)
                availablePlays[col-1][rowUp] = 1;
        }
        else if(col != PanCnt - 1 && col != 0 && row != 0){
            for(int i = col-1; i <= col+1; i++){
                if(gameData[col][row] != 0 && gameData[i][rowUp] != 0){
                    if(canJump(col, row, i, rowUp) == true){
                        int jumpCol = getJumpPos(col, row, i, rowUp)[0];
                        int jumpRow = getJumpPos(col, row, i, rowUp)[1];
                        availablePlays[jumpCol][jumpRow] = 1;
                    }
                }
                else if(baseGameData[i][rowUp] == 1 && gameData[i][rowUp] == 0)
                    availablePlays[i][rowUp] = 1;
            }
        }
    }

    public void getDown(int col, int row){
        int rowDown = row+1;
        if(col == 0 && row != PanCnt-1){
            if(gameData[col][row] != 0 && gameData[col+1][rowDown] != 0){
                if(canJump(col, row, col+1, rowDown) == true){
                    int jumpCol = getJumpPos(col, row, col+1, rowDown)[0];
                    int jumpRow = getJumpPos(col, row, col+1, rowDown)[1];
                    availablePlays[jumpCol][jumpRow] = 1;
                }
            }
            else if(baseGameData[col+1][rowDown] == 1 && gameData[col+1][rowDown] == 0)
                availablePlays[col+1][rowDown] = 1;
        }
        else if(col == PanCnt - 1 && row != PanCnt-1){
            if(gameData[col][row] != 0 && gameData[col-1][rowDown] != 0){
                if(canJump(col, row, col-1, rowDown) == true){
                    int jumpCol = getJumpPos(col, row, col-1, rowDown)[0];
                    int jumpRow = getJumpPos(col, row, col-1, rowDown)[1];
                    availablePlays[jumpCol][jumpRow] = 1;
                }
            }
            else if(baseGameData[col-1][rowDown] == 1 && gameData[col-1][rowDown] == 0)
                availablePlays[col-1][rowDown] = 1;
        }
        else if(col != PanCnt-1 && col != 0 && row != PanCnt-1){
            for(int i = col-1; i <= col+1; i++){
                if(gameData[col][row] != 0 && gameData[i][rowDown] != 0){
                    if(canJump(col, row, i, rowDown) == true){
                        int jumpCol = getJumpPos(col, row, i, rowDown)[0];
                        int jumpRow = getJumpPos(col, row, i, rowDown)[1];
                        availablePlays[jumpCol][jumpRow] = 1;
                    }
                }
                else if(baseGameData[i][rowDown] == 1 && gameData[i][rowDown] == 0)
                    availablePlays[i][rowDown] = 1;
            }
        }
    }
    public boolean checkTeamPiece(int col, int row){
        if(NowPlayer == ORANGE && (gameData[col][row] == ORANGE || gameData[col][row] == ORANGE_KING)) //bottom
            return true;
        if(NowPlayer == WHITE && (gameData[col][row] == WHITE || gameData[col][row] == WHITE_KING)) //top
            return true;
        else
            return false;

    }
    public boolean isLegalPos(int col, int row){
        if(row < 0 || row >= PanCnt || col < 0 || col >= PanCnt)
            return false;
        else return true;
    }

    public boolean canJump(int col, int row, int opponentCol, int opponentRow){
        //뛰어넘을수 있는지
        //and in bounds
        // 4 conditions based on column and row relations to the other piece
        if(((gameData[col][row] == WHITE || gameData[col][row] == WHITE_KING) && (gameData[opponentCol][opponentRow] == ORANGE || gameData[opponentCol][opponentRow] == ORANGE_KING)) || (gameData[col][row] == ORANGE || gameData[col][row] == ORANGE_KING) && (gameData[opponentCol][opponentRow] == WHITE || gameData[opponentCol][opponentRow] == WHITE_KING)){
            //If the piece is white/red and opponent piece is opposite TODO fix this if. It's so ugly
            if(opponentCol == 0 || opponentCol == PanCnt-1 || opponentRow == 0 || opponentRow == PanCnt-1)
                return false;
            int[] toData = getJumpPos(col, row, opponentCol, opponentRow);
            if(isLegalPos(toData[0],toData[1]) == false) //check board outofbounds
                return false;
            if(gameData[toData[0]][toData[1]] == 0){
                isJump = true;
                return true;
            }
        }
        return false;
    }

    public int[] getJumpPos(int col, int row, int opponentCol, int opponentRow){
        if(col > opponentCol && row > opponentRow && gameData[col-2][row-2] == 0)
            return new int[] {col-2, row-2};
        else if(col > opponentCol && row < opponentRow && gameData[col-2][row+2] == 0)
            return new int[] {col-2, row+2};
        else if(col < opponentCol && row > opponentRow && gameData[col+2][row-2] == 0)
            return new int[] {col+2, row-2};
        else
            return new int[] {col+2, row+2};
    }





    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(java.awt.event.MouseEvent evt) {
        int col = (evt.getX()-8) / PanSize;
        int row = (evt.getY()-30) / PanSize;
        if(inPlay == false && gameData[col][row] != 0 || inPlay == true && checkTeamPiece(col, row) == true){
            resetPlay();
            storedCol = col;
            storedRow = row;
            getAvailablePlays(col, row);
        }
        else if(inPlay == true && availablePlays[col][row] == 1){
            makeMove(col, row, storedCol, storedRow);
        }
        else if(inPlay == true && availablePlays[col][row] == 0){
            resetPlay();
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

