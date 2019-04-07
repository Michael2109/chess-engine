package chess

case class Chessboard(pieces: List[List[Option[Piece]]], nextColour: Colour) {}

case object Chessboard {

  def pieceAtPosition(chessboard: Chessboard, position: Position): Option[Piece] = {
    chessboard.pieces(position.y)(position.x)
  }

  def makeMove(chessboard: Chessboard, move: Move): Chessboard = {
    move match {
      case standardMove: StandardMove =>
        val pieceToMove = pieceAtPosition(chessboard, standardMove.startPosition)
        val movedPiece = setPiece(chessboard, pieceToMove, standardMove.endPosition)
        val newChessboard = setPiece(movedPiece, None, standardMove.startPosition)
        newChessboard
    }
  }

  def setPiece(chessboard: Chessboard, maybePiece: Option[Piece], position: Position): Chessboard = {
    val newRow: List[Option[Piece]] = chessboard.pieces(position.y).updated(position.x, maybePiece)
    val newPieces: List[List[Option[Piece]]] = chessboard.pieces.updated(position.y, newRow)
    Chessboard(newPieces, chessboard.nextColour)
  }


  def getPieces(chessboard: Chessboard): List[Piece] = {
    chessboard.pieces.flatten.filter(_.isDefined).map(_.get)
  }

  def getPiecesOfColour(chessboard: Chessboard, colour: Colour): List[Piece] = {
    getPieces(chessboard).filter(_.colour.equals(colour))
  }

  def changeChessboardColour(chessboard: Chessboard): Chessboard = {
    Chessboard(chessboard.pieces, chessboard.nextColour)
  }

  def changeColour(colour: Colour): Colour = {
    colour match {
      case White => Black
      case Black => White
    }
  }
}
