package Chess;
//Classe que detem as regras do jogo

import BordGame.Board;
import BordGame.Piece;
import BordGame.Position;
import Chess.Pieces.Peao;
import Chess.Pieces.Rei;
import Chess.Pieces.Torre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;


    //Lista para peças do tabuleiro e peças capturadas
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    //Dimensão do tabuleiro e definições de inicialização
    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }


    public boolean getCheckMate(){
        return checkMate;
    }

    //Propriedada para ter acesso ao programa principal
    public boolean getCheck(){
        return check;
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
        //Valida se o jogador se colocou em check
        if(testCheck(currentPlayer)){
            undoMove(source,targer,capturedPiece);
            throw  new ChessException("### Voce se colocou em XEQUE, MOVIMENTO NAO PERMITIDO ! ###");
        }
        //Valida se o oponente se colocou em check
        check = (testCheck(opponent(currentPlayer))) ? true : false;

        //verifique se a jogada deixou em xeque mata
        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }else {
            nextTurn();
        }
        return  (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Não existe peca na poosicao de origem!");
        }
        //Validando se a peça é do jogador atual ou do adiversario
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A peça escolhida não é sua!");
        }
        if(!board.piece(position).isTherAnyPossibleMove()){
            throw new ChessException("Nao existe movimentos possivel para esta peca!");
        }
    }

    private void validateTargetPosition(Position source,Position targer){
        if(!board.piece(source).possibleMove(targer)){
            throw  new ChessException("A peca escolhida nao pode realizar esta movimento !");
        }

    }
    //Remove pela da origem e da de destino
    private Piece makeMove(Position source, Position target){
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturePiece = board.removePiece(target);
        //Colocando posição da origem para o destino
        board.placePiece(p, target);

        if(capturePiece != null){
            piecesOnTheBoard.remove(capturePiece);
            capturedPieces.add(capturePiece);
        }

        return capturePiece;
    }

    //Desfaz o movimento do Rei caso ele se coloque em cheque
    private void undoMove(Position source,Position target, Piece capturedPiece){
       ChessPiece p = (ChessPiece)board.removePiece(target);
       p.decreaseMoveCount();
        board.placePiece(p, source);

        //Caso tenha capturado volta a peça para posição destino
        if(capturedPiece != null ){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void nextTurn(){
        turn++;
        //Troca de jogador
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    //Metodo devoldo o oponte
    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    //Localiza o Rei da cor em jogo
    private ChessPiece king(Color color){
        //Filtrando a lista com expressao lambda.
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof Rei){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("Nao foi encontrado " + color + " um Rei no tabuleiro !");
    }
    //Metodo verifica se o Rei esta em CHECK por alguma peça do tabuleiro
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        //Metodo que filtra as peças do tabuleiro com a cor do oponente do Rei
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        //Verifica se tem para cada peça do opnente leva a posição do Rei
        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return  true;
            }
        }
        return false;
    }
    //Logica para o Xeque Mate
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
           boolean[][] mat = p.possibleMoves();
           for(int i=0; i<board.getRows(); i++){
               for(int j=0; j<board.getRows(); j++){
                   if(mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source, target);
                        //Teste se o Rei da minha cor esta em Xeque.
                        boolean testCheck = testCheck(color);
                        undoMove(source, target,capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                   }
               }
           }
        }
        return true;


    }

    //Metodo recebe as coordenadas do xadez, passo a posição nas coordenadas do jogo
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }


    private void initialSetup(){
        placeNewPiece('a', 1, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Rei(board, Color.WHITE));
        placeNewPiece('h', 1, new Torre(board, Color.WHITE));
        placeNewPiece('a', 2, new Peao(board, Color.WHITE));
        placeNewPiece('b', 2, new Peao(board, Color.WHITE));
        placeNewPiece('c', 2, new Peao(board, Color.WHITE));
        placeNewPiece('d', 2, new Peao(board, Color.WHITE));
        placeNewPiece('e', 2, new Peao(board, Color.WHITE));
        placeNewPiece('f', 2, new Peao(board, Color.WHITE));
        placeNewPiece('g', 2, new Peao(board, Color.WHITE));
        placeNewPiece('h', 2, new Peao(board, Color.WHITE));

        placeNewPiece('a', 8, new Torre(board, Color.BLACK));
        placeNewPiece('e', 8, new Rei(board, Color.BLACK));
        placeNewPiece('h', 8, new Torre(board, Color.BLACK));
        placeNewPiece('a', 7, new Peao(board, Color.BLACK));
        placeNewPiece('b', 7, new Peao(board, Color.BLACK));
        placeNewPiece('c', 7, new Peao(board, Color.BLACK));
        placeNewPiece('d', 7, new Peao(board, Color.BLACK));
        placeNewPiece('e', 7, new Peao(board, Color.BLACK));
        placeNewPiece('f', 7, new Peao(board, Color.BLACK));
        placeNewPiece('g', 7, new Peao(board, Color.BLACK));
        placeNewPiece('h', 7, new Peao(board, Color.BLACK));

    }


}
