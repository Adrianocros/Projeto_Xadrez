package Chess;

import BordGame.Board;
import BordGame.Piece;
import BordGame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount(){
        return moveCount;
    }

    //Metodo para incrementar e decrementar o valor de movimento
    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    //Obtem a posição da peça de xadrez
    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    //Verifica se existe peça adversario na casa escolhida.
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        //verifica se a peça é adversaria
        return p != null && p.getColor() != color;
    }

}
