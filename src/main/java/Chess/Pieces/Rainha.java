package Chess.Pieces;

import BordGame.Board;
import BordGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Rainha extends ChessPiece {

    public Rainha(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "Q";
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
        //Verifica peça a NW - DIAGONA
        p.setValues(position.getRow() - 1, position.getColumn() -1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()-1, p.getColumn() -1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //Verifica peça a NE
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()-1,p.getColumn() + 1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Verifica peça a SE
        p.setValues(position.getRow()+ 1, position.getColumn() + 1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn()+ 1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Verifica peça a SW
        p.setValues(position.getRow() + 1, position.getColumn() -1);
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() -1);
        }
        if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;
    }
}
