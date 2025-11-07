import java.io.IOException;
import java.util.Scanner;
import net.objecthunter.exp4j.*;


public class Main {
 static Thread input_listener = new Thread() {
    public void run() {
      Scanner scanner = new Scanner(System.in);
      while (true) {
        String input = scanner.findWithinHorizon("w",1);
        if (input != null) {
          if (input.equals("w")) {
            re.myo(1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          };
        } else {
          System.out.print("\33[2k");
        };
          
        input = scanner.findWithinHorizon("s",1);
        if (input != null) {
          if (input.equals("s")) {
            re.myo(-1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          }
        } else {
          System.out.print("\33[2k");
        };
        
        input = scanner.findWithinHorizon("a",1);
        if (input != null) {
          if (input.equals("a")) {
            re.mxo(-1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          };
        } else {
          System.out.print("\33[2k");
        };
        
        input = scanner.findWithinHorizon("d",1);
        if (input != null) {
          if (input.equals("d")) {
            re.mxo(1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          };
        } else {
          System.out.print("\33[2k");
        };

        input = scanner.findWithinHorizon("f",1);
        if (input != null) {
          if (input.equals("f")) {
            re.mzo(-1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          };
        };

        input = scanner.findWithinHorizon("g",1);
        if (input != null) {
          if (input.equals("g")) {
            re.mzo(1);
            System.out.print("\33[2k");
            re.calc();
            //scanner.close();
            continue;
          };
        };
      }
    };
  };

  
  static String argument = "";

  public static void main(String[] args) {
    argument = args[0];
    try {
      // only works for linux
      // todo: add compat for windows
      ProcessBuilder pb = new ProcessBuilder("stty","-icanon","-iuclc");
      pb.inheritIO();
      pb.redirectErrorStream(true);
      Process process = pb.start();
      process.waitFor();

    } catch (IOException e) {
      // Handle exceptions, e.g. print an error message
      e.printStackTrace();
    }  catch (InterruptedException e) {
    e.printStackTrace();
    }
    input_listener.start();
    re.calc();
    

    
    

    
    

    

    

    


    

  };

  public static String getFunc() {
    return argument;
  }
};


//ignbrk brkint ignpar parmrk inpck istrip inlcr igncr
//icrnl ixon ixoff icanon opost isig iuclc ixany imaxbel xcase min 1 time 0