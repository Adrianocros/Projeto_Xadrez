package Chess.Pieces;

import BordGame.Board;
import BordGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Peao extends ChessPiece {


    public Peao(Board board, Color color) {
        super(board, color);
    }

    //Movimentos do Peao
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

        if(getColor() == Color.WHITE){
            p.setValues(position.getRow()- 1,position.getColumn());
            if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()- 2,position.getColumn());
            Position p2 = new Position(position.getRow()- 1,position.getColumn());
            if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExistis(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()- 1,position.getColumn() -1);
            if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()- 1,position.getColumn() +1);
            if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
        }

        }else{
                p.setValues(position.getRow()+ 1,position.getColumn());
                if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)){
                    mat[p.getRow()][p.getColumn()] = true;
                }
                p.setValues(position.getRow()+ 2,position.getColumn());
                Position p2 = new Position(position.getRow()- 1,position.getColumn());
                if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExistis(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                    mat[p.getRow()][p.getColumn()] = true;
                }
                p.setValues(position.getRow()+ 1,position.getColumn() -1);
                if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
                    mat[p.getRow()][p.getColumn()] = true;
                }
                p.setValues(position.getRow()+ 1,position.getColumn() +1);
                if(getBoard().positionExistis(p) && isThereOpponentPiece(p)){
                    mat[p.getRow()][p.getColumn()] = true;
        }

            }

        return mat;
    }
    @Override
    public String toString(){
        return "P";
    }
}
