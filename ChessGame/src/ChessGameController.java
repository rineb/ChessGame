/**
 * This class is the Controller class of this application
 */
public class ChessGameController {
    public static void main(String[] args) {
        BoardView boardView = new BoardView();

        String player1Name = boardView.readPlayerName("1 (White)");
        String player2Name = boardView.readPlayerName("2 (Black)");
        Player player1 = new Player(player1Name, 1);
        Player player2 = new Player(player2Name, -1);

        Board chessBoard = new Board(player1, player2);
        boardView.setBoard(chessBoard);
        boardView.repaint();

        while (true) {
            makeActualMove(boardView, player1, chessBoard, "");
            makeActualMove(boardView, player2, chessBoard, "");
        }
    }

    /**
     * This method represents an actual piece move for a player
     * @param boardView Instance of BoardView
     * @param player Player who is making the move
     * @param chessBoard Instance of ChessBoard
     * @param prevMoveMessage A message that is shown after a move has failed
     */
    private static void makeActualMove(BoardView boardView, Player player, Board chessBoard, String prevMoveMessage) {
        int[] move = boardView.readMove(player, prevMoveMessage);
        String moveResult = chessBoard.canMovePiece(player, move);
        if  (moveResult.equals("OK")) {
            chessBoard.movePiece(player, move);
        } else {
            //Repeat question (Recursion is used)
            makeActualMove(boardView, player, chessBoard, moveResult);
        }
    }
}