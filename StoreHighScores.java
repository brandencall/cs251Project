import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class StoreHighScores {

    private int MAX = 10;

    private static int currentScore = 0;
    private static int highScore = -1;

    public static void updateHighScores() {
        currentScore = LineRunnerGame.score;
        System.out.println(currentScore);

        String subDirct = Paths.get("").toUri().toString();
        String subDirct1 = subDirct.substring(8);

        String subDirct2 = subDirct1.replace("%20", " ");

        File file = new File(subDirct2 + "highScores.txt");
        BufferedReader BR = null;
        try {
            BR = new BufferedReader(new FileReader(file));
            String token = BR.readLine();

            if (token != null) {
                highScore = Integer.parseInt(token);
                System.out.println(highScore);
            }

            if (highScore < currentScore) {

                FileWriter fw = new FileWriter(subDirct2 + "highScores.txt", false);
                fw.write("" + currentScore);
                highScore = currentScore;
                fw.close();
                BR.close();


            }
        } catch (IOException e){

        }
    }

    public static String getScoreInfo(){
        return "Your score: " + currentScore + " High Score: " + highScore;
    }


}
