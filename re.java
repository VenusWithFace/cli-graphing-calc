import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import net.objecthunter.exp4j.*;


public class re {
  static byte boundX = 72;
  static byte boundY = 72;
  static int x_offset = boundX/2;
  static int y_offset = boundY/2;
  static double zoom = 0.1;
  static boolean isLineThick = true;

  static boolean[][] pos = new boolean[boundY+1][boundX+1];

  public synchronized static void mxo(byte num) {
    x_offset+=num;
  };

  public synchronized static void myo(byte num) {
    y_offset+=num;
  };

  public synchronized static void mzo(byte num) {
    zoom+=num;
  };
  
  public static byte getBoundX() {
    return boundX;
  };

  public static byte getBoundY() {
    return boundY;
  };

  public static int getOffsetX() {
    return y_offset;
  };

  public static int getOffsetY() {
    return x_offset;
  };

  public static double f(double x) {
    //exp4j library thing to parse the function cause im not tryna write a parser

    //main.getfunc js gets the first argument from main
    //like if you put: java Main "sin(x)" it reads the sin(x) part
    ExpressionBuilder funky = new ExpressionBuilder(Main.getFunc());
    Expression result = funky.variable("x").build();
    result.setVariable("x",x);
    return result.evaluate();
    //im the goat at variable names
    };


  public static void calc() {
    double x=0.0-boundX/2;
    double y=Math.round(f(0.0-boundY/2));
    pos[(int)y+boundY/2][(int)x+boundX/2]=true;
    while (x<boundX/2) {
        double slope = (f(x+1)-f(x));
        if (Math.round(f(x)) > y) {
            y++;
        } else if (Math.round(f(x)) < y) {
            y--;
        } else {
            if (isLineThick) {
                x++;
            } else {
                pos[(int)y+boundY/2][(int)x+boundX/2]=true;
                x++;
                continue;
            }
        }
        if (y+boundY/2 >= 0 &&
            y+boundY/2 < boundY &&
            x+boundX/2 >= 0 &&
            x+boundX/2 < boundX) 
        {
            pos[(int)y+boundY/2][(int)x+boundX/2]=true;
        }
        
        
    }
    disp.disp(pos, x_offset, y_offset);
  }
}





