package chess

trait Move

case class StandardMove(startPosition: Position, endPosition: Position) extends Move
case class KingSideCastling(colour: Colour) extends Move
case class QueenSideCastling(colour: Colour) extends Move
