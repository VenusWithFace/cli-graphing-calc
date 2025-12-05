package GraphingCalc.main;

//making a window object thing so i dont have as many statics
public class Window {

    //move to x/y on screen
    private String moveX = "\033[%s;H";
    private String moveY = "\033[;%sH";
    private String move = "\033[%s;%oH";

    //clears
    private String CLEAR = "\033[2J";
    private String CLEARLINE = "\33[2k";

    //colors.. ANSI escape code wikipedia page for ref.
    private String BG = "\033[48:5:254m";
    private String FG = "\033[38:5:17m";
    private String TERMBG = "\033[48:5:17m";
    private String TEXTCOLOR = "\033[38:5:254m";
    private String RESET = "\033[m";


    private boolean[][] screenBuffer;
    private String formattedScreenBuffer;
    private boolean[][] currentScreen;
    private String formattedCurrentScreen;

    //screen width and height
    private int boundX;
    private int boundY;

    private int screenX;
    private int screenY;

    private String windowName;




    private String formatScreen(boolean[][] originalArray) {
        String formattedArray = BG+FG;

        for (int y = boundY;y>0;y-=2) {
            formattedArray+=String.format(move, ((boundY-y)/2+screenY)+1,screenX);
            for (int x = boundX;x>=0;x--) {


                if (originalArray[y][x]) {
                    if (originalArray[y-1][x]) {
                        formattedArray+="█";
                    } else {
                        formattedArray+="▀";
                    }
                } else if (originalArray[y-1][x]) {
                        formattedArray+="▄";
                } else {
                    if (y==boundY/2) {
                        if (x==boundX/2) {
                            formattedArray+="⡗";
                        } else {
                            formattedArray+="⠒";
                        }
                    } else if (x==boundX/2) {
                        formattedArray+="⡇";
                    } else {
                        formattedArray+=" ";
                    }
                }
            }
            formattedArray+="\n";
        }
        formattedArray+=TERMBG+TEXTCOLOR;
        return formattedArray;
    }






    //init should only run once. it just sets the vars and stuff
    public void init (int boundX, int boundY, String windowName) {
        if (screenBuffer != null || currentScreen != null) {
            throw new RuntimeException("Can't initialize Window object twice");
        }
        screenBuffer = new boolean[boundY + 1][boundX + 1];
        currentScreen = new boolean[boundY + 1][boundX + 1];
        this.boundX=boundX;
        this.boundY=boundY;
        this.windowName=windowName;

        //first thing makes background color and second thing clears screen to actually apply it
        System.out.print(TERMBG+TEXTCOLOR+CLEAR);
    }

    //move the window around the terminal. only updates on draw
    public void moveScreen (int x, int y) {
        screenX=2*x;
        screenY=y;
    };


    public boolean[][] getScreen() {
        return this.currentScreen;
    };

    /*
    *method overloading--if they put a whole matrix it 
    *replaces the whole screenBuffer but if they put
    *a value, x, and y it replaces a specific position
    */
    public void updateScreenBuffer(boolean[][] content) {
        formattedScreenBuffer=formatScreen(content);
        screenBuffer=content;
    }


    public void updateScreenBuffer(boolean content, byte x, byte y) {
        this.screenBuffer[y][x] = content;
        formattedScreenBuffer=formatScreen(screenBuffer);
    }



    public void draw() {
        currentScreen=screenBuffer;
        screenBuffer = new boolean[boundY][boundX];
        formattedCurrentScreen=formattedScreenBuffer;
        formattedScreenBuffer = "";


        //move to screen location
        System.out.printf(move,screenY,screenX);
        System.out.print(BG+FG+String.format(move,screenY,(boundX/3)));
        System.out.print(windowName);
        System.out.print(formattedCurrentScreen);


    }
}


/*
ansi code ref
-------------------
\033[A - move cursor up
\033[B - move cursor down 
\033[C - move cursor right 
\033[D - move cursor left
\033[H - move cursor to top left of screen
\033[x;yH - move cursor to x,y on screen;


*/