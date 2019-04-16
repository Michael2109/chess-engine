package chess

import scala.collection.immutable.Stack
import scala.collection.mutable


class Chessboard() {

  var whiteTurn: Boolean = true

  val whitePawns: Long = 8 & 9 & 10 & 11 & 12 & 13 & 14 & 15
  val whiteRooks: Long = 56 & 63
  val whiteKnights: Long = 0
  val whiteBishops: Long = 0
  val whiteQueens: Long = 0
  val whiteKings: Long = 0

  val blackPawns: Long = 48 & 49 & 50 & 51 & 52 & 53 & 54 & 55
  val blackRooks: Long = 0 & 7
  val blackKnights: Long = 0
  val blackBishops: Long = 0
  val blackQueens: Long = 0
  val blackKings: Long = 0

  // https://stackoverflow.com/questions/9354860/how-to-get-the-value-of-a-bit-at-a-certain-position-from-a-byte
  def toBitValue(position: Short): Short ={

  }

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

  def getPieceSet(pieceType: Byte): Long = piecesBB(pieceType)

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

  def movePawn(colour: Colour, oldPosition: Long, newPosition: Long): Unit ={
    piecesBB(PieceType.WhitePiece) = piecesBB(PieceType.WhitePiece)
  }

}
