package chess

trait PieceType
case class Pawn(firstMove: Boolean) extends PieceType
case class Knight() extends PieceType
case class Bishop() extends PieceType
case class Rook() extends PieceType
case class Queen() extends PieceType
case class King() extends PieceType

case class Piece(pieceType: PieceType, colour: Colour) {}
