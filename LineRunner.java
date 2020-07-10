import javax.swing.JFrame;
import java.awt.Container;

public class LineRunner{

    public static int screenWidth = 1000;
    public static int screenHeight = 800;
    public static Container gameContainer;

    private JFrame gameWindow;


    public LineRunner(){
        gameWindow = new JFrame("Line Runner");
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);

        gameContainer = new Container();
        gameContainer = gameWindow.getContentPane();
        gameContainer.add(new StartMenu());

        gameWindow.pack();
    }

    public static void main(String[] args) {
        new LineRunner();
        
    }
}
