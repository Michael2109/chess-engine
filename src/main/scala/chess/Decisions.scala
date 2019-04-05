package chess

class Decisions {

  def getBestMove(Chessboard: Chessboard): Move = {
    StandardMove(Position(1,1), Position(2,2))
  }

  def checkmate(chessboard: Chessboard): Option[Colour] = {
    val whitePieces = ChessboardUtils.getPiecesOfColour(chessboard, White)
    val blackPieces = ChessboardUtils.getPiecesOfColour(chessboard, Black)

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