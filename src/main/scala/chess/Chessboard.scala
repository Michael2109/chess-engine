package chess

class Chessboard(pieces: List[List[Option[Piece]]], nextColour: Colour) {

    def pieceAtPosition(position: Position): Option[Piece] ={
      pieces(position.y)(position.x)
    }


}
