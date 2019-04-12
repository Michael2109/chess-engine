package chess

import scala.collection.mutable

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

  val changes: mutable.Map[Position, Option[Piece]] = mutable.HashMap()

  def makeMove(chessboard: Chessboard, move: Move): Chessboard = {
    changes.clear()
    move match {
      case StandardMove(startPosition, endPosition) =>
        val pieceToMove = pieceAtPosition(chessboard, startPosition)

        val updatedPieceToMove = pieceToMove match {
          case Some(piece) => piece match {
            case piece: Piece => piece.pieceType match {
              case _: Pawn => Option.apply(Piece(Pawn(false), piece.colour))
              case _ => Some(piece)
            }
          }
        }

        changes += (startPosition -> pieceToMove)
        changes += (endPosition -> pieceAtPosition(chessboard, endPosition))

        chessboard.pieces(endPosition.y)(endPosition.x) = updatedPieceToMove
        chessboard.pieces(startPosition.y)(startPosition.x) = None
    }

    Chessboard.changeChessboardColour(chessboard)
  }

  def undoMove(chessboard: Chessboard): Chessboard = {
    changes.foreach(entry => {
      chessboard.pieces(entry._1.y)(entry._1.x)  = entry._2
    })
    Chessboard.changeChessboardColour(chessboard)
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
