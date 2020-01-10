package bingo;

import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Dea Gjini, Isabella Enriquez, Julia Liu
 */
public class Bingo {

    static InputStreamReader in = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(in);

    /**
     * Main calls the methods to start the bingo and asks the user if they would
     * like to play again
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int yes = 0;
        do {
            yes = 0;
            int[][] map = new int[5][5];
            GenerateCard(map);
            DrawNum(map);
            System.out.print("BINGO! Want to play again? Press 1 for yes and any other integer for no: ");
            yes = Integer.parseInt(input.readLine());
        } while (yes == 1);
    }

    /**
     * GenerateCard generates the card by using different ArrayLists for each
     * row
     * @param map
     */
    public static void GenerateCard(int[][] map) {
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int i = 1; i <= 15; i++) {
            B.add(i);
        }
        ArrayList<Integer> I = new ArrayList<Integer>();
        for (int i = 16; i <= 30; i++) {
            I.add(i);
        }
        ArrayList<Integer> N = new ArrayList<Integer>();
        for (int i = 31; i <= 45; i++) {
            N.add(i);
        }
        ArrayList<Integer> G = new ArrayList<Integer>();
        for (int i = 46; i <= 60; i++) {
            G.add(i);
        }
        ArrayList<Integer> O = new ArrayList<Integer>();
        for (int i = 61; i <= 75; i++) {
            O.add(i);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (j == 0) {
                    map[i][j] = B.get((int) (Math.random() * B.size()));
                    B.remove(B.indexOf(map[i][j]));
                } else if (j == 1) {
                    map[i][j] = I.get((int) (Math.random() * I.size()));
                    I.remove(I.indexOf(map[i][j]));
                } else if (j == 2) {
                    map[i][j] = N.get((int) (Math.random() * N.size()));
                    N.remove(N.indexOf(map[i][j]));
                } else if (j == 3) {
                    map[i][j] = G.get((int) (Math.random() * G.size()));
                    G.remove(G.indexOf(map[i][j]));
                } else if (j == 4) {
                    map[i][j] = O.get((int) (Math.random() * O.size()));
                    O.remove(O.indexOf(map[i][j]));
                }
            }
        }
        DisplayCard(map);
    }

    /**
     * DisplayCard displays the map every time a spot is filled
     * @param map
     */
    public static void DisplayCard(int[][] map) {
        System.out.println("B " + " I " + " N " + " G " + " O ");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] <= 9) {
                    System.out.print(map[i][j] + "  ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("---------------------------");
    }

    /**
     * DrawNum draws the numbers until there is a win
     * @param map
     */
    public static void DrawNum(int[][] map) {
        int num = 0, col = 0, row = 0;
        ArrayList<Integer> draw = new ArrayList<Integer>();
        ArrayList<Integer> drawn = new ArrayList<Integer>();
        for (int i = 1; i <= 75; i++) {
            draw.add(i);
        }
        do {
            num = draw.get((int) (Math.random() * draw.size()));
            draw.remove(draw.indexOf(num));
            drawn.add(num);

            if (num <= 15) {
                col = 0; 
            } else if (num >= 16 && num <= 30) {
                col = 1; 
            } else if (num >= 31 && num <= 45) {
                col = 2;
            } else if (num >= 46 && num <= 60) {
                col = 3;
            } else if (num >= 61 && num <= 75) {
                col = 4;
            }

            for (int i = 0; i < map.length; i++) {
                if (map[i][col] == num) {
                    map[i][col] = 0;
                    row = i;
                    DisplayCard(map); 
                    System.out.println(drawn);
                    System.out.println("---------------------------");
                }
            }
        } while (CheckWinHorizontal(map, col, row) && CWVertical(map, col) && CWDiagonal(map) && CWDiagonalBR(map));
    }

    /**
     * CheckWinHorisontal checks a horizontal win (down a row)
     * @param map
     * @param col
     * @param row
     * @return
     */
    public static Boolean CheckWinHorizontal(int[][] map, int col, int row) {
        for (int i = 0; i < map[row].length; i++) {
            if (map[row][i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * CWVertical checks a vertical win
     * @param map
     * @param col
     * @return Boolean
     */
    public static Boolean CWVertical(int[][] map, int col) {
        for (int i = 0; i < map.length; i++) {
            if (map[i][col] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method: CWDiagonal checks for a win along the diagonal from the top left
     * to the bottom right
     * @param map
     * @return Boolean
     */
    public static Boolean CWDiagonal(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            if (map[i][i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * CWDiagonalBR checks a win along the diagonal from the bottom left to the
     * top right
     * @param map
     * @return Boolean
     */
    public static Boolean CWDiagonalBR(int[][] map) {
        int counter = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            if (map[i][counter] != 0) {
                return true;
            }
            counter++;
        }
        return false;
    }
}
