import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverScreen extends JPanel implements KeyListener {
    private String gameOverMessage = "遊戲結束";
    private String finalScoreMessage = "最終得分: ";
    private String restartMessage = "請按 Enter 重新開始遊戲";
    private int score;

    GameOverScreen(int score) {
        this.score = score;
        setPreferredSize(new Dimension(400, 300));  // 設定畫面尺寸
        setBackground(Color.BLACK);  // 背景顏色為黑色
        setFocusable(true);  // 使面板可以接收鍵盤事件
        addKeyListener(this);  // 添加鍵盤事件監聽器
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft JhengHei", Font.BOLD, 36));  // 設定字型
        g.drawString(gameOverMessage, 120, 100);  // 顯示遊戲結束訊息

        g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 24));  // 設定字型
        g.drawString(finalScoreMessage + score, 120, 150);  // 顯示最終得分

        g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));  // 設定字型
        g.drawString(restartMessage, 90, 200);  // 顯示重新開始提示
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // 按下 Enter 鍵重新開始遊戲
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();  // 關閉結束畫面
            launchGame();  // 啟動新遊戲
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // 啟動遊戲的方法
    private void launchGame() {
        JFrame gameFrame = new JFrame("Pac Man");
        PacMan pacmanGame = new PacMan();  // 創建遊戲面板
        gameFrame.add(pacmanGame);  // 把遊戲面板加入到視窗
        gameFrame.setSize(pacmanGame.getPreferredSize());  // 設定視窗大小
        gameFrame.setLocationRelativeTo(null);  // 視窗顯示在螢幕中間
        gameFrame.setResizable(false);  // 不允許調整視窗大小
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 設定關閉視窗時退出程式
        gameFrame.setVisible(true);  // 顯示視窗
    }
}
