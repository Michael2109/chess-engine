package chess

case class Chessboard(pieces: Array[Array[Option[Piece]]], nextColour: Colour) {}

case object Chessboard {

  def pieceAtPosition(chessboard: Chessboard, position: Position): Option[Piece] = {
    chessboard.pieces(position.y)(position.x)
  }

  def pieceColourAtPosition(chessboard: Chessboard, position: Position): Option[Colour] = {
    pieceAtPosition(chessboard, position) match {
      case Some(piece) => Some(piece.colour)
      case None => None
    }
  }

  def makeMove(chessboard: Chessboard, move: Move): Chessboard = {
    move match {
      case standardMove: StandardMove =>
        val pieceToMove = pieceAtPosition(chessboard, standardMove.startPosition)

        Chessboard(chessboard.pieces.zipWithIndex.map {
          case (row, y) => row.zipWithIndex.map {
            case (piece, x) =>
              if(x == standardMove.startPosition.x && y == standardMove.startPosition.y){
                None
              } else if(x == standardMove.endPosition.x && y == standardMove.endPosition.y){
                pieceToMove
              } else {
                piece
              }
          }
        }, Chessboard.changeColour(chessboard.nextColour))
    }
  }

  def getPieces(chessboard: Chessboard): Array[Piece] = {
    chessboard.pieces.flatten.filter(_.isDefined).map(_.get)
  }

  def getPiecesOfColour(chessboard: Chessboard, colour: Colour): Array[Piece] = {
    getPieces(chessboard).filter(_.colour.equals(colour))
  }

  def changeChessboardColour(chessboard: Chessboard): Chessboard = {
    Chessboard(chessboard.pieces, changeColour(chessboard.nextColour))
  }

  def changeColour(colour: Colour): Colour = {
    colour match {
      case White => Black
      case Black => White
    }
  }
}
