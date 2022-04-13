package Chess;
//Classe que detem as regras do jogo

import BordGame.Board;
import BordGame.Piece;
import BordGame.Position;
import Chess.Pieces.King;
import Chess.Pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private Board board;

    //Dimensão do tabuleiro e definições de inicialização
    public ChessMatch(){
        turn = 1;
        currentPlayer = Color.WHITE;
        board = new Board(8,8);
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position targer = targetPosition.toPosition();
        //Validando se na posição de origem ha uma peça
        validateSourcePosition(source);
        //Validando posição destino
        validateTargetPosition(source,targer);
        //Operação responsavel pela captura da peça
        Piece capturedPiece = makeMove(source, targer);
        nextTurn();
        return  (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Não existe peça na poosição de origem!");
        }
        //Validando se a peça é do jogador atual ou do adiversario
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A peça escolhida não é sua!");
        }
        if(!board.piece(position).isTherAnyPossibleMove()){
            throw new ChessException("Não existe movimentos possivel para esta peça!");
        }
    }

    private void validateTargetPosition(Position source,Position targer){
        if(!board.piece(source).possibleMove(targer)){
            throw  new ChessException("A peça escolhida não pode realizar esta movimento !");
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

    private void nextTurn(){
        turn++;
        //Troca de jogador
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
