import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPanel extends JPanel implements ActionListener, ChangeListener {

    public WorldPanel(){

        // left side
        // panel with the world buttons
        /*JLabel gridPlaceholder = new JLabel("Grid goes here.");
        add(gridPlaceholder);
        */

        setLayout(null);

        World world = new World(5);
        world.setBounds(40, 10, 300, 300);
        world.setBackground(Color.BLACK);
        add(world);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
