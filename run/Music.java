package run;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread {
    private Player player;
    /*현재 곡이 무한 반복인지 한번만 반복인지 확인 시켜주는 함수*/
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public Music(String name, boolean isLoop){
        try{
            this.isLoop=isLoop;
            file = new File(Main.class.getResource("/Music/"+name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public int getTime(){
        if(player==null)
            return 0;
        return player.getPosition();
    }
    //언제 실행되고 있던간에 항상 종료 되고 있게 해주는 함수
    public void close(){
        isLoop=false;
        player.close();
        //해당 쓰레드를 중지상태로 만들어준다
        this.interrupt();
    }
    @Override
    public void run(){
        try{
            do{
                player.play();
                fis=new FileInputStream(file);
                bis=new BufferedInputStream(fis);
                player=new Player(bis);
            }while(isLoop);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
