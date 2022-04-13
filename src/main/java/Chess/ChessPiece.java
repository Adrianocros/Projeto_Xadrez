package Chess;

import BordGame.Board;
import BordGame.Piece;
import BordGame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    //Verifica se existe peça adversario na casa escolhida.
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        //verifica se a peça é adversaria
        return p != null && p.getColor() != color;
    }

}
