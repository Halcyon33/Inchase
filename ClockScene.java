//버튼 디테일 수정, 상자 따는거 추가 

package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javadevelop.InchaseMain;

public class ClockScene extends JPanel {
    private InchaseMain main;
    private ImageIcon clockImage, clockSolved, clockBack, clockKey, keyGet;
    private JLabel bg;
    private StringBuilder input = new StringBuilder();
    private JLabel inputLabel;
    private int stage = 0; // 0:기본, 1:solve, 2:back, 3:key, 4:keyget
    private final String ANSWER = "1024"; // 정답

    // 키패드 컴포넌트들을 필드로 선언
    private JButton[] numButtons = new JButton[9];
    private JButton zeroBtn;
    private JButton clearBtn;

    public ClockScene(InchaseMain main) {
        this.main = main;
        setLayout(null);

        // 이미지 로드
        clockImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\clockcloseup.png");
        clockSolved = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\clocksolve.png");
        clockBack = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\clockback.png");
        clockKey = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\clockkey.png");
        keyGet = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\clockkeyget.png");

        // 배경
        bg = new JLabel(clockImage);
        bg.setBounds(0, 0, 1920, 1080);
        bg.setLayout(null);
        add(bg);

        // 입력 표시창
        inputLabel = new JLabel("", SwingConstants.CENTER);
        inputLabel.setBounds(850, 350, 220, 50);
        inputLabel.setOpaque(true);
        inputLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        inputLabel.setForeground(Color.BLACK);
        bg.add(inputLabel);

        // 숫자 버튼 (0~9)
        int startX = 850, startY = 410, size = 55, gap = 8;
        for (int i = 0; i < 9; i++) {
            int num = i + 1;
            int row = i / 3, col = i % 3;

            numButtons[i] = new JButton(String.valueOf(num));
            numButtons[i].setBounds(startX + (size + gap) * col, startY + (size + gap) * row, size, size);
            numButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 25));
            numButtons[i].addActionListener(e -> appendNumber(num));
            bg.add(numButtons[i]);
        }

        // 0 버튼
        zeroBtn = new JButton("0");
        zeroBtn.setBounds(startX + (size + gap), startY + (size + gap) * 3, size, size);
        zeroBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        zeroBtn.addActionListener(e -> appendNumber(0));
        bg.add(zeroBtn);

        // 초기화 버튼
        clearBtn = new JButton("초기화");
        clearBtn.setBounds(850, 665, 120, 45);
        clearBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        clearBtn.setBackground(Color.WHITE);
        clearBtn.setForeground(Color.BLACK);
        clearBtn.addActionListener(e -> clearInput());
        bg.add(clearBtn);

        // 화면 클릭 시 다음 이미지로 전환
        bg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeImage();
            }
        });

        // 뒤로가기 버튼
        JButton backBtn = new JButton("뒤로가기");
        backBtn.setBounds(1740, 50, 150, 80);
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(50, 50, 50, 200));
        backBtn.addActionListener(e -> main.goBack());
        bg.add(backBtn);
    }
    
    // 숫자 입력 처리
    private void appendNumber(int num) {
        if (stage == 1) return; // 정답 후 추가 입력 방지

        input.append(num);
        inputLabel.setText(input.toString());

        if (input.toString().equals(ANSWER)) {
            bg.setIcon(clockSolved);
            stage = 1;
            inputLabel.setText("정답");
            
            // ✨ [수정] 정답 시 키패드와 정답창 숨기기
            hideKeypad();

        } else if (input.length() >= 4) {
            inputLabel.setText("오답");
        }
    }

    // 입력 초기화
    private void clearInput() {
        input.setLength(0);
        inputLabel.setText("");
        bg.setIcon(clockImage);
        stage = 0;
        
        // ✨ [수정] 초기화 시 키패드와 정답창 다시 보이게
        showKeypad();
    }

    // 키패드와 입력창을 숨기는 헬퍼 메서드
    private void hideKeypad() {
        for (JButton btn : numButtons) {
            btn.setVisible(false);
        }
        zeroBtn.setVisible(false);
        clearBtn.setVisible(false);
        inputLabel.setVisible(false); // ✨ [추가] 정답창 숨기기
    }

    // 키패드와 입력창을 보이게 하는 헬퍼 메서드
    private void showKeypad() {
        for (JButton btn : numButtons) {
            btn.setVisible(true);
        }
        zeroBtn.setVisible(true);
        clearBtn.setVisible(true);
        inputLabel.setVisible(true); // ✨ [추가] 정답창 다시 보이기
    }

    // 이미지 클릭 전환
    private void changeImage() {
        if (stage == 1) { // clocksolve -> clockback
            bg.setIcon(clockBack);
            stage = 2;
        } else if (stage == 2) { // clockback -> clockkey
            bg.setIcon(clockKey);
            stage = 3;
        } else if (stage == 3) { // clockkey -> keyget
            bg.setIcon(keyGet);
            stage = 4;
            main.setHasKey(true); 
        }
    }
}