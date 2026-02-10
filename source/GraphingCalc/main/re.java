package GraphingCalc.main;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import GraphingCalc.net.objecthunter.exp4j.*;


public class re {
  static byte boundX = 72;
  static byte boundY = 72;
  static int x_offset = boundX/2;
  static int y_offset = boundY/2;
  static double zoom = 0.1;
  static boolean isLineThick = false;
  static double progress = 0;
  static boolean[][] pos = new boolean[boundY][boundX];
  static int detail = 2;
  
  public static byte getBoundX() {
    return boundX;
  };

  public static byte getBoundY() {
    return boundY;
  };


  public static boolean[][] calc(String funk) {
    for (int i=0; i<boundY;i++) {
      Arrays.fill(pos[i],false);
    }
    //int detail = 1000;

    //double x=0.0-boundX/2;
    //double y=0.0-boundY/2;
    try {
      for (double y=0-boundY/2; y<boundY/2; y+=0.1) {
        for (double x=0-boundX/2; x<boundX/2;x+=0.1) {
  //            String[] splitFunc=Main.getFunc().split("=");
              ExpressionBuilder funky = new ExpressionBuilder(funk.split("=")[0]);
              Expression result = funky.variable("x").variable("y").build().setVariable("x",x).setVariable("y",y);
              double ans1 = result.evaluate();
              ExpressionBuilder funkytwo = new ExpressionBuilder(funk.split("=")[1]);
              Expression resulttwo = funkytwo.variable("x").variable("y").build().setVariable("x",x).setVariable("y",y);
              double ans2 = resulttwo.evaluate();

              if (Math.round(ans1) == Math.round(ans2)) {
                if ((int)Math.round(y+boundY/2) < boundY && (int)Math.round(y+boundY/2) >= 0 && 
                (int)Math.round(x+boundX/2) < boundX && (int)Math.round(x+boundX/2) >= 0) {
                  pos[(int)Math.round(y+boundY/2)][(int)Math.round(x+boundX/2)]=true;
                }
              };
        }
      }
      return pos;
    } catch (Exception e) {
      System.err.println(e);
      return pos;
    }

    //double y=Math.round(f(0.0-boundY/2));
    


    /*
    if (y+boundY/2 >= 0 &&
        y+boundY/2 < boundY &&
        x+boundX/2 >= 0 &&
        x+boundX/2 < boundX) 
      {
        pos[(int)y+boundY/2][(int)Math.round(x+boundX/2)]=true;
      }
    */


      /*
    while (x<boundX/2) {
        progress=Math.abs((x+boundX/2)/boundX)*100;
        double slope = (f(x+1)-f(x));
        if (Math.round(f(x)) > y) {
            y++;
        } else if (Math.round(f(x)) < y) {
            y--;
        } else {
            if (isLineThick) {
                x+=(1.0/detail);
            } else {
                if (y+boundY/2 >= 0 &&
                    y+boundY/2 < boundY &&
                    x+boundX/2 >= 0 &&
                    x+boundX/2 < boundX) 
                {
                    pos[(int)y+boundY/2][(int)Math.round(x+boundX/2)]=true;
                }            
                x+=(1.0/detail);
                continue;
            }
        }
        if (y+boundY/2 >= 0 &&
            y+boundY/2 < boundY &&
            x+boundX/2 >= 0 &&
            x+boundX/2 < boundX) 
        {
            pos[(int)y+boundY/2][(int)Math.round(x+boundX/2)]=true;
        }
        
        
    }
    */
    //progress=0;
    //return pos;
  }
}





