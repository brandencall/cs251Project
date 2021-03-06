import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class InstructionScreen extends JPanel implements ActionListener, KeyListener {

    private String jumpInstructions;
    private String duckInstructions;
    private String keyPressInstruction;

    public InstructionScreen() {

        jumpInstructions = "Press the space bar or up arrow key to jump over objects";
        duckInstructions = "Press the down arrow key to duck under objects";
        keyPressInstruction = "To start the game press the space bar";

        this.setPreferredSize(new Dimension(LineRunner.screenWidth, LineRunner.screenHeight));

        LineRunner.gameContainer.add(this);
        addKeyListener(this);
    
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Times New Roman", Font.BOLD, 100));
        g.setColor(Color.BLACK);
        g.drawString("Instructions", 100, 100);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        g.drawString(jumpInstructions, 100, 250);

        g.drawString(duckInstructions, 100, 300);

        g.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        g.drawString(keyPressInstruction, 100, 400);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            LineRunner.gameContainer.remove(this);
            LineRunner.gameContainer.add(new LineRunnerGame());
            LineRunner.gameContainer.revalidate();
            LineRunner.gameContainer.repaint();
        }     
    }
}