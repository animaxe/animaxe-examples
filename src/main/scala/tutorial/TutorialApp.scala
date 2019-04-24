package tutorial.webapp
import core.Ctx2DRenderer.PlotterCmd2D
import core.Model.Drawing
import core._
import org.scalajs.dom
import dom.{CanvasRenderingContext2D, document}
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.HTMLElement
import core.LinearAlgebra._

object TutorialApp {

  def appendCanvas(target: HTMLElement): CanvasRenderingContext2D = {
    val c = document.createElement("canvas").asInstanceOf[Canvas]
    target.appendChild(c)
    val w = 300
    c.width = w
    c.height = w
    c.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  }

  def main(args: Array[String]): Unit = {
    //val v =  Vector[Int :: Int :: HNil, Int](HList((0, 1)))
    val v1 =  Vec(0, 1)
    val v2 =  Vec(0, 1)
    //val v3 = v1 + v2

    appendPar(document.body, "Scala is cool")
    val ctx = appendCanvas(document.body)
    Ctx2DRenderer.animate(ctx, t => {
      rect(0, 0, 300, 300, "black") ++
        face(Math.sin(t) * 150 + 150)
    })
  }

  // TODO difficulty type safetying it
  def orth3(i: (Double, Double),
            j: (Double, Double),
            k: (Double, Double),
            vec: (Double, Double, Double)): (Double, Double) =
    (vec._1 * i._1 + vec._2 * j._1 + vec._3 * k._1,
      vec._1 * i._2 + vec._2 * j._2 + vec._3 * k._2)

  // Non-time varying
  def face(w: Double): Drawing[PlotterCmd2D] = List(
    StrokeStyle("red"),
    LineWidth(5),
    BeginPath(),
    MoveTo(w / 3, 0),
    LineTo(w / 3, w / 3),
    MoveTo(w * 2 / 3, 0),
    LineTo(w * 2 / 3, w / 3),
    MoveTo(w, w / 2),
    Stroke(),
  )

  def rect(x: Double, y: Double, w: Double, h: Double, style: String): Drawing[PlotterCmd2D] = List(
    FillStyle(style),
    BeginPath(),
    MoveTo(x, y),
    LineTo(x + w, y),
    LineTo(x + w, y + h),
    LineTo(x, y + h),
    LineTo(x, y),
    Fill(),
  )


  // TODO difficulty type safetying it
  def hypercube3[D <: Product](dim: Int): Drawing[PlotterCmd[(Double, Double, Double)]] = List(
    BeginPath(),
    MoveTo(0, 0, 0),
    LineTo(0, 0, 1),
    LineTo(0, 1, 1),
    LineTo(0, 1, 1),
    LineTo(1, 1, 1),
    LineTo(1, 1, 0),
    LineTo(1, 0, 0),
    LineTo(1, 0, 0),
    LineTo(1, 0, 1),
    LineTo(1, 1, 1),
    Stroke(),
  )

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }
}
