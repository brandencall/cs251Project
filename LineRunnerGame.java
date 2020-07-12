import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Paths;


import javax.swing.*;

public class LineRunnerGame extends JPanel implements ActionListener, KeyListener {


    private JLabel walking1;
    private JLabel walking2;
    private JLabel jump1;
    private JLabel jump2;
    private JLabel duck;
    private JLabel trashObstacle;
    private JLabel bird1Obstacle;
    private JLabel bird2Obstacle;
    private Rectangle playerHitBox;
    private Rectangle trashHitBox;
    private Rectangle birdHitBox;
    private Timer timerWalk = new Timer(5,this);
    private Timer timerJump = new Timer(1, this);
    private Timer timerObstacle = new Timer(5, this);
    private int walkCounter = 0;
    private int jumpCounter = 0;
    private double backgroundVel = 2;
    private double getBackgroundVelInitial = 2;
    private int backgroundCounter = 0;
    private double jumpInitial = -1;
    private double gravity = 0.001875;
    private int yinitial = 440;
    private int y = yinitial;
    public static int score = 0;
    private int trashRandY = 600;
    private int trashX = 1000;
    private int trashSpawn = 1000;
    private int trashBackgroundCounter;
    private int newTrashX = 1000;
    private int birdSpawn = 0;
    private int birdX = 1000;
    private int birdRandY = 300;
    private int birdBackgroundCounter;
    private int newBirdX = 1000;
    private boolean birdChange = true;
    private boolean nextPhase = false;


