package chess.bitset

object BitsetUtils {

  def bitset(positions: Short*): Long ={
    positions.foldLeft(0L)((a,b) => (1L << a) & (1L << b))
  }

}
