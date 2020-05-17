import java.util.ArrayList;
import java.util.List;

/**
 * This class models the Bishop piece
 */
public class Bishop extends Piece {
    private final String longName = "Bishop";

    @Override
    public String getLongName() {
        return longName;
    }

    @Override
    public boolean checkCanMoveTo(int rowInitial, int colInitial, int rowFinal, int colFinal, Player player,
                                  List<Board.PieceInformation> piecesInfo) {

        List<int[]> allAllowedPositions = new ArrayList<>();

        for (int row = rowInitial - 1, col = colInitial - 1; row >= 0 && col >= 0; row--, col--) {
            if (positionIsTaken(row, col, piecesInfo)) {
                if(positionIsOurs(row, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[] {row, col}); //For top left
        }

        for (int row = rowInitial - 1, col = colInitial + 1; row >= 0 && col < 8; row--, col++) {
            if (positionIsTaken(row, col, piecesInfo)) {
                if(positionIsOurs(row, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[] {row, col}); //For top right
        }

        for (int row = rowInitial + 1, col = colInitial - 1; row < 8 && col >= 0; row++, col--) {
            if (positionIsTaken(row, col, piecesInfo)) {
                if(positionIsOurs(row, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[] {row, col}); //For bottom left
        }

        for (int row = rowInitial + 1, col = colInitial + 1; row < 8 && col < 8; row++, col++) {
            if (positionIsTaken(row, col, piecesInfo)) {
                if(positionIsOurs(row, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[] {row, col}); //For bottom right
        }

        List<int[]> freeAllowedPositions = new ArrayList<>();
        for (int[] allowedPosition : allAllowedPositions) {
            if (!positionIsOurs(allowedPosition[0], allowedPosition[1], player, piecesInfo)) {
                freeAllowedPositions.add(allowedPosition);
            }
        }

        boolean canMove = positionInList(rowFinal, colFinal, freeAllowedPositions);

        return canMove;
    }


}
