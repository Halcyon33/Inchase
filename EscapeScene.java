package scenes;

import javadevelop.InchaseMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EscapeScene extends JPanel {
    private InchaseMain main;
    private JLabel bgLabel;
    
    private final String[] endingImages = {
        "C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\escape_success.png",
        "C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\ending_1.png",
        "C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\ending_2.png",
        "C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\ending_3.png",
        "C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\ending_4.png"
    };
    // 상태 값 
    private int currentIndex = 0;
    // 메인 루프
    public EscapeScene(InchaseMain main) {
        this.main = main;
        setLayout(null);

        
        bgLabel = new JLabel(new ImageIcon(endingImages[0]));
        bgLabel.setBounds(0, 0, 1920, 1080);
        bgLabel.setLayout(null);
        add(bgLabel);

        
        Timer sequenceTimer = new Timer(2000, e -> showNextImage()); 
        sequenceTimer.setRepeats(true);
        sequenceTimer.start();
    }
    // 상태값 비교 하면서 다음 장면 불러오기
    private void showNextImage() {
        currentIndex++;

        if (currentIndex < endingImages.length) {
            bgLabel.setIcon(new ImageIcon(endingImages[currentIndex]));
        } else {            
            addExitButton();
        }

        repaint();
    }
    // 게임 종료 버튼
    private void addExitButton() {
        JButton exitBtn = new JButton("게임 종료");
        exitBtn.setBounds(860, 900, 200, 60);
        exitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 23));
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setForeground(Color.BLACK);
        exitBtn.addActionListener(e -> System.exit(0));

        bgLabel.add(exitBtn);
        repaint();
    }

}

