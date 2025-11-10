import java.io.IOException;
import java.util.Scanner;
import net.objecthunter.exp4j.*;


public class Main {
 static Thread input_listener = new Thread() {
    public void run() {

        //the key inputs. 
        //pretty much just reads the console and clears it when a key is pressed.
        //only works w/o hitting enter cause of the stty thing on line 49
        //not compatible with windows or some linux distros yet afaik

        Scanner scanner = new Scanner(System.in);
        while (true) {
        String input = scanner.findWithinHorizon("[wasd]",1);
        if (input != null) {
            if (input.equals("w")) {
                //have to cast to byte cause it defaults to int ig
                re.myo((byte)1);
            };
            if (input.equals("a")) {
                re.mxo((byte)-1);
            };
            if (input.equals("s")) {
                re.myo((byte)-1);
            };
            if (input.equals("d")) {
                re.mxo((byte)1);
            };
            System.out.print("\33[2k");
            //calling calculator function to recalculate stuff after moving camera
            //calc function calls draw function
            re.calc();
            continue;
        } else {
            //33[2k clears whole line
            System.out.print("\33[2k");
        };
      }
    };
  };

  
  static String argument = "";

  public static void main(String[] args) {
    Window window = new Window();
    



    


    argument = args[0];
    try {
        // -only works for linux
        // -todo: add compat for windows, mac, and prolly other linux distros

        //-stty is the command to change settings
        //-icanon turns off canonical terminal meaning
        // you dont gotta hit enter for it to read the console
        //-iuclc converts uppercase to lowercase
        //for more do stty --help
        //gracias stack overflow

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
        window.init(re.getBoundX(),re.getBoundY(),"Graphing Calculator V1.1");
        window.moveScreen(2,2);
        window.updateScreenBuffer(re.calc());
        window.draw();
  };

  public static String getFunc() {
    return argument;
  }
};