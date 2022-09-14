import java.util.Random;
import java.util.Scanner;

public class MineSweeper {



    void playGround(char[][] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" ");
        for (int i = 0; i <= 4 * (x.length) + 1; i++) {
            System.out.print(" ");
        }
        System.out.println(" ");
        for (int i = 0; i < x.length; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < x[0].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
    }

    void createField(boolean[][] x, char[][] fieldView) {
        Random rand = new Random();
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = false;
                fieldView[i][j] = 'x';
            }
        }
        int mineCounter=0;
        while (mineCounter<15) {

            int line = rand.nextInt(10);
            int column = rand.nextInt(10);
            x[line][column] = true;
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[0].length; j++) {
                    if (x[i][j]==true) {
                        mineCounter++;
                    }
                }
            }
        }
    }

    // Satır kontrolu
    int lineControl(int line, Scanner scan) {
        while ((line>=10)||(line < 0)) {
            System.out.println("Hatali satir girisi!!");
            System.out.println("Lutfen gecerli bir satir sayisi giriniz");
            line = scan.nextInt();
        }
        return line;
    }

    // Sutun kontrolu
    int columnControl(int column, Scanner scan) {
        while ((column>=10)||(column < 0)) {
            System.out.println("Hatali sutun girisi!!");
            System.out.println("Lutfen gecerli bir sutun sayisi giriniz");
            column = scan.nextInt();
        }
        return column;
    }


    boolean play(boolean[][] mineLocation, char[][] fieldView, Scanner scan) {
        playGround(fieldView);
        System.out.println();
        System.out.println("Satir sayisini giriniz:");
        int line = scan.nextInt();
        line = lineControl(line, scan);
        System.out.println("Sutun sayisini giriniz: ");
        int column = scan.nextInt();
        column = columnControl(column, scan);


        while (fieldView[line][column] == '0') {
            System.out.println();
            System.out.println("Bu konumu daha once girdiniz!!");
            System.out.println("Lutfen satir giriniz: ");
            line = scan.nextInt();
            line = lineControl(line, scan);

            System.out.println();
            System.out.println("Lutfen sutun giriniz: ");
            column = scan.nextInt();
            column = columnControl(column, scan);


        }
        if (mineLocation[line][column]) {
            for (int i = 0; i < fieldView.length; i++) {
                for (int j = 0; j < fieldView[0].length; j++) {
                    if (mineLocation[i][j]) {
                        fieldView[i][j] = '*';
                    } else {
                        fieldView[i][j] = 'x';
                    }
                }
            }
            System.out.println();
            playGround(fieldView);
            System.out.println();
            System.out.println("Mayina bastin oyunu kaybettiniz!!");
            return false;
        }else{
            fieldView[line][column]='0';
            return true;
        }

    }
    void run(){
        Scanner scan=new Scanner(System.in);
        boolean[][] mineLocation=new boolean[10][10];
        char[][] fieldView=new char[10][10];
        createField(mineLocation,fieldView);
        boolean control=true;
        int numberOfMoves=0;

        while (control){
            numberOfMoves++;
            control=play(mineLocation,fieldView,scan);
            if (numberOfMoves==10){
                System.out.println("Oyunu kazandiniz");
                control=false;
            }
        }
        System.out.println("Hamle sayisi: "+(numberOfMoves-1));
    }

}

