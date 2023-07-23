import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener{

        private WorkArea workArea;
        private JCheckBox clusterCheckbox, lineCheckbox;
        //private ClusterHandler c;

        public App() {
                super("Final Assignment");
                //c = new ClusterHandler();
                //InstructionList.getInstance().addObserver(c);

                // upper panel
                JPanel upperPanel = new JPanel(new GridLayout(2, 18));
                JLabel title = new JLabel("Spider World");
                JLabel leveltext = new JLabel("Level");
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
                JButton step = new JButton("Step");
                JButton turn = new JButton("Turn");
                JButton red = new JButton("Red");
                JButton blue = new JButton("Blue");
                JButton green = new JButton("Green");
                JButton black = new JButton("Black");
                JButton restartLevel = new JButton("Restart Level");
                JButton directions = new JButton("Directions");
                upperPanel.add(title);
                upperPanel.add(leveltext);
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
                upperPanel.add(step);
                upperPanel.add(turn);
                upperPanel.add(red);
                upperPanel.add(blue);
                upperPanel.add(green);
                upperPanel.add(black);
                upperPanel.add(restartLevel);
                upperPanel.add(directions);

                // left panel
                // will be dynamically resized
                setLayout(new BorderLayout());
                JPanel westPanel = new JPanel(new GridLayout(10,7));
                westPanel.setBackground(Color.WHITE);

                JButton exampleButton = new JButton("Example for World");
                westPanel.add(exampleButton);

                // right panel





                // Group the checkboxes
                ButtonGroup group = new ButtonGroup();
                group.add(clusterCheckbox);
                group.add(lineCheckbox);

                add(westPanel,BorderLayout.WEST);
                add(upperPanel, BorderLayout.NORTH);

                // work area
                // workArea = new WorkArea();
                // workArea.setBackground(Color.GRAY);
                // add(workArea,BorderLayout.CENTER);
        }

        public static void main(String[] args) {
                App app = new App();
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setSize(1000,550);
                app.setVisible(true);
                app.setResizable(false);
                LevelHelper levels = new LevelHelper();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                /*
                if (e.getActionCommand().equals("Run")){
                        if (clusterCheckbox.isSelected()){
                                workArea.setDrawLinesFlag(false);
                                c.update(DataSource.getInstance(), workArea);
                                workArea.repaint();
                        }
                        if (lineCheckbox.isSelected()){
                                workArea.setDrawLinesFlag(true);
                                LineHandler l = new LineHandler();
                                DataSource.getInstance().addObserver(l);
                                workArea.repaint();
                        }
                }
                */

        }
}