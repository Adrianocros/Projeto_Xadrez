package Chess.Pieces;

import BordGame.Board;
import BordGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        //Verifica peça a acima
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() -1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //Verifica peça a esquerda
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Verifica peça a direita
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Verifica peça a baixo
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
