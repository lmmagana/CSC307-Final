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
        JLabel gridPlaceholder = new JLabel("Grid goes here.");
        add(gridPlaceholder);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
