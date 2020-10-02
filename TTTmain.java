import java.util.Arrays;
import java.util.Scanner;

public class TTTmain {
    static int[][] cross = new int[3][3];
    static Scanner input = new Scanner(System.in);
    static int[] firstStepsHum = new int[4];
    static int flag;
    static int[] sumLine = new int[8];

    public static void main(String[] args) {

        System.out.println("You play with crosses. The cross is indicated by the number 1. Your opponent is playing with zeroes. Zero is indicated by number 5. Empty fields are indicated by number 0");
        System.out.println("If you want to go first, press 1. If you want to go second, press 2.");
        int step = input.nextInt();
        System.out.println(step);
        switch (step) {
            case 1:
                inputHum();
                printCross();
                flag = 1;
                firstMove();
                printCross();
                break;
            case 2:
                firstMove();
                printCross();
                break;

        }
        while (true) {
            inputHum();
            flag = flag + 1;
            printCross();
            sumLine();
            if (winControl()) {
                System.out.println("WIN HUM");
                break;
            }

            nMove();
            printCross();
            sumLine();
            if (winControl()) {
                System.out.println("WIN BOT");
                break;
            }
        }
    }

    private static void nMove() {
        winStrateg();
        winControl();
        defStrateg();
    }

    private static void defStrateg() {
        antiBlits();
        secMove();
        defoultStep();


    }

    private static void defoultStep() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (cross[i][j] == 0) {
                    cross[i][j] = 5;
                    break;
                }
            }
        }
    }

    private static void secMove() {  // профилактика проигрыша после 2-го хода противника
        if (flag == 2) {
            for (int m = 0; m <= 5; m++) {
                if (sumLine[m] == 6) {
                    if (firstStepsHum[0] == 2 || firstStepsHum[2] == 2) {
                        int i = 2;
                        int j = 0;
                        cross[i][j] = 5;
                        break;
                    }
                }
            }

        }
    }

    private static void antiBlits() {   //профилактика выиграша, если на линии 2 икса
        for (int k = 0; k <= 7; k++) {
            if (sumLine[k] == 2) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (cross[i][j] == 0) {
                            cross[i][j] = 5;
                            sumLine();
                            for (int l = 0; l <= 7; l++) {
                                if (sumLine[l] == 7) {
                                    cross[i][j] = 5;
                                    break;
                                } else {
                                    cross[i][j] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private static void winStrateg() {    // проверка возможности закончить игру в 1 ход
        for (int k = 0; k <= 7; k++) {
            if (sumLine[k] == 10) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (cross[i][j] == 0) {
                            cross[i][j] = 5;
                               if (winControl()) {
                                break;
                            } else {
                                cross[i][j] = 0;
                            }

                        }
                    }
                }
            }
        }
    }

    private static boolean winControl() {  // проверка статуса выиграша
        sumLine();
        for (int k = 0; k < 8; k++) {
            if (sumLine[k] == 3 || sumLine[k] == 15)
                return true;
        }
        return false;
    }

    private static void sumLine() {    // суммирвание линий массива
        for (int k = 0; k <= 2; k++) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    sumLine[k] = sumLine[k] + cross[i][j]; // просчет вертикальных линий
                }
            }
        }
        for (int k = 3; k <= 5; k++) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    sumLine[k] = sumLine[k] + cross[i][j]; // просчет горизонтальных линий
                }
            }
        }
        sumLine[6] = cross[0][0] + cross[1][1] + cross[2][2]; // просчет дмагоналей
        sumLine[7] = cross[0][2] + cross[1][1] + cross[2][0];
    }


    private static void firstMove() {  // первый ход бота
        if (cross[1][1] == 0) {
            cross[1][1] = 5;
        } else {
            cross[0][0] = 5;
        }
    }

    private static void printCross() {   // печать массива
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(cross[i]));
        }
    }

    private static void inputHum() {           //ввод координат человеком
        System.out.println("Input X coordinate   ");
        int x = input.nextInt();
        System.out.println("Input Y coordinate   ");
        int y = input.nextInt();
        cross[x - 1][y - 1] = 1;
        if (flag == 1) {
            firstStepsHum[0] = x - 1;
            firstStepsHum[1] = y - 1;
        }
        if (flag == 2) {
            firstStepsHum[2] = x - 1;
            firstStepsHum[3] = y - 1;
        }
    }


}
