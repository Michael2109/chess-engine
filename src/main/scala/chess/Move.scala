package chess

trait Move

case class StandardMove(pieceType: Byte, startPosition: Short, endPosition: Short) extends Move
case class KingSideCastling(colour: Colour) extends Move
case class QueenSideCastling(colour: Colour) extends Move
