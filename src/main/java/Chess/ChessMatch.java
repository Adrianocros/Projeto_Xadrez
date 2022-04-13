package Chess;
//Classe que detem as regras do jogo

import BordGame.Board;
import BordGame.Piece;
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position targer = targetPosition.toPosition();
        //Validando se na posição de origem ha uma peça
        validateSourcePosition(source);
        //Operação responsavel pela captura da peça
        Piece capturedPiece = makeMove(source, targer);
        return  (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Não existe peça na poosição de origem!");
        }
    }

    //Remove pela da origem e da de destino
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturePiece = board.removePiece(target);
        //Colocando posição da origem na de destino
        board.placePiece(p, target);
        return capturePiece;
    }

    //Metodo recebe as coordenadas do xadez, passo a posição nas coordenadas do jogo
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(column, row).toPosition());
    }

    //Metodo responsavel por inicar a partida de Xadez colocando as peças no tabuleiro
    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));


    }
}
