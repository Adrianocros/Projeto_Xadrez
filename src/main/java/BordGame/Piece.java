package BordGame;

public abstract class Piece {
    protected Position position;
    private Board board;



    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    //Somente classes do mesmo pacote e sublcasses porem acessar o tabuleiro de uma peça
    //Não esta acessivel a camada de tabuleiro.
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    //Definindo possivel movimentos da peça
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    //Verificando se ha algum movimento possivel para peça
    public boolean isTherAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int i=0;i<mat.length;i++){
            for (int j=0;j<mat.length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

}
