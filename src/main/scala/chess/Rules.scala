package chess

object Rules {

  def possibleMoves(chessboard: Chessboard, position: Position): List[Move] = {
    val pieceAtPosition = Chessboard.pieceAtPosition(chessboard, position)
    pieceAtPosition match {
      case Some(piece) => {
        piece match {
          case Piece(pieceType, colour) => pieceType match {
            case Rook => rookMoves(chessboard, position, colour)
            case Knight => knightMoves(chessboard, position, colour)
            case Bishop => bishopMoves(chessboard, position, colour)
            case Queen => queenMoves(chessboard, position, colour)
            case King => kingMoves(chessboard, position, colour)
            case Pawn => pawnMoves(chessboard, position, colour)
          }
        }
      }
      case None => List()
    }
  }

  def rookMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {
    val directions = List(Position(0, 1), Position(1, 0), Position(0, -1), Position(-1, 0))
    directions.flatMap(direction => movesInDirection(chessboard, position, direction, colour, 8))
  }

  def knightMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {
    val offsets = List(Position(-2, -1), Position(-2, 1), Position(2, -1), Position(2, 1), Position(-1, -2), Position(-1, 2), Position(1, -2), Position(1, 2))
    val newMoves = offsets.map(offset => StandardMove(position, Position.addPositions(position, offset)))
    newMoves.filter(move => Position.validPosition(move.endPosition) && (takingAPiece(chessboard, move) || movingToEmptySpace(chessboard, move)))
  }

  def bishopMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {
    val directions = List(Position(1, 1), Position(1, -1), Position(-1, -1), Position(-1, 1))
    directions.flatMap(direction => movesInDirection(chessboard, position, direction, colour, 8))
  }

  def queenMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {
    val directions = List(Position(0, 1), Position(1, 0), Position(0, -1), Position(-1, 0), Position(1, 1), Position(1, -1), Position(-1, -1), Position(-1, 1))
    directions.flatMap(direction => movesInDirection(chessboard, position, direction, colour, 8))
  }

  def kingMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {
    val directions = List(Position(0, 1), Position(1, 0), Position(0, -1), Position(-1, 0), Position(1, 1), Position(1, -1), Position(-1, -1), Position(-1, 1))
    directions.flatMap(direction => movesInDirection(chessboard, position, direction, colour, 1))
  }

  def pawnMoves(chessboard: Chessboard, position: Position, colour: Colour): List[Move] = {

    /**
      *
      *
    let direction =
        if colour == White
          then (0, -1)
          else (0, 1)
  let forwardMoves = movesInDirection chessboard position position direction colour 1

  let attackDirections =
        if colour == White
          then [(-1, -1), (1, -1)]
          else [(-1, 1), (1, 1)]

  let attackMoves =
        filter (takingAPiece chessboard) $ concat $ map (\attackDirection -> movesInDirection chessboard position position attackDirection colour 1) attackDirections

  filter
    (\move ->
       case move of
         Move startPos endPos -> pieceColourAtPosition chessboard endPos == Nothing)
    forwardMoves ++ attackMoves



      */

    List()
  }

  private def movesInDirection(chessboard: Chessboard, currentPosition: Position, direction: Position, colour: Colour, movesLeft: Int): List[Move] = {

    def movesInDirection(chessboard: Chessboard, startPosition: Position, currentPosition: Position, direction: Position, colour: Colour, movesLeft: Int): List[Move] =
    {
      List()
    }

    movesInDirection(chessboard, currentPosition, currentPosition, direction, colour, movesLeft)
  }

  private def takingAPiece(chessboard: Chessboard, move: Move): Boolean ={
    move match {
      case StandardMove (startPosition, endPosition) =>
        Position.validPosition(startPosition) && Position.validPosition(endPosition) &&
          Chessboard.pieceColourAtPosition(chessboard, startPosition).isDefined &&
          !Chessboard.pieceColourAtPosition(chessboard, startPosition).equals(Chessboard.pieceColourAtPosition(chessboard, endPosition))
    }
  }

  private def movingToEmptySpace(chessboard: Chessboard, move: Move): Boolean ={
    move match {
      case StandardMove(_, endPosition) => {
        Chessboard.pieceAtPosition(chessboard, endPosition) match {
          case Some(_) => false
          case None => true
        }
      }
    }
  }
}
