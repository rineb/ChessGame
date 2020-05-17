import java.util.ArrayList;
import java.util.List;

/**
 *This class models the Knight piece
 */
public class Knight extends Piece {
    private final String longName = "Knight";

    @Override
    public String getLongName() {
        return longName;
    }

    @Override
    public boolean checkCanMoveTo(int rowInitial, int colInitial, int rowFinal, int colFinal, Player player,
                                  List<Board.PieceInformation> piecesInfo) {

        List<int[]> allAllowedPositions = new ArrayList<>();

        int row, col;
        row = rowInitial - 2;
        col = colInitial - 1;
        if (row >= 0 && col >= 0) {
            allAllowedPositions.add(new int[] {row, col});
        }

        row = rowInitial - 1;
        col = colInitial - 2;
        if (row >= 0 && col >= 0) {
            allAllowedPositions.add(new int[] {row, col});
        } //For the top left "square"

        row = rowInitial - 2;
        col = colInitial + 1;
        if (row >= 0 && col < 8) {
            allAllowedPositions.add(new int[] {row, col});
        }

        row = rowInitial - 1;
        col = colInitial + 2;
        if (row >= 0 && col < 8) {
            allAllowedPositions.add(new int[] {row, col});
        } //For the top right "square"

        row = rowInitial + 1;
        col = colInitial - 2;
        if (row < 8 && col >= 0) {
            allAllowedPositions.add(new int[] {row, col});
        }

        row = rowInitial + 2;
        col = colInitial - 1;
        if (row < 8 && col >= 0) {
            allAllowedPositions.add(new int[] {row, col});
        } //For the bottom left "square"

        row = rowInitial + 2;
        col = colInitial + 1;
        if (row < 8 && col < 8) {
            allAllowedPositions.add(new int[] {row, col});
        }

        row = rowInitial + 1;
        col = colInitial + 2;
        if (row < 8 && col < 8) {
            allAllowedPositions.add(new int[] {row, col});
        } //For the bottom right "square"

        List<int[]> freeAllowedPositions = new ArrayList<>();
        for (int[] allowedPosition : allAllowedPositions) {
            if (!positionIsOurs(allowedPosition[0], allowedPosition[1], player, piecesInfo)) {
                //If there is no piece in a location where the move is allowed, the move gets added
                // to the free allowed moves list
                freeAllowedPositions.add(allowedPosition);
            }//Finds all free allowed positions
        }

        boolean canMove = positionInList(rowFinal, colFinal, freeAllowedPositions);

        return canMove;
    }

}
