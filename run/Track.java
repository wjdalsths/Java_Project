package run;

public class Track {
    private String titileImage; //제목 부분 이미지
    private String startImage; //게임 선택 창 표지 이미지
    private String gameImage; //해당 곡을 실행했을 때 표지 이미지
    private String startMusic; //게임 선택 창 음악

    public String getTitileImage() {
        return titileImage;
    }
    public void setTitileImage(String titileImage) {
        this.titileImage = titileImage;
    }
    public String getStartImage() {
        return startImage;
    }
    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }
    public String getGameImage() {
        return gameImage;
    }
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }
    public String getStartMusic() {
        return startMusic;
    }
    public void setStartMusic(String startMusic) {
        this.startMusic = startMusic;
    }

    public Track(String titileImage, String startImage, String gameImage, String startMusic) {
        super();
        this.titileImage = titileImage;
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
    }
}
