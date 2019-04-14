package chess

trait Colour

case object White extends Colour
case object Black extends Colour

object Colour {

  def changeColour(colour: Colour): Colour ={
    colour match {
      case White => Black
      case Black => White
    }
  }
}

