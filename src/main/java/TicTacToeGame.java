import Modal.*;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class TicTacToeGame {

    private ArrayDeque<Player> deque;
    Board board;
    HashMap<Integer, Pair> boxNumber = new HashMap<>();

    public TicTacToeGame() {
        initialiseGame();
    }

    public void initialiseGame(){
        board= new Board(3);
        deque = new ArrayDeque<>();
        mapBoxtoPair();

        Player p1 = new Player("Player1",new PieceO());
        deque.addLast(p1);
        Player p2 = new Player("Player2",new PieceX());
        deque.addLast(p2);

    }


    public String startGame(){
        boolean noWinner = true;
        int countofPlayer1turn =0;
        int countofPlayer2turn =0;
        while (noWinner){

            Player playerTurn = deque.removeFirst();

            board.printBoard();

            int countOfFreeSpaces = board.countOfFreeSpaces();

            if(countOfFreeSpaces ==0){
                noWinner=false;
                continue;
            }

            System.out.print("Player:" + playerTurn.getName() + " Enter Box number: ");
            Scanner inputScanner = new Scanner(System.in);
            int i = inputScanner.nextInt();

            int inputRow = (int)boxNumber.get(i).getKey();
            int inputColumn = (int)boxNumber.get(i).getValue();

            boolean enteredSuccessfully = board.addPiece(inputRow, inputColumn, playerTurn.getPlayingPiece());

            if(!enteredSuccessfully){
                System.out.println("incorrect place please give correct place");
                deque.addFirst(playerTurn);
                continue;
            }
            deque.addLast(playerTurn);

            if(playerTurn.getName().equalsIgnoreCase("Player1")) {
                countofPlayer1turn++;
            }else {
                countofPlayer2turn++;
            }

            if(countofPlayer1turn>=3|| countofPlayer2turn>=3){

                boolean winner = isWinner( inputRow,inputColumn,playerTurn.getPlayingPiece().pieceType);
                if(winner){
                    return playerTurn.getName();

                }

            }



        }
        return "Tie";
    }

    public  boolean isWinner(int row, int coulmn, PieceType pieceType){


        boolean rowCheck = true;
        boolean coulmnCheck= true;
        boolean diagonalCheck= true;
        boolean antiDiagonalCheck= true;

        for (int i=0;i<board.getSize();i++){

            if( board.getBoard()[row][i]==null || board.getBoard()[row][i].pieceType!=pieceType ){
                rowCheck=  false;
            }
        }

        for (int i=0;i<board.getSize();i++){

            if( board.getBoard()[i][coulmn]==null || board.getBoard()[i][coulmn].pieceType!=pieceType ){
                coulmnCheck=  false;
            }
        }

        for (int i=0, j=0;i<board.getSize();i++,j++){

            if( board.getBoard()[i][j]==null || board.getBoard()[i][j].pieceType!=pieceType ){
                diagonalCheck=  false;
            }
        }

        for (int i=0, j=board.getSize()-1;i<board.getSize();i++,j--){

            if( board.getBoard()[i][j]==null || board.getBoard()[i][j].pieceType!=pieceType ){
                antiDiagonalCheck=  false;
            }
        }

        return  rowCheck|| coulmnCheck||diagonalCheck||antiDiagonalCheck;
    }


    public  void mapBoxtoPair(){
        boxNumber.put(1, new Pair(0,0));
        boxNumber.put(2, new Pair(0,1));
        boxNumber.put(3, new Pair(0,2));
        boxNumber.put(4, new Pair(1,0));
        boxNumber.put(5, new Pair(1,1));
        boxNumber.put(6, new Pair(1,2));
        boxNumber.put(7, new Pair(2,0));
        boxNumber.put(8, new Pair(2,1));
        boxNumber.put(9, new Pair(2,2));
    }
}
