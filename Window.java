
//making a window object thing so i dont have as many statics
public class Window {
    private boolean[][] screenBuffer;
    private String formattedScreenBuffer;
    private boolean[][] currentScreen;
    private String formattedCurrentScreen;

    //screen width and height
    private int boundX;
    private int boundY;

    private int screenX;
    private int screenY;




    private String formatScreen(boolean[][] originalArray) {
        String formattedArray = "";

        for (int y = boundY;y>=0;y-=2) {
            System.out.printf("\033[%s;H", screenX);
            for (int x = boundX;x>=0;x--) {
                if (originalArray[y][x]) {
                    if (originalArray[y-1][x]) {
                        formattedArray+="█";
                    } else {
                        formattedArray+="▀";
                    }
                } else {
                    if (originalArray[y-1][x]) {
                        formattedArray+="▄";
                    }
                }
            }
            formattedArray+="\n";
        }

        return formattedArray;
    }






    //init should only run once. it just sets the vars and stuff
    public void init (int boundX, int boundY) {
        if (screenBuffer == null || currentScreen == null) {
            throw new RuntimeException("Can't initialize Window object twice");
        }
        screenBuffer = new boolean[boundY + 1][boundX + 1];
        currentScreen = new boolean[boundY + 1][boundX + 1];
        this.boundX=boundX;
        this.boundY=boundY;
    }

    //move the window around the terminal. only updates on draw
    public void moveScreen (int x, int y) {
        screenX=x;
        screenY=y;
    } 


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
        formattedScreenBuffer
    }



    public void draw() {
        currentScreen=screenBuffer;
        screenBuffer = new boolean[boundY][boundX];
        formattedCurrentScreen=formattedScreenBuffer;
        formattedScreenBuffer = "";

        //move to screen location
        System.out.printf("\033[;%sH",screenY);



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