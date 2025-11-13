package scenes;

import javadevelop.InchaseMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoorScene extends JPanel {
    private InchaseMain main;
    private ImageIcon doorImage;
    private JLabel displayLabel;
    private StringBuilder code = new StringBuilder();
    private final String correctCode = "4278";

    public DoorScene(InchaseMain main) {
        this.main = main;
        setLayout(null);

        doorImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\doorcloseup.png");

        JLabel bg = new JLabel(doorImage);
        bg.setBounds(0, 0, 1920, 1080);
        bg.setLayout(null);
        add(bg);

       
        displayLabel = new JLabel("");
        displayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setBounds(950, 650, 200, 60);
        bg.add(displayLabel);

        
        int startX = 780, startY = 210, size = 95, gap = 8;
        for (int i = 0; i < 9; i++) {
            int num = i + 1;
            int row = i / 3, col = i % 3;

            JButton numBtn = makeInvisibleButton(
                startX + (size + gap) * col,
                startY + (size + gap) * row,
                size, size,
                String.valueOf("")
            );
            numBtn.addActionListener(e -> appendNumber(num));
            bg.add(numBtn);
        }

        
        JButton clearBtn = new JButton("초기화");
        clearBtn.setBounds(1050, 665, 120, 45);
        clearBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        clearBtn.setBackground(Color.WHITE);
        clearBtn.setForeground(Color.BLACK);
        clearBtn.addActionListener(e -> clearInput());
        bg.add(clearBtn);

        
        JButton backBtn = new JButton("뒤로가기");
        backBtn.setBounds(1740, 50, 150, 80);
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(50, 50, 50, 200));
        backBtn.addActionListener(e -> main.goBack());
        bg.add(backBtn);
    }

    private JButton makeInvisibleButton(int x, int y, int w, int h, String text) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    private void appendNumber(int num) {
        if (code.length() < 4) {
            code.append(num);
            displayLabel.setText(code.toString());
        }

        if (code.length() == 4) {
            if (code.toString().equals(correctCode)) {
                
                main.showScene("escape");
            } else {
                JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다!");
                clearInput();
            }
        }
    }

    private void clearInput() {
        code.setLength(0);
        displayLabel.setText("");
    }
}


