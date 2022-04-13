package Application;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            //Imprime tabuleiro na tela
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Digite a posição: ");
            ChessPosition source = UI.readChessPosition(scanner);

            System.out.println();
            System.out.print("Informe o destino: ");
            ChessPosition target = UI.readChessPosition(scanner);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

        }

    }
}
