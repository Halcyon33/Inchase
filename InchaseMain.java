package javadevelop;

import scenes.BookshelfScene;
import scenes.ClockScene;
import scenes.DeskScene;
import scenes.DoorScene;
import scenes.EscapeScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InchaseMain extends JFrame {

    // 🔹 메인 배경
    private JLabel mainLabel;
    private ImageIcon mainImage;

    // 🔹 아이템 상태
    private boolean hasKey = false;         // 열쇠 획득 여부
    private boolean hasRedPotion = false;   // 붉은 물약 획득 여부
    private boolean hasBluePotion = false;  // 파란 물약 획득 여부
    private boolean hasWhitePotion = false; // 하얀 물약 획득 여부
    
    private DeskScene deskScene;
    private DoorScene doorScene;
    private BookshelfScene bookshelfScene;
    private ClockScene clockScene;
    private EscapeScene escapeScene;
    
    // 🔹 생성자
    public InchaseMain() {
        setTitle("Inchase Room");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 메인 배경 이미지 설정
        mainImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\Inchase.page1.png\\");
        mainLabel = new JLabel(mainImage);
        mainLabel.setLayout(null);
        setContentPane(mainLabel);

        showMainScene();

        setVisible(true);
    }

    // 🔹 메인 장면 표시
    public void showMainScene() {
        mainLabel.removeAll();
        mainLabel.setIcon(mainImage);

        JButton doorBtn = makeButton(1500, 360, 300, 600, e -> showScene("door"));
        JButton deskBtn = makeButton(580, 560, 900, 250, e -> showScene("desk"));
        JButton bookshelfBtn = makeButton(200, 300, 250, 400, e -> showScene("bookshelf"));
        JButton clockBtn = makeButton(650, 80, 900, 420, e -> showScene("clock"));

        mainLabel.add(doorBtn);
        mainLabel.add(deskBtn);
        mainLabel.add(bookshelfBtn);
        mainLabel.add(clockBtn);

        revalidate();
        repaint();
    }


    // 씬 전환
    public void showScene(String name) {
        JPanel scene = switch (name) {
            case "door" -> doorScene == null ? doorScene = new DoorScene(this) : doorScene;
            case "desk" -> { // 씬 전환 시 DeskScene의 상태 초기화
                if (deskScene == null) {
                    deskScene = new DeskScene(this);
                }
                deskScene.resetToDeskView(); // DeskScene의 resetToDeskView() 호출
                yield deskScene;
            }
            case "bookshelf" -> bookshelfScene == null ? bookshelfScene = new BookshelfScene(this) : bookshelfScene;
            case "clock" -> clockScene == null ? clockScene = new ClockScene(this) : clockScene;
            case "escape" -> escapeScene == null ? escapeScene = new EscapeScene(this) : escapeScene;
            default -> null;
        };
        if (scene != null) {
            setContentPane(scene);
            revalidate();
            repaint();
        }
    }

    // 🔹 메인으로 복귀
    public void goBack() {
        setContentPane(mainLabel);
        showMainScene();
    }

    public void goMainScene() {
        setContentPane(mainLabel);
        revalidate();
        repaint();
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey(boolean value) {
        hasKey = value;
    
    }

    public boolean hasRedPotion() {
        return hasRedPotion;
    }

    public void setHasRedPotion(boolean value) {
        hasRedPotion = value;
      
    }

    public boolean hasBluePotion() {
        return hasBluePotion;
    }

    public void setHasBluePotion(boolean value) {
        hasBluePotion = value;
       
    }
    public boolean hasWhitePotion() {
        return hasWhitePotion;
    }

    public void setHasWhitePotion(boolean value) {
        hasWhitePotion = value;
       
    }

    private JButton makeButton(int x, int y, int w, int h, ActionListener action) {
        JButton btn = new JButton();
        btn.setBounds(x, y, w, h);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.addActionListener(action);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InchaseMain::new);
    }

}
