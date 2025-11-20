package scenes;

import javadevelop.InchaseMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeskScene extends JPanel {
    private InchaseMain main;
    private JLabel bgLabel;
    private JButton resetButton;
    private JButton backBtn; 
    private String currentState = "desk";
    private boolean isCombining = false;
    private boolean isDrawerPuzzleSolved = false;
    private boolean isPotionCombined = false;
    private boolean inSmellStage = false;

    //이미지 로드
    private final ImageIcon deskImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskcloseup.png");
    private final ImageIcon drawerImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser.png");
    private final ImageIcon drawerSolvedImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deckdrowersolve.png");
    private final ImageIcon drawerSolvedEmptyImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deckdrowersolveempty.png");

    //상자 관련 이미지 
    private final ImageIcon boxImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\desktopboxcloseup.png"); 
    private final ImageIcon paperImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\desktopboxpaper.png");  
    private final ImageIcon redImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\desktopboxredget.png");     
    private final ImageIcon boxLockedImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskboxlocked.png");  
    //서랍 색상 조합 이미지 
    private final ImageIcon drawerR = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_R.png");
    private final ImageIcon drawerG = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_G.png");
    private final ImageIcon drawerB = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_B.png");
    private final ImageIcon drawerY = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_Y.png");
    private final ImageIcon drawerRR = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_RR.png");
    private final ImageIcon drawerRB = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_RB.png");
    private final ImageIcon drawerRY = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_RY.png");
    private final ImageIcon drawerRG = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_RG.png");
    private final ImageIcon drawerBB = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_BB.png");
    private final ImageIcon drawerBR = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_BR.png");
    private final ImageIcon drawerBY = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_BY.png");
    private final ImageIcon drawerBG = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_BG.png");
    private final ImageIcon drawerGY = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_GY.png");
    private final ImageIcon drawerGR = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_GR.png");
    private final ImageIcon drawerGB = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_GB.png");
    private final ImageIcon drawerGG = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_GG.png");
    private final ImageIcon drawerYY = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_YY.png");
    private final ImageIcon drawerYR = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_YR.png");
    private final ImageIcon drawerYB = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_YB.png");
    private final ImageIcon drawerYG = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskdrowser_YG.png");

    //물약 합성 관련 이미지
    private final ImageIcon deskPurpleWhite = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskpurplewhite.png");
    private final ImageIcon deskSelect = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskselect.png");
    private final ImageIcon deskWhite = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskwhite.png");
    private final ImageIcon blind1Image = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\blind1.png");
    private final ImageIcon blind2Image = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\blind2.png");
    private final ImageIcon purpleDrinkImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\purpledrink.png");
    private final ImageIcon smellImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\smell.png");
    private final ImageIcon whiteDrinkImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\whitedrink.png");
    private final ImageIcon deskCombineImage = new ImageIcon("C:\\Users\\suyou\\eclipse-workspace\\javadevelop\\src\\javagui\\deskcombine.png");

    // 메인 루프
    public DeskScene(InchaseMain main) {
        this.main = main;
        setLayout(null);

        bgLabel = new JLabel(deskImage);
        bgLabel.setBounds(0, 0, 1920, 1080);
        bgLabel.setLayout(null);
        add(bgLabel);
        // 뒤로가기 버튼
        this.backBtn = new JButton("뒤로가기");
        this.backBtn.setBounds(1740, 50, 150, 80);
        this.backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        this.backBtn.setForeground(Color.WHITE);
        this.backBtn.setBackground(new Color(50, 50, 50, 200));
        this.backBtn.addActionListener(e -> main.goBack());
        bgLabel.add(this.backBtn);
        // 초기화 버튼
        resetButton = new JButton("초기화");
        resetButton.setBounds(845, 467, 150, 70);
        resetButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        resetButton.setBackground(new Color(70, 70, 70, 200));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> resetPuzzle());
        resetButton.setVisible(false);
        bgLabel.add(resetButton);

        // 마우스 클릭 처리
        bgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                //  합성이 완료된 상태라면, 어떤 클릭이든 메인으로 복귀 처리
                if (isPotionCombined) { 
                    main.goBack();
                    return;
                }
                
                if (isCombining || inSmellStage) return; 
                int x = e.getX(), y = e.getY();
                // 상자 버튼 위치 값
                switch (currentState) {
                    case "desk" -> handleDeskClick(x, y);
                    case "box" -> handleBoxClick(x, y);
                    
                    case "box_paper" -> { 
                        if (main.hasRedPotion()) {
                            bgLabel.setIcon(redImage); 
                            currentState = "red_get";
                        } else {
                            bgLabel.setIcon(boxImage);
                            currentState = "box";
                        }
                        backBtn.setVisible(true); 
                        return; 
                    }
                    case "red_get" -> handleRedGetClick(x, y); 

                    case "drawer", "drawer_R", "drawer_G", "drawer_B", "drawer_Y",
                            "drawer_RB", "drawer_RY", "drawer_RG", "drawer_RR",
                            "drawer_BR", "drawer_BY", "drawer_BG", "drawer_BB",
                            "drawer_GR", "drawer_GY", "drawer_GG", "drawer_GB",
                            "drawer_YR", "drawer_YY", "drawer_YG", "drawer_YB"
                            -> handleDrawerClick(x, y);
                    case "drawer_solved" -> {
                        bgLabel.setIcon(drawerSolvedEmptyImage);
                        resetButton.setVisible(false);
                        currentState = "drawer_solved_empty";
                    }
                    case "drawer_solved_empty", "box_locked" -> {
                        bgLabel.setIcon(deskImage);
                        currentState = "desk";
                    }
                }
            }
        });
    }

    //외부에서 호출하여 장면 상태를 기본 책상 화면으로 초기화합니다. 
    public void resetToDeskView() {
        if (bgLabel != null) {
            bgLabel.setIcon(deskImage);
        }
        if (resetButton != null) {
            resetButton.setVisible(false);
        }
        if (backBtn != null) {
            backBtn.setVisible(true);
        }
        currentState = "desk";
        isCombining = false; 
        inSmellStage = false; 
    }
    
    //책상 클릭 처리
    private void handleDeskClick(int x, int y) {
        if (isCombining) return;

        // 상자 클릭
        if (x > 560 && x < 930 && y > 306 && y < 460) {
            if (main.hasKey()) { 
                if (main.hasRedPotion()) {
                    bgLabel.setIcon(redImage);
                    currentState = "red_get";
                } else {
                    bgLabel.setIcon(boxImage);
                    currentState = "box";
                }
            } else { 
                bgLabel.setIcon(boxLockedImage);
                currentState = "box_locked";
                JOptionPane.showMessageDialog(this, "상자는 잠겨 있다. 뭔가 열쇠가 필요해 보인다.");
            }
            return;
        }

        //서랍 클릭
        if (x > 620 && x < 1260 && y > 500 && y < 640) {
            if (isDrawerPuzzleSolved) {
                bgLabel.setIcon(drawerSolvedEmptyImage);
                resetButton.setVisible(false);
                currentState = "drawer_solved_empty";
            } else {
                bgLabel.setIcon(drawerImage);
                resetButton.setVisible(true);
                currentState = "drawer";
            }
        }
        //  물약 합성
        else if (main.hasBluePotion() && main.hasWhitePotion() && y < 400 && x > 300 && x < 1100) { 
            startCombineSequence();
        }
    }

    //상자 내부 클릭 처리
    private void handleBoxClick(int x, int y) {
        
        //붉은 물약 클릭-물약을 아직 획득하지 않았을 때만 클릭 가능
        if (!main.hasRedPotion() && x > 550 && x < 750 && y > 420 && y < 630) {
            bgLabel.setIcon(redImage); 
            main.setHasRedPotion(true); 
            currentState = "red_get"; 
            return;
        }

        // 종이 클릭
        if (x > 750 && x < 950 && y > 420 && y < 630) {
            bgLabel.setIcon(paperImage);
            currentState = "box_paper";
            backBtn.setVisible(false); 
            return;
        }
        
    }

    // 붉은 물약 획득 후 상자 화면 클릭 처리 (currentState == "red_get") 
    private void handleRedGetClick(int x, int y) {
        
        //  종이 클릭 
        if (x > 750 && x < 950 && y > 420 && y < 630) {
            bgLabel.setIcon(paperImage);
            currentState = "box_paper";
            backBtn.setVisible(false); 
            return;
        }
        
        // 종이가 아닌 다른 영역 클릭 시 
        bgLabel.setIcon(deskImage); 
        currentState = "desk"; 
        backBtn.setVisible(true); 
    }
    
    // 서랍 퍼즐 처리 
    private void handleDrawerClick(int x, int y) {
        boolean red = (x > 685 && x < 741 && y > 450 && y < 500);
        boolean green = (x > 751 && x < 790 && y > 450 && y < 500);
        boolean blue = (x > 685 && x < 741 && y > 505 && y < 550);
        boolean yellow = (x > 751 && x < 790 && y > 505 && y < 550);
        // 퍼즐 조합 조건
        if (currentState.equals("drawer")) {
            if (red) { bgLabel.setIcon(drawerR); currentState = "drawer_R"; }
            else if (green) { bgLabel.setIcon(drawerG); currentState = "drawer_G"; }
            else if (blue) { bgLabel.setIcon(drawerB); currentState = "drawer_B"; }
            else if (yellow) { bgLabel.setIcon(drawerY); currentState = "drawer_Y"; }
            return;
        }
        // 입력별로 받는 모든 경우의 수
        switch (currentState) {
            case "drawer_R" -> {
                if (blue) { bgLabel.setIcon(drawerRB); currentState = "drawer_RB"; }
                else if (yellow) { bgLabel.setIcon(drawerRY); currentState = "drawer_RY"; }
                else if (green) { bgLabel.setIcon(drawerRG); currentState = "drawer_RG"; }
                else if (red) { bgLabel.setIcon(drawerRR); currentState = "drawer_RR"; }
            }
            case "drawer_B" -> {
                if (red) { bgLabel.setIcon(drawerBR); currentState = "drawer_BR"; }
                else if (yellow) { bgLabel.setIcon(drawerBY); currentState = "drawer_BY"; }
                else if (green) { bgLabel.setIcon(drawerBG); currentState = "drawer_BG"; }
                else if (blue) { bgLabel.setIcon(drawerBB); currentState = "drawer_BB"; }
            }
            case "drawer_G" -> {
                if (yellow) { bgLabel.setIcon(drawerGY); currentState = "drawer_GY"; }
                else if (blue) { bgLabel.setIcon(drawerGB); currentState = "drawer_GB"; }
                else if (red) { bgLabel.setIcon(drawerGR); currentState = "drawer_GR"; }
                else if (green) { bgLabel.setIcon(drawerGG); currentState = "drawer_GG"; }
            }
            case "drawer_Y" -> {
                if (yellow) { bgLabel.setIcon(drawerYY); currentState = "drawer_YY"; }
                else if (blue) { bgLabel.setIcon(drawerYB); currentState = "drawer_YB"; }
                else if (red) { bgLabel.setIcon(drawerYR); currentState = "drawer_YR"; }
                else if (green) { bgLabel.setIcon(drawerYG); currentState = "drawer_YG"; }
            }
        }

        // 퍼즐 정답 (RB)
        if (currentState.equals("drawer_RB")) {
            bgLabel.setIcon(drawerSolvedImage);
            currentState = "drawer_solved";
            isDrawerPuzzleSolved = true;
            main.setHasBluePotion(true);
            resetButton.setVisible(false);
        }
    }
    // 퍼즐 초기화
    private void resetPuzzle() {
        if (!isDrawerPuzzleSolved) {
            bgLabel.setIcon(drawerImage);
            currentState = "drawer";
        }
    }

    // 물약 합성 관련 컷씬
    private void startCombineSequence() {
        if (isCombining) return;
        isCombining = true;
        
        bgLabel.removeAll(); 
        resetButton.setVisible(false);
        bgLabel.setIcon(deskPurpleWhite);
    
        addNextClick(() -> {
            bgLabel.removeAll();
            bgLabel.setIcon(deskSelect);
            addNextClick(() -> {
                bgLabel.removeAll();
                bgLabel.setIcon(deskWhite);
                // 타이머 호춣해서 1.5초 마다 해당 장면 불러오기
                Timer t1 = new Timer(1500, e -> {
                    bgLabel.setIcon(blind1Image);
                    repaint();

                    Timer t2 = new Timer(1500, e2 -> {
                        bgLabel.setIcon(blind2Image);
                        repaint();

                        Timer t3 = new Timer(1500, e3 -> showPurpleDrink());
                        t3.setRepeats(false);
                        t3.start();
                    });
                    t2.setRepeats(false);
                    t2.start();
                });
                t1.setRepeats(false);
                t1.start();
            });
        });
        repaint();
    }
    // 보라물약 마시는 장면 (클릭으로 장면 변환)
    private void showPurpleDrink() {
        bgLabel.removeAll();
        bgLabel.setIcon(purpleDrinkImage);
        addNextClick(this::showSmell);
    }
    // 실명한 장면 (버튼 눌러서 하얀 물약마시기)
    private void showSmell() {
        bgLabel.removeAll();
        bgLabel.setIcon(smellImage);
        inSmellStage = true;

        JButton drinkBtn = new JButton("하얀 물약 마시기");
        drinkBtn.setBounds(860, 900, 220, 70);
        drinkBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        drinkBtn.setBackground(Color.WHITE);
        drinkBtn.setForeground(Color.BLACK);
        drinkBtn.addActionListener(e -> showWhiteDrink());
        bgLabel.add(drinkBtn);

        repaint();
    }
    // 히얀 물약 마시고 돌아오는 컷씬
    private void showWhiteDrink() {
        inSmellStage = false;
        bgLabel.removeAll();
        bgLabel.setIcon(whiteDrinkImage);

        Timer t = new Timer(1500, e -> {
            bgLabel.setIcon(deskCombineImage);
            isPotionCombined = true; 
            isCombining = false; 

            backBtn.setVisible(true);
            
            repaint();
        });
        t.setRepeats(false);
        t.start();
    }
    // 다음장면 불러 오는 버튼
    private void addNextClick(Runnable nextAction) {
        JButton btn = makeInvisibleButton(0, 0, 1920, 1080);
        btn.addActionListener(e -> nextAction.run());
        bgLabel.add(btn);
    }
    // 버튼 투명화 
    private JButton makeInvisibleButton(int x, int y, int w, int h) {
        JButton btn = new JButton();
        btn.setBounds(x, y, w, h);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

}



