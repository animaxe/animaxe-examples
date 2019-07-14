import core.Vectors._
import core._
import core.Ctx2DRenderer._
import core.Model._
object Shapes {

  // Turn a function into a time varying one
  def triangle(v1: Vec2, v2: Vec2, v3: Vec2): Drawing[PlotterCmd2D] = 
    List(
        MoveTo(v1),
        LineTo(v2),
        LineTo(v3),
        LineTo(v1)
    )

}