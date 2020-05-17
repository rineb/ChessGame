import java.util.ArrayList;
import java.util.List;

/**
 * This class models the Queen piece
 */
public class Queen extends Piece {
    private final String longName = "Queen";

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
        } //For te Bishop

        for (int row = rowInitial - 1; row >= 0; row--) {
            if (positionIsTaken(row, colInitial, piecesInfo)) {
                if(positionIsOurs(row, colInitial, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, colInitial});
                    break;
                }
            }
            allAllowedPositions.add(new int[]{row, colInitial});
        }

        for (int row = rowInitial + 1; row < 8; row++) {
            if (positionIsTaken(row, colInitial, piecesInfo)) {
                if(positionIsOurs(row, colInitial, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{row, colInitial});
                    break;
                }
            }
            allAllowedPositions.add(new int[]{row, colInitial});
        }

        for (int col = colInitial - 1; col >= 0; col--) {
            if (positionIsTaken(rowInitial, col, piecesInfo)) {
                if(positionIsOurs(rowInitial, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{rowInitial, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[]{rowInitial, col});
        }

        for (int col = colInitial + 1; col < 8; col++) {
            if (positionIsTaken(rowInitial, col, piecesInfo)) {
                if(positionIsOurs(rowInitial, col, player, piecesInfo)) {
                    break;
                } else {
                    allAllowedPositions.add(new int[]{rowInitial, col});
                    break;
                }
            }
            allAllowedPositions.add(new int[]{rowInitial, col});
        } //For te Rook

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
