package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javadevelop.InchaseMain;

public class BookshelfScene extends JPanel {
    private InchaseMain main;

    // 이미지들
    private ImageIcon bookshelfCloseup;
    private ImageIcon bookshelfBoxOpen;
    private ImageIcon bookshelfBoxOpenWhiteGet;
    private ImageIcon bookshelfPaperCloseup;

    private JLabel bg;
    private String currentState;

    // 퍼즐 관련 필드
    private JTextField[] numberFields = new JTextField[4];
    private int currentIndex = 0;
    private boolean puzzleSolved = false;

    // ✨ [추가] 키패드 버튼 필드 선언
    private JButton[] numButtons = new JButton[9];
    private JButton nextBtn;
    private JButton resetBtn;
    private JButton backBtn; // 뒤로가기 버튼도 필드로 선언하여 필요시 제어 가능하게 함

    public BookshelfScene(InchaseMain main) {
        this.main = main;
        setLayout(null);

        // 이미지 로드
        bookshelfCloseup = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\bookshelfcloseup.png");
        bookshelfBoxOpen = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\bookshelfboxopen.png");
        bookshelfBoxOpenWhiteGet = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\bookshelfboxopenwhiteget.png");
        bookshelfPaperCloseup = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\bookshelfpapercloseup.png");

        // 배경
        bg = new JLabel(bookshelfCloseup);
        bg.setBounds(0, 0, 1920, 1080);
        bg.setLayout(null);
        add(bg);

        currentState = "closeup";

        // 🔹 뒤로가기 버튼 초기화 및 추가
        backBtn = new JButton("뒤로가기");
        backBtn.setBounds(1740, 50, 150, 80);
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(50, 50, 50, 200));
        backBtn.addActionListener(e -> main.goBack());
        bg.add(backBtn);

        // 🔹 클릭 감지
        bg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });

        // 🔹 퍼즐 UI 추가
        setupKeypad();
    }

    // ================= 퍼즐 UI =================
    private void setupKeypad() {
        int xStart = 1495;
        int yStart = 340;
        int size = 80, gap = 8;

        // 입력칸 (네모 4개)
        for (int i = 0; i < 4; i++) {
            numberFields[i] = new JTextField();
            numberFields[i].setBounds(1495 + i * 90, 340, 80, 80);
            numberFields[i].setHorizontalAlignment(JTextField.CENTER);
            numberFields[i].setFont(new Font("맑은 고딕", Font.BOLD, 40));
            numberFields[i].setEditable(false);
            bg.add(numberFields[i]);
        }

        // 키패드 (1~9)
        int startX = 1495, startY = 430;
        for (int i = 0; i < 9; i++) {
            int num = i + 1;
            int row = i / 3, col = i % 3;

            // ✨ [수정] 버튼을 필드 배열에 저장
            numButtons[i] = new JButton(String.valueOf(num));
            numButtons[i].setBounds(startX + (size + gap) * col, startY + (size + gap) * row, size, size);
            numButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 25));
            numButtons[i].addActionListener(e -> addNumber(num));
            bg.add(numButtons[i]);
        }

        // 다음칸 버튼
        nextBtn = new JButton("다음");
        nextBtn.setBounds(1768, 430, 80, 80);
        nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nextBtn.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % 4;
        });
        bg.add(nextBtn);

        // 초기화 버튼
        resetBtn = new JButton("초기화");
        resetBtn.setBounds(1768, 550, 100, 80);
        resetBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        resetBtn.addActionListener(e -> resetPuzzle());
        bg.add(resetBtn);
    }

    private void addNumber(int num) {
        if (!puzzleSolved && currentIndex < 4) {
            numberFields[currentIndex].setText(String.valueOf(num));
        }
        checkAnswer();
    }

    private void resetPuzzle() {
        for (int i = 0; i < 4; i++) {
            numberFields[i].setText("");
        }
        currentIndex = 0;
    }

    private void checkAnswer() {
        if (puzzleSolved) return;
        
        StringBuilder sb = new StringBuilder();
        for (JTextField field : numberFields) {
            sb.append(field.getText());
        }
        
        if (sb.length() == 4 && sb.toString().equals("9658")) {
            puzzleSolved = true;
            JOptionPane.showMessageDialog(this, "잠금 해제됨!");
            
            // ✨ [수정] 잠금 해제 시 키패드 컴포넌트 숨기기
            for (JTextField field : numberFields) {
                field.setVisible(false);
            }
            for (JButton btn : numButtons) {
                btn.setVisible(false);
            }
            nextBtn.setVisible(false);
            resetBtn.setVisible(false);
        }
    }

    // ================= 클릭 이벤트 =================
    private void handleClick(int x, int y) {
        // 🟩 책장 클로즈업 상태
        if (currentState.equals("closeup")) {
            // 상자 클릭
            if (x >= 800 && x <= 1150 && y >= 841 && y <= 950) {
                if (puzzleSolved) {
                    if (main.hasWhitePotion()) {
                        bg.setIcon(bookshelfBoxOpenWhiteGet);
                        currentState = "whiteget";
                    } else {
                        bg.setIcon(bookshelfBoxOpen);
                        currentState = "boxopen";
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "상자는 잠겨 있다. 뭔가 암호가 필요해 보인다.");
                }
            }

            // 종이 클릭
            if (x >= 1180 && x <= 1310 && y >= 878 && y <= 968) {
                bg.setIcon(bookshelfPaperCloseup);
                currentState = "paper";
            }
        }

        // 🟦 상자 열림
        else if (currentState.equals("boxopen")) {
            if (x >= 850 && x <= 1000 && y >= 800 && y <= 920) {
                main.setHasWhitePotion(true);
                bg.setIcon(bookshelfBoxOpenWhiteGet);
                currentState = "whiteget";
            }
        }

        // 🟧 종이 보기 → 클릭 시 돌아감
        else if (currentState.equals("paper")) {
            bg.setIcon(bookshelfCloseup);
            currentState = "closeup";
            
            // 🐛 이전 문제 해결을 위해 repaint 호출
            bg.repaint(); 
        }
    }
}