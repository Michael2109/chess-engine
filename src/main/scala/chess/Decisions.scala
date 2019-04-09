package chess

object Decisions {

  def getBestMove(chessboard: Chessboard): Move = {
    chessboard.pieces.zipWithIndex.map {
      case(row, y) => row.zipWithIndex.map {
        case (piece, x) => Rules.possibleMoves(chessboard, Chessboard.pieceAtPosition(chessboard, Position(x, y))
      }
    }
    StandardMove(Position(1,1), Position(2,2))
  }

  def checkmate(chessboard: Chessboard): Option[Colour] = {
    val whitePieces = Chessboard.getPiecesOfColour(chessboard, White)
    val blackPieces = Chessboard.getPiecesOfColour(chessboard, Black)

    if (!whitePieces.exists(_.pieceType == King)) {
      Option.apply(Black)
    } else if (!blackPieces.exists(_.pieceType == King)) {
      Option.apply(White)
    } else {
      None
    }
  }

  def stalemate(chessboard: Chessboard): Boolean ={
    false
  }
}
