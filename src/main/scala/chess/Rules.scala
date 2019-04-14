package chess

object Rules {

  def allPossibleMoves(chessboard: Chessboard): Array[Move] = chessboard.pieces.zipWithIndex.flatMap {
    case (row, y) => row.zipWithIndex.map {
      case (piece, x) => piece match {
        case Some(_) => Rules.possibleMoves(chessboard, Position(x, y))
        case None => List()
      }

    }
  }.flatten

  def allPossibleMovesForColour(chessboard: Chessboard, colour: Colour): Array[Move] = chessboard.pieces.zipWithIndex.flatMap {
    case (row, y) => row.zipWithIndex.map {
      case (piece, x) => piece match {
        case Some(p) =>
          if (p.colour == chessboard.nextColour) {
            Rules.possibleMoves(chessboard, Position(x, y))
          } else {
            List()
          }
        case None => List()
      }

    }
  }.flatten


  def possibleMoves(chessboard: Chessboard, position: Position): List[Move] = {
    val pieceAtPosition = chessboard.pieceAtPosition(position)
    pieceAtPosition match {
      case Some(piece) =>
        piece match {
          case Piece(pieceType, colour) => pieceType match {
            case _: Rook => rookMoves(chessboard, position, colour)
            case _: Knight => knightMoves(chessboard, position, colour)
            case _: Bishop => bishopMoves(chessboard, position, colour)
            case _:Queen => queenMoves(chessboard, position, colour)
            case _:King => kingMoves(chessboard, position, colour)
            case pawn: Pawn => pawnMoves(chessboard, position, colour, pawn)
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

  def pawnMoves(chessboard: Chessboard, position: Position, colour: Colour, pawn: Pawn): List[Move] = {

    val forwardDirection = if (colour == White) Position(0, -1) else Position(0, 1)
    val forwardMove: List[Move] = movesInDirection(chessboard, position, forwardDirection, colour, if(pawn.firstMove) 2 else 1)


    val attackDirections: List[Position] = if (colour == White) List(Position(-1, -1), Position(1, -1)) else List(Position(-1, 1), Position(1, 1))
    val attackMoves: List[Move] = attackDirections.flatMap(direction => movesInDirection(chessboard, position, direction, colour, 1)).filter(takingAPiece(chessboard, _))

    forwardMove.filter(move => move match {
      case StandardMove(_, endPosition) => chessboard.pieceColourAtPosition(endPosition).isEmpty
    }) ++ attackMoves
  }

  private def movesInDirection(chessboard: Chessboard, currentPosition: Position, direction: Position, colour: Colour, movesLeft: Int): List[Move] = {

    def movesInDirection(chessboard: Chessboard, startPosition: Position, currentPosition: Position, direction: Position, colour: Colour, movesLeft: Int): List[Move] = {
      val nextPosition = Position.addPositions(currentPosition, direction)
      val nextMove = StandardMove(startPosition, nextPosition)
      if (movesLeft != 0 && Position.validPosition(nextPosition)) {
        if (movingToEmptySpace(chessboard, nextMove)) {
          movesInDirection(chessboard, startPosition, nextPosition, direction, colour, (movesLeft - 1)) ++ List(nextMove)
        } else if (takingAPiece(chessboard, nextMove)) {
          List(nextMove)
        } else {
          List()
        }
      } else {
        List()
      }

    }

    movesInDirection(chessboard, currentPosition, currentPosition, direction, colour, movesLeft)
  }

  private def takingAPiece(chessboard: Chessboard, move: Move): Boolean = {
    move match {
      case StandardMove(startPosition, endPosition) =>
        val pieceColourStart = chessboard.pieceColourAtPosition(startPosition)
        val pieceColourEnd = chessboard.pieceColourAtPosition(endPosition)
        val differentColours = pieceColourStart match {
          case Some(colourA) => pieceColourEnd match {
            case Some(colourB) => colourB != colourA
            case None => false
          }
          case None => false
        }
        Position.validPosition(startPosition) && Position.validPosition(endPosition) && differentColours
    }
  }

  private def movingToEmptySpace(chessboard: Chessboard, move: Move): Boolean = {
    move match {
      case StandardMove(_, endPosition) =>
        chessboard.pieceAtPosition(endPosition).isEmpty

    }
  }
}
