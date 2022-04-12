package Chess;
//Classe que detem as regras do jogo

import BordGame.Board;
import BordGame.Position;
import Chess.Pieces.King;
import Chess.Pieces.Rook;

public class ChessMatch {
    private Board board;

    //Dimensão do tabuleiro
    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
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

    //Metodo responsavel por inicar a partida de Xadez colocando as peças no tabuleiro
    private void initialSetup(){
        board.placePiece(new Rook(board,Color.WHITE), new Position(2,1));
        board.placePiece(new King(board,Color.BLACK), new Position(0,4));
        board.placePiece(new King(board,Color.WHITE), new Position(7,4));


    }
}
