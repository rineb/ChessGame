import java.util.ArrayList;
import java.util.List;

/**
 * Class Board is a "Mini-Controller" for the board tasks
 */
public class Board {
    private Player player1;
    private Player player2;
    private List<PieceInformation> piecesInfo;

    /**
     * The constructor for class Board
     * @param player1 Instance of Player
     * @param player2 Instance of Player
     */
    public Board(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;
        this.piecesInfo = new ArrayList<>(32);

        this.piecesInfo.addAll(createPlayerPieces(1, player1));
        this.piecesInfo.addAll(createPlayerPieces(2, player2));
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<PieceInformation> getPiecesInfo() {
        return piecesInfo;
    }

    /**
     * This method checks to see if a player can move a piece from and to a certain spot
     * @param player The player that has the piece
     * @param move An array that contains 4 elements, the initial and final row and column that
     *             the player has given as input
     * @return "OK" if move is allowed, otherwise an error message is returned
     */

    public String canMovePiece(Player player, int[] move) {
        int rowInitial = move[0];
        int colInitial = move[1];
        int rowFinal = move[2];
        int colFinal = move[3];

        //When we want to move a piece, if there's no piece to move or the piece doesn't belong to us,
        //an error message is returned
        PieceInformation chosenPieceInfo = getPlayerPieceInfo(player, rowInitial, colInitial);

        if  (chosenPieceInfo == null) {
            return "Error: Piece doesn't belong to you or spot is empty";
        }

        //checkCanMoveTo uses the concept of Polymorphism
        boolean canMove = chosenPieceInfo.piece.checkCanMoveTo(rowInitial, colInitial, rowFinal,
                colFinal, player, piecesInfo);

        if (canMove) {
            return "OK";
        } else {
            return "Error: Move not allowed";
        }
    }

    /**
     * This method is used to check whether a piece in a specific position belongs to a player
     * @param player The player which we are interested in
     * @param row The desired row
     * @param col The desired column
     * @return The piece with the given coordinates if the piece belongs to the player specified, else returns null
     */
    private PieceInformation getPlayerPieceInfo(Player player, int row, int col) {
        for (PieceInformation p : piecesInfo) {
            if  (p.row == row && p.column == col && p.player == player) {
                return p; //p is an instance of PieceInformation
            }
        }
        return null;
    }

    /**
     * This method creates the playing pieces for the player specified
     * @param playerIndex A number that identifies the player
     * @param player The player whose pieces we are creating
     * @return A list that contains 16 playing pieces for a player
     */
    private List<PieceInformation> createPlayerPieces(int playerIndex, Player player) {
        int firstRow;
        int secondRow;
        if (playerIndex == 1) {
            firstRow = 6;
            secondRow = 7;
        } else {
            firstRow = 1;
            secondRow = 0;
        }

        List<PieceInformation> pieces = new ArrayList<>(16);
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 0));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 1));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 2));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 3));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 4));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 5));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 6));
        pieces.add(new PieceInformation(player, new Pawn(), firstRow, 7));
        pieces.add(new PieceInformation(player, new King(), secondRow, 4));
        pieces.add(new PieceInformation(player, new Queen(), secondRow, 3));
        pieces.add(new PieceInformation(player, new Bishop(), secondRow, 2));
        pieces.add(new PieceInformation(player, new Bishop(), secondRow, 5));
        pieces.add(new PieceInformation(player, new Rook(), secondRow, 0));
        pieces.add(new PieceInformation(player, new Rook(), secondRow, 7));
        pieces.add(new PieceInformation(player, new Knight(), secondRow, 1));
        pieces.add(new PieceInformation(player, new Knight(), secondRow, 6));

        return pieces;
    }

    /**
     *Move the piece if possible and if necessary capture the opponent's piece
     * @param player The player who is making the move
     * @param move An array that contains 4 elements. Each element being initial row, initial column,
     *            final row, final column of the piece
     * @return Whether the move was successful
     */
    public boolean movePiece(Player player, int[] move) {
        PieceInformation pieceInfo = getPlayerPieceInfo(player, move[0], move[1]);
        if (pieceInfo == null) { //If player can't grab the piece
            return false;
        }

        PieceInformation pieceAtFinalPosition = pieceInfoAtPosition(move[2], move[3], piecesInfo);
        if (pieceAtFinalPosition != null) {
            piecesInfo.remove(pieceAtFinalPosition);
        }

        pieceInfo.row = move[2];
        pieceInfo.column = move[3];
        return true;
    }

    private PieceInformation pieceInfoAtPosition(int row, int col, List<PieceInformation> piecesInfo) {
        for (PieceInformation pieceInfo : piecesInfo) {
            if (pieceInfo.row == row && pieceInfo.column == col) {
                return pieceInfo;
            }
        }
        return null;
    }

    /**
     * A class that models information about the pieces, their player and location
     */
    class PieceInformation {
        Piece piece;
        int row;
        int column;
        Player player;

        /**
         * The constructor for class PieceInformation
         * @param player The player who has the piece
         * @param piece The piece we're interested in
         * @param row The row in which the piece is located
         * @param column The column in which the piece is located
         */
        public PieceInformation(Player player, Piece piece, int row, int column) {
            this.player = player;
            this.piece = piece;
            this.row = row;
            this.column = column;
        }
    }
}

