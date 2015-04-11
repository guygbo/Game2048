package guygbo.games.game2048;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IO {   
    public static final String FILE_SAVE = "save.txt";
    public static final String FILE_RECORD = "record.txt";
  
    public static int readHighestScore() {
        int highestScore;
        Scanner recordFile = null;
        try {
            recordFile = new Scanner(new FileInputStream(FILE_RECORD));
            if (recordFile.hasNextInt()) {
                highestScore = recordFile.nextInt();
                recordFile.close();
            } else {
                highestScore = 0;
            }
        } catch (FileNotFoundException e) {
            highestScore = 0;
            File outFile = new File(FILE_RECORD);
            try {
                outFile.createNewFile();
            } catch (IOException e2) {
                e2.toString();
            }
        }
        return highestScore;
    }
    
    public static void writeNewHighestScore(int newHighestScore) {
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new FileOutputStream(FILE_RECORD));
            outFile.println(newHighestScore);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            outFile.println(dateFormat.format(date));
            outFile.close();
        } catch (FileNotFoundException e) {
        }
    }

}
