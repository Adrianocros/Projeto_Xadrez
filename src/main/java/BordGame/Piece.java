package BordGame;

public class Piece {
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

}
