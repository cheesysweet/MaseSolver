package GUI;

import Main.SetMaze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class handles the graphical user interface used to interact with the user to display the different mazes.
 * @author André Timan & Anton Byström.
 */

public class GuiManager extends JFrame implements ActionListener {

    private final SetMaze maze = new SetMaze();
    private String mazeName = "easyCutout7";
    private final JButton solve = new JButton("Solve");
    private final JButton checkSolved = new JButton("checkSolved");
    ImagePanel imagePanel = new ImagePanel("easyCutout7");

    /**
     * The necessary swing settings are set inside the constructor.
     * As well as adding the interactive buttons and images for display onto the frame.
     */

    public GuiManager(){
        String[] maz = {"easyCutout7" , "medRectangle7", "hardCircle7", "hardEscape7", "hardWheel7", "megaRectangle10", "megaTube7"};
        JComboBox<String> maze = new JComboBox(maz);
        
        solve.addActionListener(this);
        checkSolved.addActionListener(this);
        checkSolved.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solve);
        buttonPanel.add(checkSolved);

        maze.setSelectedIndex(0);
        maze.addActionListener(this);


        this.add(maze, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setTitle("Maze");
        this.setSize(800, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method handles actions made on the frame such as the buttons and the combo box with the mazes.
     * Whenever a button is pressed the images are set visible or invisible, depending on  what the user has prompted.
     * If a new maze is selected then the appropriate methods are called to repaint the panel and displayed the new image.
     * @param e A action.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Solve")) {
            checkSolved.setVisible(true);
            solve.setVisible(false);
            try {
                maze.readFile(mazeName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (e.getActionCommand().equals("checkSolved")) {
            imagePanel.setSolvedImage(mazeName);
        } else {
            JComboBox cb = (JComboBox)e.getSource();
            mazeName = (String)cb.getSelectedItem();
            imagePanel.setImage(mazeName);
            checkSolved.setVisible(false);
            solve.setVisible(true);
        }
        this.repaint();
    }
}
