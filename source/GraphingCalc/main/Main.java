package GraphingCalc.main;
import java.io.IOException;
import java.util.Scanner;
import GraphingCalc.net.objecthunter.exp4j.*;
import GraphingCalc.main.re.*;
import GraphingCalc.main.Window.*;
import java.lang.Object;
import java.util.Arrays;
import java.util.regex.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.File;


//todo: write thing to log errors to a file w/ errorstream and bufferedwriter
//todo: check compat
//todo switch draw to use System.out.bufferedwriter instead of print for drawing screen
//remember to do *bufferedwritername*.flush() to print.

public class Main extends Thread {
    private static String userInput = "";
    private static Window window = new Window();


    public static void main(String[] args) {
        try {
            File errorLog = new File("error.log");
            FileOutputStream fos = new FileOutputStream(errorLog);
            PrintStream ps = new PrintStream(fos);
            System.setErr(ps);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        


        //-ctlecho
        try {
            ProcessBuilder pb = new ProcessBuilder("stty","-icanon","-iuclc");
            pb.inheritIO();
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        window.init(re.getBoundX(),re.getBoundY(),"Graphing Calculator V1.1");
        window.moveScreen(2,2);
        Thread keyListener = new Thread(new Main());

        window.drawInputSection();
        window.draw();

        keyListener.start();


    }

    public void run() {
    int height = Math.round(re.getBoundY()/2);

    
    Pattern pattern = Pattern.compile(".+");
    Scanner scanner = new Scanner(System.in);
    while (true) {
        
        String input = scanner.findWithinHorizon(pattern,1);

        if (!input.equals(null)) {
            if (input.equals("\u007F")) {
                window.delLastChar();
                window.drawInputSection();
                continue;
            }

            window.updateInput(input);
            window.drawInputSection();
        // Source - https://stackoverflow.com/a
        // Posted by Ervin Szilagyi
        // Retrieved 2026-01-13, License - CC BY-SA 4.0
            StringBuilder sb = new StringBuilder(window.getCurText());
            for (int i=0; i < sb.length(); i+=0) {
                if (Character.isWhitespace(sb.charAt(i))) {
                    sb.deleteCharAt(i);
                } else {
                    i++;
                }
            }
            window.updateScreenBuffer(re.calc(sb.toString()));
            window.draw();
            
            //scanner.close();
        }
        //[ qwertyuiopasdfghjklzxcvbnm1234567890!@#$%^&*()-_+=]||(\\n)|\\\\u0008
        
        

        //window.updateInput(input);
    }
}
}