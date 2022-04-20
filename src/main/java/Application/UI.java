package Application;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    //Cores para imprimir no console, utilizar um console tipo Git
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }


    public static ChessPosition readChessPosition(Scanner scanner){
        try {
            String s = scanner.nextLine();
            char column = s.charAt(0);
            int  row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }catch (RuntimeException e){
            throw  new InputMismatchException("Erro na posicao do Xadrez : Valores validos sao de a1 a h8");
        }
    }
    //Imprimindo a partida
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turno: " + chessMatch.getTurn());
        System.out.println("Esperando jogador " +  chessMatch.getCurrentPlayer() + " ,realizar a jogada");
        if(chessMatch.getCheck()){
            System.out.println("#### XEQUE !!! ####");
        }
    }



    public static void printBoard(ChessPiece[][] pieces){
        for( int i=0; i<pieces.length;i++){
            System.out.print((8 - i) + " ");
            for (int j=0;j<pieces.length;j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    //Colorinado a tabuleiro com movimentos possiveis e marcados
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        for( int i=0; i<pieces.length;i++){
            System.out.print((8 - i) + " ");
            for (int j=0;j<pieces.length;j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }


    //Impressao de uma peça no console COLORIDO
    private static void printPiece(ChessPiece piece, boolean background){
        //Valida para mudar o fundo da tela
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    //Metodo que informa a caputra de peças
    private static void printCapturedPieces(List<ChessPiece> captured){
        //Filtrando a lista  e todos que são da cor branca e pretas.
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Pecas capturadas: ");

        System.out.print("Brancas: ");
        //Imprimindo Array de valores no Java
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Pretas : ");
        //Imprimindo Array de valores no Java
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

}
