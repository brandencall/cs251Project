import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class StoreHighScores {

    private int MAX = 10;

    private static int currentScore = 0;
    private static int highScore = 0;

    public static void updateHighScores() throws IOException {
        currentScore = LineRunnerGame.score;
        Scanner reader = new Scanner(new File("src/highScores.txt"));
        String token = reader.next();
        
        if(token != null){
            highScore =  Integer.parseInt(token);
        }

        if(highScore < currentScore) {
//            System.out.println("highscore < currentscore");
//            System.out.println(currentScore);
            FileWriter fw = new FileWriter("src/highScores.txt", false);
            fw.write("" + currentScore);
            highScore = currentScore;
            fw.close();

        }
    }

    public static String getScoreInfo(){
        return "Your score: " + currentScore + " High Score: " + highScore;
    }


}

