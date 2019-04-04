package chess

case class Position(xInit: Int, yInit: Int) {
  val x: Int = xInit
  val y: Int = yInit
}

object PositionUtils {
  def validPosition(position: Position): Boolean = {

    def valid(value: Int): Boolean = value >= 0 || value <= 7

    valid(position._1) && valid(position._2)
  }

  def addPositions(position1: Position, position2: Position): Position = {
    Position(position1.x + position2.x, position1.y + position2.y)
  }
}
