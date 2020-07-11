import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

public class LineRunnerGame extends JPanel implements ActionListener, KeyListener {


    private JLabel walking1;
    private JLabel walking2;
    private JLabel jump1;
    private JLabel jump2;
    Timer timerWalk = new Timer(300,this);
    Timer timerJump = new Timer(1, this);
    int walkCounter = 0;
    int jumpCounter = 0;
    double jumpInitial = -0.7826;
    double gravity = 0.001225;
    int yinitial = 440;
    int y = yinitial;

    public LineRunnerGame(){



        this.setPreferredSize(new Dimension(LineRunner.screenWidth, LineRunner.screenHeight));

        LineRunner.gameContainer.add(this);
        addKeyListener(this);
        ImageIcon walk1Orig = new ImageIcon("src/walk1.png");
        Image walk1OrigImg = walk1Orig.getImage();
        Image walk1NewImg = walk1OrigImg.getScaledInstance(323,405, Image.SCALE_SMOOTH);
        walking1 = new JLabel(new ImageIcon(walk1NewImg));
        walking1.setSize(323,405);
        walking1.setLocation(50,400);
        ImageIcon walk2Orig = new ImageIcon("src/walk2.png");
        Image walk2OrigImg = walk2Orig.getImage();
        Image walk2NewImg = walk2OrigImg.getScaledInstance(338,442, Image.SCALE_SMOOTH);
        walking2 = new JLabel(new ImageIcon(walk2NewImg));
        walking2.setSize(338,442);
        walking2.setLocation(50,370);
        ImageIcon jump1Orig = new ImageIcon("src/jump1.png");
        Image jump1OrigImg = jump1Orig.getImage();
        Image jump1NewImg = jump1OrigImg.getScaledInstance(375,360, Image.SCALE_SMOOTH);
        jump1 = new JLabel(new ImageIcon(jump1NewImg));
        jump1.setSize(375,340);
        jump1.setLocation(35,y);
        ImageIcon jump2Orig = new ImageIcon("src/jump2.png");
        Image jump2OrigImg = jump2Orig.getImage();
        Image jump2NewImg = jump2OrigImg.getScaledInstance(375,368, Image.SCALE_SMOOTH);
        jump2 = new JLabel(new ImageIcon(jump2NewImg));
        jump2.setSize(375,368);
        jump2.setLocation(35,y);
        timerWalk.start();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setVisible(true);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,70,LineRunner.screenWidth,5);


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == (KeyEvent.VK_UP)) {
            timerWalk.stop();


            timerJump.start();


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if (action.equals(timerWalk)){
            this.remove(jump2);
            this.remove(jump1);
            if (walkCounter % 2 == 0){
                this.remove(walking1);
                this.add(walking2);
                this.revalidate();
                this.repaint();
            } else {
                this.remove(walking2);
                this.add(walking1);
                this.revalidate();
                this.repaint();
            }
            walkCounter++;
        } else if (action.equals(timerJump)){
            double delY = (jumpInitial*(jumpCounter)) + (0.5*gravity*jumpCounter*jumpCounter);
            double Velocity = jumpInitial + gravity*jumpCounter;
            int change = (int) delY;
            y = yinitial + change;
            this.remove(walking1);
            this.remove(walking2);
            if (Velocity >=  0){
                this.remove(jump1);
                jump2.setLocation(30,y);
                this.add(jump2);
            } else {
                this.remove(jump2);
                jump1.setLocation(30,y);
                this.add(jump1);
            }
            this.revalidate();
            this.repaint();

            jumpCounter++;
            if (y > yinitial){
                timerJump.stop();
                y = yinitial;
                jumpCounter = 0;
                timerWalk.start();

            }
        }


    }
    
}