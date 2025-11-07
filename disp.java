import java.io.IOException;
public class disp {
  public static void disp(boolean[][] pos, int x_offset, int y_offset) {
    String str;
    int boundX = re.getBoundX();
    int boundY = re.getBoundY();
    int offset_x = re.getOffsetX();
    int offset_y = re.getOffsetY();
    String window_title="Graphing Calculator V1";

    str="\033[40;0;37;25m  ";
    System.out.print("\033[2;H");
    //System.out.print("\033[47;1;90m");
    //System.out.print("\033[38;5;213m");
    //System.out.print("\033[38;5;231;48;5;53m");
    //System.out.print("\033[10m");
    for (byte i=0;i<=boundX-window_title.length();i++) {
      if (i == boundX/2-window_title.length()/2) {
        str+="\033[38;5;18m"+window_title;
        continue;
      };
      str+="\033[38;5;18m\033[48;5;250m-";
    };
    str+="\n";

    for (int y=boundY-1;y>0;y-=2) {
      str+="\033[40;0;37;25m";
      str+="  ";
      str+="\033[48;5;17m";
      for (int x=0;x<boundX;x++) {
        if (pos[y+1][x] && pos[y][x]) {
          str+="\033[1m█";
        } else if (pos[y][x]) {
          str+="\033[1m▄";
        } else if (pos[y+1][x]) {
          str+="\033[1m▀";
        } else if (y==Math.round(boundY/2) || y+1==Math.round(boundY/2)) {
          if (x==Math.round(boundX/2)) {
            str+="\033[1m⡗";
          } else {
            str+="\033[1m⠒";
          }
        } else if (x==Math.round(boundX/2)) {
          str+="\033[1m⡇";
        } else {
          str+=" ";
        }
        //System.out.print(str);
      }
      //System.out.print(y_offset);
      if (y<boundY) {str+="\033[48;5;18m  ";};
      str+="\n";
      //+toSup(y+1)+toSub(y)+"\n"
    }
    str+="\033[40;0;37;25m    ";
    for (byte i = 0; i< boundX;i++) {
      str+="\033[48;5;18m ";
    };
    str+="\n";
    System.out.print("\033[48;5;147m");
    System.out.print(str);
    System.out.print("\033[40;0;37;25m");
  }
};