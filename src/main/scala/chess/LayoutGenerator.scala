package chess

import chess.bitset.BitsetUtils

object LayoutGenerator {

  def emptyChessboard: Array[Long] = Array.fill(12)(0)

  def standardChessboard: Array[Long] = Array(
    // White Pawn
    8 | 9 | 10 | 11 | 12 | 13 | 14 | 15,
    // White Rook
    0 | 7,
    // White Knight
    1| 6,
    // White Bishop
    2| 5,
    // White Queen
    3,
    // White King
    4,

    // Black Pawn
    48 | 49 | 50 | 51 | 52 | 53 | 54 | 55,
    // Black Rook
    //56 | 63,
    0,
    // Black Knight
    //57| 62,
    0,
    // Black Bishop
    //58| 61,
    0,
    // Black Queen
    // 59,
    0,
    // Black King
    //60
    0).map(1L << _)

}