    public LineRunnerGame(){



        this.setPreferredSize(new Dimension(LineRunner.screenWidth, LineRunner.screenHeight));

        LineRunner.gameContainer.add(this);
        addKeyListener(this);
        String subDirct = Paths.get("").toUri().toString();
        String subDirct1 = subDirct.substring(8);
        System.out.println(subDirct1);



        ImageIcon walk1Orig = new ImageIcon( subDirct1 + "/Images/walk1.png");
        Image walk1OrigImg = walk1Orig.getImage();
        Image walk1NewImg = walk1OrigImg.getScaledInstance(323,405, Image.SCALE_SMOOTH);
        walking1 = new JLabel(new ImageIcon(walk1NewImg));
        walking1.setSize(323,405);
        walking1.setLocation(50,400);
        ImageIcon walk2Orig = new ImageIcon(subDirct1+ "/Images/walk2.png");
        Image walk2OrigImg = walk2Orig.getImage();
        Image walk2NewImg = walk2OrigImg.getScaledInstance(338,442, Image.SCALE_SMOOTH);
        walking2 = new JLabel(new ImageIcon(walk2NewImg));
        walking2.setSize(338,442);
        walking2.setLocation(50,370);
        ImageIcon jump1Orig = new ImageIcon(subDirct1 +"/Images/jump1.png");
        Image jump1OrigImg = jump1Orig.getImage();
        Image jump1NewImg = jump1OrigImg.getScaledInstance(375,360, Image.SCALE_SMOOTH);
        jump1 = new JLabel(new ImageIcon(jump1NewImg));
        jump1.setSize(375,340);
        jump1.setLocation(35,y);
        ImageIcon jump2Orig = new ImageIcon(subDirct1 + "/Images/jump2.png");
        Image jump2OrigImg = jump2Orig.getImage();
        Image jump2NewImg = jump2OrigImg.getScaledInstance(375,368, Image.SCALE_SMOOTH);
        jump2 = new JLabel(new ImageIcon(jump2NewImg));
        jump2.setSize(375,348);
        jump2.setLocation(35,y);
        ImageIcon duckOrig = new ImageIcon(subDirct1 + "/Images/Duck.png");
        Image duckOrigImg = duckOrig.getImage();
        Image duckNewImg = duckOrigImg.getScaledInstance(435,240, Image.SCALE_SMOOTH);
        duck = new JLabel(new ImageIcon(duckNewImg));
        duck.setSize(435,240);
        duck.setLocation(35,510);
        ImageIcon trashOrig = new ImageIcon(subDirct1 + "/Images/trash.png");
        Image trashOrigImg = trashOrig.getImage();
        Image trashNewImg = trashOrigImg.getScaledInstance(135,165, Image.SCALE_SMOOTH);
        trashObstacle = new JLabel(new ImageIcon(trashNewImg));
        trashObstacle.setSize(135,165);
        trashObstacle.setLocation(trashX, trashRandY);
        ImageIcon bird1Orig = new ImageIcon(subDirct1 + "/Images/bird1.png");
        Image bird1OrigImg = bird1Orig.getImage();
        Image bird1NewImg = bird1OrigImg.getScaledInstance(135,60, Image.SCALE_SMOOTH);
        bird1Obstacle = new JLabel(new ImageIcon(bird1NewImg));
        bird1Obstacle.setSize(135,60);
        bird1Obstacle.setLocation(birdX, birdRandY);
        ImageIcon bird2Orig = new ImageIcon(subDirct1 +"/Images/bird2.png");
        Image bird2OrigImg = bird2Orig.getImage();
        Image bird2NewImg = bird2OrigImg.getScaledInstance(142,90, Image.SCALE_SMOOTH);
        bird2Obstacle = new JLabel(new ImageIcon(bird2NewImg));
        bird2Obstacle.setSize(142,90);
        bird2Obstacle.setLocation(birdX, birdRandY);
        this.add(trashObstacle);
        playerHitBox = new Rectangle(200, 430, 120, 330);




        timerWalk.start();
        timerObstacle.start();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setVisible(true);


    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,70,LineRunner.screenWidth,5);
        GradientPaint paint = new GradientPaint(0,0,new Color(54,81,214),0,LineRunner.screenHeight, new Color(114, 140, 155));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(paint);
        g2d.fillRect(0,75,LineRunner.screenWidth, LineRunner.screenHeight - 75);
        g2d.setColor(new Color(18, 94, 34));
        g2d.fillRect(0,550,LineRunner.screenWidth, LineRunner.screenHeight - 550);
        g2d.setColor(new Color(112, 114, 116, 10));
        g2d.fillRect(0,0,LineRunner.screenWidth, 70);
        g2d.setColor(new Color(0,0,0));
        g2d.setFont(new Font("Impact", Font.PLAIN, 50));
        g2d.drawString("Line Runner", 5,53);
        g2d.drawString("Score: ", 650, 53);
        g2d.drawString(Integer.toString(score), 790, 53);
//        g2d.drawString(Double.toString(backgroundVel), 50,100);
//        g2d.drawString(Integer.toString(backgroundCounter), 50,150);
//        g2d.drawString(Boolean.toString(nextPhase),50,200);
//        g2d.drawRect(playerHitBox.x,playerHitBox.y,playerHitBox.width,playerHitBox.height);






    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == (KeyEvent.VK_UP) && playerHitBox.height != 220) {
            timerWalk.stop();
            timerJump.start();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !timerJump.isRunning()){
            timerWalk.stop();
            this.remove(walking2);
            this.remove(walking1);
            this.add(duck);
            playerHitBox.height = 220;
            playerHitBox.y = 510;
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == (KeyEvent.VK_DOWN) && !timerJump.isRunning()){
            this.remove(duck);
            playerHitBox.height = 330;
            playerHitBox.y = 430;
            timerWalk.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();

        if (action.equals(timerWalk)){
            if (walkCounter <= 60){

                this.remove(walking1);
                this.add(walking2);
                this.revalidate();
                this.repaint();
            } else if (walkCounter <= 120){

                this.remove(walking2);
                this.add(walking1);
                this.revalidate();
                this.repaint();
            } else {
                walkCounter = 0;
            }
            walkCounter++;
        } else if (action.equals(timerObstacle)) {
            Rectangle result2 = new Rectangle(0,0,0,0);
            backgroundCounter ++;
            trashBackgroundCounter++;
            backgroundVel = getBackgroundVelInitial + (Math.log(backgroundCounter + 1)/10.4) + 0.00002*backgroundCounter;
            trashX = newTrashX - (int)(backgroundVel*trashBackgroundCounter);
            trashHitBox = trashObstacle.getBounds();

            if (trashX < -136){
                trashBackgroundCounter = 0;
                newTrashX = trashSpawn + (int) (Math.random()*trashSpawn);
                // rand y from 550 - 635
                trashRandY = 555 + (int) (Math.random()*80);
            }
            if(backgroundCounter == 12600){
                nextPhase = true;

            }
            if(nextPhase && trashBackgroundCounter == 0){
                this.add(bird1Obstacle);
                this.add(bird2Obstacle);
                trashSpawn = 1500;
                birdSpawn = 1700;
                birdX = 1000;
                birdBackgroundCounter = 0;
                nextPhase = false;
            }
            if(backgroundCounter > 12600 && trashSpawn == 1500){
                birdBackgroundCounter++;
                birdX = newBirdX - (int)(backgroundVel*trashBackgroundCounter);
                birdHitBox = bird2Obstacle.getBounds();
                birdHitBox.width = birdHitBox.width - 35;


                if (birdX < -142){
                    birdBackgroundCounter = 0;
                    birdX = newBirdX + (int) (Math.random()*birdSpawn);
                    birdRandY = 75 + (int) (Math.random()*(510 - birdHitBox.getHeight() - 75));
                }
                if (backgroundCounter % 60 == 0){
                    this.bird1Obstacle.setVisible(birdChange);
                    this.bird2Obstacle.setVisible(!birdChange);
                    birdChange = !birdChange;
                }
                result2 = SwingUtilities.computeIntersection(
                        (int)playerHitBox.getX(),
                        (int)playerHitBox.getY(),
                        (int) playerHitBox.getWidth(),
                        (int)playerHitBox.getHeight(),
                        birdHitBox);


            }
            bird1Obstacle.setLocation(birdX,birdRandY);
            bird2Obstacle.setLocation(birdX,birdRandY);
            trashObstacle.setLocation(trashX, trashRandY);

            Rectangle result1 = SwingUtilities.computeIntersection(
                    (int)playerHitBox.getX(),
                    (int)playerHitBox.getY(),
                    (int) playerHitBox.getWidth(),
                    (int)playerHitBox.getHeight(),
                    trashHitBox);
            if((result1.getHeight() > 0 && result1.getWidth() > 0) || (result2.getHeight() > 0 && result2.getWidth() > 0)){
                timerWalk.stop();
                timerObstacle.stop();
                timerJump.stop();
                LineRunner.gameContainer.remove(this);
                LineRunner.gameContainer.add(new DeathScreen());
                LineRunner.gameContainer.revalidate();
                LineRunner.gameContainer.repaint();
            }
            score = (int) (backgroundCounter*backgroundVel/20);
            this.revalidate();
            this.repaint();


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
            playerHitBox.y = 430 + change;
            this.revalidate();
            this.repaint();

            jumpCounter++;
            if (y > yinitial){
                timerWalk.start();
                timerJump.stop();
                this.remove(jump2);
                this.remove(jump1);
                this.add(walking1);
                playerHitBox.y = 430;
                this.revalidate();
                this.repaint();
                walkCounter = 1;
                y = yinitial;
                jumpCounter = 0;


            }
        }


    }

}