package Application;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                //Limpando tela
                UI.clearScreen();
                //Imprime tabuleiro na tela
                UI.printMatch(chessMatch);
                System.out.println();
                System.out.print("Digite a posição: ");
                ChessPosition source = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Informe o destino: ");
                ChessPosition target = UI.readChessPosition(scanner);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            }catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
           }
        }

    }
}
