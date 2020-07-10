import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineRunnerGame extends JPanel implements ActionListener, KeyListener {

    public LineRunnerGame(){
        this.setPreferredSize(new Dimension(LineRunner.screenWidth, LineRunner.screenHeight));

        LineRunner.gameContainer.add(this);
        addKeyListener(this);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}