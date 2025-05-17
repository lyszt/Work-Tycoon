import GUI.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.Initialize();
        SoundPlayer.playSound("resources/audio/menu.wav");
    }
}