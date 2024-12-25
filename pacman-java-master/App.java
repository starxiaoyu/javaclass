import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame startFrame = new JFrame("Pac Man - Start Screen");
        StartScreen startScreen = new StartScreen();  // 創建開始畫面
        startFrame.add(startScreen);
        startFrame.setSize(500, 500);  // 設置開始畫面大小
        startFrame.setLocationRelativeTo(null);  // 視窗顯示在螢幕中間
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 設定關閉視窗時退出程式
        startFrame.setVisible(true);  // 顯示開始畫面
    }
}
