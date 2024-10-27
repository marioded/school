package tech.zmario.school.exercise.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.exercise.Exercise;
import tech.zmario.school.api.util.SelectionUtils;

import java.util.Scanner;

/**
 * Exercise to play tris.
 *
 * <p>Some comments are in italian to make the exercise more understandable for the students.</p>
 */
public class TrisExercise extends Exercise {

    private static final Logger LOGGER = LogManager.getLogger(TrisExercise.class);

    // Constants for the exercise
    /**
     * The character to represent an empty cell on the board.
     */
    private static final char EMPTY = '-';
    /**
     * The character to represent the first player.
     */
    private static final char PLAYER_X = 'X';
    /**
     * The character to represent the second player.
     */
    private static final char PLAYER_O = 'O';

    // Messages for the exercise
    private static final String INSERT_PLAYER = "Insert the name of the {} player";
    private static final String TURN_SWITCH = "Player {} turn!";
    private static final String POSITION_TAKEN = "The position is already taken!";
    private static final String DRAW = "The game ended in a draw!";
    private static final String PLAYER_WON = "Player {} won!";

    /**
     * The description of the exercise.
     */
    private static final String DESCRIPTION_VALUE = """
            Play tris with two players. The first player will be represented by %s and the second player by %s.
            """.formatted(PLAYER_X, PLAYER_O);

    /**
     * Creates new instance of the exercise.
     */
    public TrisExercise() {
        super("tris", DESCRIPTION_VALUE);
    }

    @Override
    public void solve(Scanner scanner) {
        LOGGER.info(INSERT_PLAYER, "first");
        String firstPlayer = scanner.nextLine();

        LOGGER.info(INSERT_PLAYER, "second");
        String secondPlayer = scanner.nextLine();

        // Creiamo la matrice 3x3 per rappresentare il tabellone
        // Sarà inizializzata con celle vuote e avrà la seguente forma:
        // - - -
        // - - -
        // - - -
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            board[i] = new char[]{EMPTY, EMPTY, EMPTY};
        }

        // Inizializziamo la variabile per tenere traccia di chi è il turno del giocatore
        boolean firstPlayerTurn = true;

        // The game loop (not a while true loop because we love our teacher)
        while (!draw(board) && !won(board, PLAYER_X) && !won(board, PLAYER_O)) {
            LOGGER.info(TURN_SWITCH, firstPlayerTurn ? firstPlayer : secondPlayer);
            printBoard(board); // Print the board to the console

            // Chiede all'utente di inserire una posizione da 1 a 9
            int position = SelectionUtils.validateSelection(LOGGER, scanner, 9);

            // Converte la posizione da 1 a 9 in una posizione valida per la matrice
            // C'è un x - 1 perché l'utente inserisce da 1 a 9, ma noi vogliamo da 0 a 8 poiché le matrici partono da 0
            // Il numero di righe è 3, quindi sarà il divisore
            int row = (position - 1) / 3; // Esempio: 5 -> 5 - 1 / 3 = 1
            int column = (position - 1) % 3; // Resto della divisione, 5 -> 5 - 1 / 3 avrà resto 2

            if (board[row][column] != EMPTY) { // Controlla se la posizione è già stata presa
                LOGGER.error(POSITION_TAKEN);
                continue;
            }

            // Inserisce il simbolo del giocatore nella posizione selezionata
            // Se è il turno del primo giocatore, inserirà X, altrimenti O
            if (firstPlayerTurn) {
                board[row][column] = PLAYER_X;
            } else {
                board[row][column] = PLAYER_O;
            }

            // Cambia il turno del giocatore usando la condizione logica NOT (quella di sistemi e reti insomma)
            firstPlayerTurn = !firstPlayerTurn;
        }

        printBoard(board);

        // Verifica se il giocatore 1 o il giocatore 2 hanno vinto, altrimenti sarà al 100% un pareggio
        if (won(board, PLAYER_X)) {
            LOGGER.info(PLAYER_WON, firstPlayer);
        } else if (won(board, PLAYER_O)) {
            LOGGER.info(PLAYER_WON, secondPlayer);
        } else {
            LOGGER.info(DRAW);
        }
    }

    /**
     * Check if the board is full. If the board is full, the game will end in a draw.
     *
     * @param board the board to check
     * @return <code>true</code> if the board is full, <code>false</code> otherwise
     */
    private boolean draw(char[][] board) {
        for (char[] chars : board) { // Visita ogni singola riga della matrice
            for (char aChar : chars) { // Visita ogni singola cella della riga
                if (aChar == EMPTY) return false; // Se c'è almeno una cella vuota, non è un pareggio
            }
        }

        // Se non c'è nessuna cella vuota, è un pareggio
        return true;
    }

    /**
     * Check if the player with the given character won the game.
     * A player wins if he has three characters in a row, column or diagonal of the board.
     *
     * @param board the board to check
     * @param winCharacter the character to check
     * @return <code>true</code> if the player won, <code>false</code> otherwise
     */
    private boolean won(char[][] board, char winCharacter) {
        for (int i = 0; i < 3; i++) { // Controlla le righe
            if (board[i][0] == winCharacter &&
                board[i][1] == winCharacter &&
                board[i][2] == winCharacter) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) { // Controlla le colonne
            if (board[0][i] == winCharacter &&
                board[1][i] == winCharacter &&
                board[2][i] == winCharacter) {
                return true;
            }
        }

        // Controllo diagonale n. 1
        if (board[0][0] == winCharacter &&
            board[1][1] == winCharacter &&
            board[2][2] == winCharacter) {
            return true;
        }

        // Controllo diagonale n. 2
        return board[0][2] == winCharacter &&
               board[1][1] == winCharacter &&
               board[2][0] == winCharacter;
    }

    /**
     * Print the board to the console.
     * Supports any size of the board.
     *
     * @param board the matrix to print
     */
    private void printBoard(char[][] board) {
        for (char[] chars : board) {
            StringBuilder row = new StringBuilder();

            for (char currentChar : chars) {
                row.append(currentChar).append(" ");
            }

            LOGGER.info(row);
        }
    }
}
