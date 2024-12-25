import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class StartScreen extends JPanel implements KeyListener {
    private String hardmessage = "超級吃豆人";
    private String message = "請按 Enter 開始遊戲";
    private String instructions = "使用方向鍵移動";
    private String startingMessage = "遊戲即將開始...";
    private Image backgroundImage;
    private int fadeInAlpha = 0;
    private boolean fadeIn = true;
    public  int w = 500;
    public  int h = 500;
    StartScreen() {

        setPreferredSize(new Dimension(w, h));  // 設置開始畫面尺寸
        setBackground(Color.BLACK);  // 背景顏色為黑色
        setFocusable(true);  // 使面板可以接收鍵盤事件
        addKeyListener(this);  // 添加鍵盤事件監聽器

        // 加載背景圖片
        backgroundImage = new ImageIcon(getClass().getResource("./background.png")).getImage();

        // 設置定時器以實現漸變效果
        Timer fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fadeIn) {
                    if (fadeInAlpha < 255) {
                        fadeInAlpha += 5;  // 漸變透明度
                    }
                    if (fadeInAlpha >= 255) {
                        fadeIn = false;  // 漸變結束
                    }
                }
                repaint();  // 重繪畫面
            }
        });
        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // 畫背景圖片

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // 添加文字漸變效果
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeInAlpha / 255f));  // 設置漸變透明度


        int xm = w/2; //250
        int ym= h/2; //250
        // 使用微軟正黑體字型，若沒有此字型則使用預設字型

        Font font = new Font("Microsoft JhengHei", Font.BOLD, 36);  // 設定中文顯示字型
        g2d.setColor(Color.yellow);
        g2d.setFont(font);
        g2d.drawString(hardmessage, xm-100 ,ym-70);  // 顯示標題訊息

        //Font font = new Font("Microsoft JhengHei", Font.BOLD, 36);  // 設定中文顯示字型
        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString(message, xm-170 ,ym);  // 顯示開始訊息

        g2d.setColor(Color.yellow);
        g2d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));  // 設定中文顯示字型
        g2d.drawString(instructions,xm-70 ,ym+50 );  // 顯示遊戲控制提示

        // 如果畫面正在漸變，顯示提示文字
        if (fadeInAlpha < 255) {
            g2d.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 24));  // 設定中文顯示字型
            g2d.setColor(new Color(255, 255, 255, fadeInAlpha));
            g2d.drawString(startingMessage,xm-80 ,ym+125 );  // 顯示過渡文字
        }
        g2d.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 24));  // 設定中文顯示字型
        g2d.setColor(new Color(255, 255, 255, fadeInAlpha));
        g2d.drawString(startingMessage, xm-80, ym+125);  // 顯示過渡文字
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // 按下 Enter 開始遊戲
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();  // 關閉開始畫面
            launchGame();  // 啟動遊戲
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
