package blindMan_Exam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] infoMatrix = scanner.nextLine().split("\\s+");
        int matRow = Integer.parseInt(infoMatrix[0]);
        int matCol = Integer.parseInt(infoMatrix[1]);
        char[][] matrix = new char[matRow][matCol];

        int playerRow = 0;
        int playerCol = 0;
        int steps = 0;
        int touches = 0;

        for (int row = 0; row < matRow; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matCol; col++) {
                if (col < input.length) {
                    matrix[row][col] = input[col].charAt(0);
                } else {
                    matrix[row][col] = '-';
                }
                if (matrix[row][col] == 'B') {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }

       String command = scanner.nextLine();

        while (!"Finish".equals(command) && touches < 3) {
            switch (command) {
                case "up":
                    if (playerRow - 1 >= 0 && matrix[playerRow - 1][playerCol] != 'O') {
                        playerRow--;
                        steps++;
                        touches = checkForPlayer(matrix, playerRow, playerCol, touches);
                    }
                    break;
                case "down":
                    if (playerRow + 1 < matRow && matrix[playerRow + 1][playerCol] != 'O') {
                        playerRow++;
                        steps++;
                        touches = checkForPlayer(matrix, playerRow, playerCol, touches);
                    }
                    break;
                case "left":
                    if (playerCol - 1 >= 0 && matrix[playerRow][playerCol - 1] != 'O') {
                        playerCol--;
                        steps++;
                        touches = checkForPlayer(matrix, playerRow, playerCol, touches);
                    }
                    break;
                case "right":
                    if (playerCol + 1 < matCol && matrix[playerRow][playerCol + 1] != 'O') {
                        playerCol++;
                        steps++;
                        touches = checkForPlayer(matrix, playerRow, playerCol, touches);
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        print(steps, touches);


    }

    private static void print(int steps, int touches) {
        System.out.println("Game over!");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Touched opponents: %d", touches)).append(" ")
                .append(String.format("Moves made: %d", steps));
        System.out.println(sb.toString());
    }

    private static int checkForPlayer(char[][] matrix, int playerRow, int playerCol, int touches) {
        if (matrix[playerRow][playerCol] == 'P'){
            touches++;
            matrix[playerRow][playerCol] = '-';
        }
        return touches;
    }
}
