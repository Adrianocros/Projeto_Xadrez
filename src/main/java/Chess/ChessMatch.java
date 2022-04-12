package Chess;
//Classe que detem as regras do jogo

import BordGame.Board;

public class ChessMatch {
    private Board board;

    //Dimensão do tabuleiro
    public ChessMatch(){
        board = new Board(8,8);
    }

    //Retorna a matriz de peças de xadez correspondente a esse partida.
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i<board.getRows(); i++){
            for(int j=0; j<board.getColumns();j++){
                mat[i][j] =(ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
}
