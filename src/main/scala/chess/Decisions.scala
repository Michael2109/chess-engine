package chess

object Decisions {

  def getBestMove(Chessboard: Chessboard): Move = {
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
