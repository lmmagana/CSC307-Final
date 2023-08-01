import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class App extends JFrame implements ActionListener, ChangeListener {

        private WorkArea workAreaPanel = new WorkArea();
        private ConnectHelper c;
        private WorldPanel worldPanel;
        private Run buttonRun;
        private int checkF;

        public App() {
                super("Spider World");
                c = new ConnectHelper();
                buttonRun = Run.getInstance();
                checkF = 0;
                
                worldPanel = new WorldPanel();
                LevelHelper levels = LevelHelper.getLevels(); //Singleton of levels
                Run execute = Run.getInstance(); //Singleton of run instructions
                levels.addObserver(worldPanel); //worldPanel redraws when level changes
                execute.addObserver(worldPanel); //worldPanel redraws during execution

                // upper panel
                JPanel upperPanel = new JPanel();
                upperPanel.setBackground(Color.WHITE);
                upperPanel.setBounds(0, 0, 1500, 50);
                // upperPanel.setLayout(new GridLayout(1, 19));
                add(upperPanel);

                JLabel title = new JLabel("Spider World");
                upperPanel.add(title);
                title.setFont(new Font("Calibri", Font.BOLD, 20));

                JLabel level = new JLabel("Level");
                upperPanel.add(level);

                JButton levelOne = new JButton("1");
                JButton levelTwo = new JButton("2");
                JButton levelThree = new JButton("3");
                JButton levelFour = new JButton("4");
                JButton levelFive = new JButton("5");
                JButton levelSix = new JButton("6");
                JButton levelSeven = new JButton("7");
                JButton levelEight = new JButton("8");
                JButton levelNine = new JButton("9");
                JButton levelTen = new JButton("10");
                JButton levelEleven = new JButton("11");
                JButton levelTwelve = new JButton("12");
                JButton levelThirteen = new JButton("13");
                JButton levelFourteen = new JButton("14");
                JButton levelFifteen = new JButton("15");

                upperPanel.add(levelOne);
                upperPanel.add(levelTwo);
                upperPanel.add(levelThree);
                upperPanel.add(levelFour);
                upperPanel.add(levelFive);
                upperPanel.add(levelSix);
                upperPanel.add(levelSeven);
                upperPanel.add(levelEight);
                upperPanel.add(levelNine);
                upperPanel.add(levelTen);
                upperPanel.add(levelEleven);
                upperPanel.add(levelTwelve);
                upperPanel.add(levelThirteen);
                upperPanel.add(levelFourteen);
                upperPanel.add(levelFifteen);

                JButton directionsButton = new JButton("Directions");
                upperPanel.add(directionsButton);

                levelOne.addActionListener(this);
                levelTwo.addActionListener(this);
                levelThree.addActionListener(this);
                levelFour.addActionListener(this);
                levelFive.addActionListener(this);
                levelSix.addActionListener(this);
                levelSeven.addActionListener(this);
                levelEight.addActionListener(this);
                levelNine.addActionListener(this);
                levelTen.addActionListener(this);
                levelEleven.addActionListener(this);
                levelTwelve.addActionListener(this);
                levelThirteen.addActionListener(this);
                levelFourteen.addActionListener(this);
                levelFifteen.addActionListener(this);
                directionsButton.addActionListener(this);

                // left side
                // panel with the world buttons
                JPanel worldButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                worldButtonPanel.setBackground(Color.WHITE);
                worldButtonPanel.setBounds(0, 50, 750, 50);
                add(worldButtonPanel);

                JButton stepButton = new JButton("Step");
                JButton turnButton = new JButton("Turn");
                JButton redButton = new JButton("Red");
                redButton.setBackground(Color.RED);
                redButton.setOpaque(true);
                redButton.setBorderPainted(false);
                JButton blueButton = new JButton("Blue");
                blueButton.setForeground(Color.WHITE);
                blueButton.setBackground(Color.BLUE);
                blueButton.setOpaque(true);
                blueButton.setBorderPainted(false);
                JButton greenButton = new JButton("Green");
                greenButton.setBackground(Color.GREEN);
                greenButton.setOpaque(true);
                greenButton.setBorderPainted(false);
                JButton blackButton = new JButton("Black");
                blackButton.setBackground(Color.BLACK);
                blackButton.setForeground(Color.WHITE);
                blackButton.setOpaque(true);
                blackButton.setBorderPainted(false);

                worldButtonPanel.add(stepButton);
                worldButtonPanel.add(turnButton);
                worldButtonPanel.add(redButton);
                worldButtonPanel.add(blueButton);
                worldButtonPanel.add(greenButton);
                worldButtonPanel.add(blackButton);

                stepButton.addActionListener(this);
                turnButton.addActionListener(this);
                redButton.addActionListener(this);
                blueButton.addActionListener(this);
                greenButton.addActionListener(this);
                blackButton.addActionListener(this);

                // panel for the world
                //worldPanel = new WorldPanel();
                worldPanel.setBackground(Color.WHITE);
                worldPanel.setBounds(0, 100, 500, 750);
                add(worldPanel);

                // play and speed buttons
                JPanel worldPlaySpeedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                worldPlaySpeedPanel.setBackground(Color.WHITE);
                worldPlaySpeedPanel.setBounds(500, 100, 375, 750);
                add(worldPlaySpeedPanel);

                JButton playButton = new JButton("Play");
                worldPlaySpeedPanel.add(playButton);

                JSlider speedSlider = new JSlider(1, 10);
                speedSlider.setBorder(BorderFactory.createTitledBorder("Speed Slider"));
                speedSlider.setMajorTickSpacing(1);
                speedSlider.setMinorTickSpacing(1);
                speedSlider.setPaintTicks(true);
                speedSlider.setPaintLabels(true);
                worldPlaySpeedPanel.add(speedSlider);

                playButton.addActionListener(this);
                speedSlider.addChangeListener(this);

                // right side
                // workArea
                // WorkArea workAreaPanel = new WorkArea();
                workAreaPanel.setBounds(875, 100, 625, 750);
                add(workAreaPanel);

                // host the restart button
                JPanel restartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                restartPanel.setBackground(Color.WHITE);
                restartPanel.setBounds(750, 50, 750, 50);
                add(restartPanel);

                JButton restartButton = new JButton("Restart Level");
                restartPanel.add(restartButton);

                restartButton.addActionListener(this);
        }

        public static void main(String[] args) {
                App app = new App();
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setLayout(null);
                app.setSize(1500,800);
                app.setVisible(true);
                app.setResizable(false);
                LevelHelper levels = LevelHelper.getLevels(); //Singleton of levels
                InstructionList instructions = InstructionList.getInstructions(); //Singleton of Instructions
                Run result = Run.getInstance(); //Singleton of Run
                levels.addObserver(result); //Observer updates level info within execution when level changes

                //TEST Level 11 W/ Nested Loops and Repeat Until Color
//                instructions.clearInstructionList();
//                levels.changeCurrentLevel(11);
//                instructions.addRepeatUntilColor(Color.RED);
//                LinkedList<Instruction> tillColor = instructions.getLast().getRepeatInstructions();
//                instructions.addRepeatUntilWall(tillColor);
//                LinkedList<Instruction> tillWall = tillColor.getLast().getRepeatInstructions();
//                instructions.addPaintRed(tillWall);
//                instructions.addStep(tillWall);
//                instructions.addTurn(tillColor);
                //ENDOFTEST
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                //System.out.println(e.getActionCommand());
                playSound("./images/ButtonClick.wav");
                checkF++;
                Instruction tmp;
                LevelHelper levels = LevelHelper.getLevels();
                if (e.getSource().getClass().getName().equals("javax.swing.JButton")) {
                        switch(((JButton) e.getSource()).getText()){
                                case("Directions"):
                                        playSound("./images/PopUp.wav");
                                        JOptionPane.showMessageDialog(null, "Welcome to Spider World!\n" +
                                                "Use the 'step' and 'turn' buttons to guide the spider around her room.\n" +
                                                "A color button paints the square the spider is standing on. Can you guide the spider to paint the squares marked with diamonds?",
                                            "Directions", JOptionPane.PLAIN_MESSAGE);
                                        break;
                                case("Step"):
                                        //InstructionList.getInstructions().addStep();
                                        tmp = new Instruction("Step");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Step", 200, 25);
                                        workAreaPanel.repaint();
                                        break;
                                case("Turn"):
                                        //InstructionList.getInstructions().addTurn();
                                        tmp = new Instruction("Turn");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Turn", 200, 75);
                                        workAreaPanel.repaint();
                                        break;
                                case("Red"):
                                        //InstructionList.getInstructions().addPaintRed();
                                        tmp = new Instruction("Paint Red");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Paint Red", 200, 125);
                                        workAreaPanel.repaint();
                                        break;
                                case("Blue"):
                                        //InstructionList.getInstructions().addPaintBlue();
                                        tmp = new Instruction("Paint Blue");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Paint Blue", 200, 175);
                                        workAreaPanel.repaint();
                                        break;
                                case("Green"):
                                        //InstructionList.getInstructions().addPaintGreen();
                                        tmp = new Instruction("Paint Green");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Paint Green", 200, 225);
                                        workAreaPanel.repaint();
                                        break;
                                case("Black"):
                                        //InstructionList.getInstructions().addPaintBlack();
                                        tmp = new Instruction("Paint Black");
                                        buttonRun.runOneInstruction(tmp, checkF);
                                        workAreaPanel.addFromButton("Paint Black", 200, 275);
                                        workAreaPanel.repaint();
                                        break;
                                case("Play"):
                                        if(Run.getInstance().flag) return;
                                        System.out.println(InstructionList.getInstructions());
                                        Run play = Run.getInstance();
                                        play.execute();
                                        break;
                                case("Restart Level"):
                                        //if(Run.getInstance().flag) return;
                                        restart();
                                        break;
                                case("1"):
                                        levels.changeCurrentLevel(1);
                                        break;
                                case("2"):
                                        levels.changeCurrentLevel(2);
                                        break;
                                case("3"):
                                        levels.changeCurrentLevel(3);
                                        break;
                                case("4"):
                                        levels.changeCurrentLevel(4);
                                        break;
                                case("5"):
                                        levels.changeCurrentLevel(5);
                                        break;
                                case("6"):
                                        levels.changeCurrentLevel(6);
                                        break;
                                case("7"):
                                        levels.changeCurrentLevel(7);
                                        break;
                                case("8"):
                                        levels.changeCurrentLevel(8);
                                        break;
                                case("9"):
                                        levels.changeCurrentLevel(9);
                                        break;
                                case("10"):
                                        levels.changeCurrentLevel(10);
                                        break;
                                case("11"):
                                        levels.changeCurrentLevel(11);
                                        break;
                                case("12"):
                                        levels.changeCurrentLevel(12);
                                        break;
                                case("13"):
                                        levels.changeCurrentLevel(13);
                                        break;
                                case("14"):
                                        levels.changeCurrentLevel(14);
                                        break;
                                case("15"):
                                        levels.changeCurrentLevel(15);
                                        break;
                        }
                } else if(e.getSource().getClass().getName().equals("javax.swing.JSlider")) {
                        System.out.println("Slider was moved."); }
        }

        private void restart(){
                LevelHelper levels = LevelHelper.getLevels();
                InstructionList.getInstructions().clearInstructionList();
                levels.changeCurrentLevel(levels.getCurrentLevel());
                workAreaPanel.getDragPanel().clearBoard();
                workAreaPanel.initialize();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
                LevelHelper.getLevels().changeRunSpeed(((JSlider) e.getSource()).getValue());
        }

        private void playSound(String soundFileName) {
                try {
                        File audioFile = new File(soundFileName);
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                }
        }
}
