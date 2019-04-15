package chess

import scala.collection.immutable.Stack
import scala.collection.mutable

private class Pieces extends Enumeration {
  val WhitePieces,
  BlackPieces,
  Pawns,
  Knights,
  Bishops,
  Rooks,
  Queens,
  Kings = Value
}

class Chessboard(piecesBB: Array[Long]) {

  var whiteTurn: Boolean = true

  def pieceAtPosition(position: Position): Option[Piece] = {
    pieces(position.y)(position.x)
  }

  def pieceColourAtPosition(position: Position): Option[Colour] = {
    pieceAtPosition(position) match {
      case Some(piece) => Some(piece.colour)
      case None => None
    }
  }

  val moveHistory: mutable.Stack[Stack[(Position, Option[Piece])]] = mutable.Stack()

  def makeMove(move: Move): Unit = {
/*    move match {
      case StandardMove(startPosition, endPosition) =>

        val pieceToMove = pieceAtPosition(startPosition)

        val updatedPieceToMove = pieceToMove match {
          case Some(piece) => piece match {
            case piece: Piece => piece.pieceType match {
              case _: Pawn => Option.apply(Piece(Pawn(false), piece.colour))
              case _ => Some(piece)
            }
          }
          case None => throw new Exception("No piece found: " + startPosition)
        }

        moveHistory.push(Stack((startPosition, pieceToMove), (endPosition, pieceAtPosition(endPosition))))

        pieces(endPosition.y)(endPosition.x) = updatedPieceToMove
        pieces(startPosition.y)(startPosition.x) = None
    }

    nextColour = Colour.changeColour(nextColour)*/
  }

  def undoMove(): Unit = {
/*
    val lastMoves = moveHistory.pop()
    lastMoves.foreach(move => {
      pieces(move._1.y)(move._1.x) = move._2
    })


    nextColour = Colour.changeColour(nextColour)*/

    whiteTurn = !whiteTurn
  }

  def getPieces(): Array[Piece] = {
    pieces.flatten.filter(_.isDefined).map(_.get)
  }

  def getPiecesOfColour(colour: Colour): Array[Piece] = {
    getPieces().filter(_.colour.equals(colour))
  }

  def getPieceSet(pieceType: Byte): Long = pieceBB(pieceType)

  def getWhitePawns: Long = piecesBB(PieceType.Pawn) & piecesBB(PieceType.WhitePiece)

  def getBlackPawns: Long = piecesBB(PieceType.Pawn) & piecesBB(PieceType.BlackPiece)

  def getWhiteRook: Long = piecesBB(PieceType.Rook) & piecesBB(PieceType.WhitePiece)

  def getBlackRook: Long = piecesBB(PieceType.Rook) & piecesBB(PieceType.BlackPiece)

  def getWhiteKnight: Long = piecesBB(PieceType.Knight) & piecesBB(PieceType.WhitePiece)

  def getBlackKnight: Long = piecesBB(PieceType.Knight) & piecesBB(PieceType.BlackPiece)

  def getWhiteBishop: Long = piecesBB(PieceType.Bishop) & piecesBB(PieceType.WhitePiece)

  def getBlackBishop: Long = piecesBB(PieceType.Bishop) & piecesBB(PieceType.BlackPiece)

  def getWhiteQueen: Long = piecesBB(PieceType.Queen) & piecesBB(PieceType.WhitePiece)

  def getBlackQueen: Long = piecesBB(PieceType.Queen) & piecesBB(PieceType.BlackPiece)

  def getWhiteKing: Long = piecesBB(PieceType.King) & piecesBB(PieceType.WhitePiece)

  def getBlackKing: Long = piecesBB(PieceType.King) & piecesBB(PieceType.BlackPiece)

}
