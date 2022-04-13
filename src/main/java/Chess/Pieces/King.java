package Chess.Pieces;

import BordGame.Board;
import BordGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {


    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }


    //Esse metodo informa se o Rei pode mover para uma determinada posição
    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();

    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //Testando cada uma das oito posições possiveis para o rei se mover
        //Acima
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Abaixo
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Esquerda
        p.setValues(position.getRow(), position.getColumn() -1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Direita
        p.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //
        //NO
        p.setValues(position.getRow() -1 , position.getColumn() - 1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //NE
        p.setValues(position.getRow() - 1 , position.getColumn() + 1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SW
        p.setValues(position.getRow() + 1 , position.getColumn() - 1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SE
        p.setValues(position.getRow() + 1 , position.getColumn() + 1);
        if(getBoard().positionExistis(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
