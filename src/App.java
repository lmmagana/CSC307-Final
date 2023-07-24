import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class App extends JFrame implements ActionListener, ChangeListener {

        private ConnectHelper c;

        public App() {
                super("Spider World");
                c = new ConnectHelper();
                //l = new LevelHelper();
                //InstructionList.getInstance().addObserver(c);
                //InstructionList.getInstance().addObserver(l);

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
                WorldPanel worldPanel = new WorldPanel();
                worldPanel.setBackground(Color.WHITE);
                worldPanel.setBounds(0, 100, 375, 750);
                add(worldPanel);

                // play and speed buttons
                JPanel worldPlaySpeedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                worldPlaySpeedPanel.setBackground(Color.WHITE);
                worldPlaySpeedPanel.setBounds(375, 100, 375, 750);
                add(worldPlaySpeedPanel);

                JButton playButton = new JButton("Play");
                worldPlaySpeedPanel.add(playButton);

                JSlider speedSlider = new JSlider(1, 10);
                worldPlaySpeedPanel.add(speedSlider);

                playButton.addActionListener(this);
                speedSlider.addChangeListener(this);

                // right side
                // workArea
                WorkArea workAreaPanel = new WorkArea();
                workAreaPanel.setBounds(750, 100, 750, 750);
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
                instructions.addStep();
                instructions.addStep();
                instructions.addTurn();
                instructions.addRepeatUntilColor();
                LinkedList<Instruction> loop1 = instructions.getInstruction(3).getRepeatInstructions();
                instructions.addStep(loop1);
                instructions.addTurn(loop1);
                instructions.addRepeatUntilColor(loop1);
                instructions.addPaintGreen(loop1.getLast().getRepeatInstructions());
                instructions.addRepeatUntilColor();
                instructions.addStep(instructions.getLast().getRepeatInstructions());
                instructions.addTurn();


        }

        @Override
        public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                if (e.getSource().getClass().getName().equals("javax.swing.JButton")) {

                        switch(((JButton) e.getSource()).getText()){
                                case("Directions"):
                                        JOptionPane.showMessageDialog(null, "Welcome to Spider World!\n" +
                                                "Use the 'step' and 'turn' buttons to guide the spider around her room.\n" +
                                                "A color button paints the square the spider is standing on. Can you guide the spider to paint the squares marked with diamonds?",
                                            "Directions", JOptionPane.PLAIN_MESSAGE);
                                        break;
                                case("Step"):
                                        System.out.println("Step spider");
                                        InstructionList.getInstructions().addStep();
                                        // world.step();
                                        // or whatever it is supposed to be
                                        break;
                                case("Turn"):
                                        System.out.println("Turn spider");
                                        InstructionList.getInstructions().addTurn();
                                        // world.red();
                                        // or whatever it is supposed to be
                                        break;
                                case("Red"):
                                        System.out.println("Paint red");
                                        InstructionList.getInstructions().addPaintRed();
                                        // world.red();
                                        // or whatever it is supposed to be
                                        break;
                                case("Blue"):
                                        System.out.println("Paint Blue");
                                        InstructionList.getInstructions().addPaintBlue();
                                        // world.blue();
                                        // or whatever it is supposed to be
                                        break;
                                case("Green"):
                                        System.out.println("Paint Green");
                                        InstructionList.getInstructions().addPaintGreen();
                                        // world.green();
                                        // or whatever it is supposed to be
                                        break;
                                case("Black"):
                                        System.out.println("Paint Black");
                                        InstructionList.getInstructions().addPaintBlack();
                                        // world.black();
                                        // or whatever it is supposed to be
                                        break;
                                case("Play"):
                                        System.out.println("Play world");
                                        // world.play();
                                        // or whatever it is supposed to be
                                        break;
                        }



                } else if(e.getSource().getClass().getName().equals("javax.swing.JSlider")) {
                        System.out.println("Slider was moved.");
                }

        }

        @Override
        public void stateChanged(ChangeEvent e) {
                System.out.println(e.getSource());
        }
}