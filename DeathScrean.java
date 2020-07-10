import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DeathScrean extends JPanel implements ActionListener {

    private JButton replayButton;
    private JButton quiteButton;
    private String deathStatement;

    public DeathScrean(){
        this.setPreferredSize(new Dimension(LineRunner.screenWidth, LineRunner.screenHeight));

        LineRunner.gameContainer.add(this);

        deathStatement = "You Ded :(";

        replayButton = new JButton("Replay");
        replayButton.addActionListener(this);
        int startButtonWidth = 260;
        int startButtonHeight = 150;
        replayButton.setPreferredSize(new Dimension(startButtonWidth, startButtonHeight));
        double centerWidthStartButton = (LineRunner.screenWidth / 2) - (startButtonWidth / 2);
        replayButton.setBounds((int) centerWidthStartButton, 200, 260, 150);
        replayButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));

        quiteButton = new JButton("Exit");
        quiteButton.addActionListener(this);
        int quiteButtonWidth = 80;
        int quiteButtonHeight = 50;
        quiteButton.setPreferredSize(new Dimension(quiteButtonWidth, quiteButtonHeight));
        double centerWidthQuiteButton = (LineRunner.screenWidth/ 2) - (quiteButtonWidth/ 2);
        quiteButton.setBounds((int)centerWidthQuiteButton, 400, 100, 75);
        quiteButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        LineRunner.gameContainer.add(this);
        this.add(replayButton);
        this.add(quiteButton);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Times New Roman", Font.BOLD, 100));
        g.setColor(Color.BLACK);
        g.drawString(deathStatement, 250, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionPerformed = e.getSource();
        if(actionPerformed == replayButton){
            LineRunner.gameContainer.remove(this);
            LineRunner.gameContainer.add(new LineRunnerGame());
            LineRunner.gameContainer.revalidate();
            LineRunner.gameContainer.repaint();
        } else if(actionPerformed == quiteButton){
            System.exit(1);
        }
    }

    
    
}