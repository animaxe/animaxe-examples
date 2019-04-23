package core

import core.Model.Drawing
import org.scalajs.dom.CanvasRenderingContext2D

sealed trait PlotterCmd[D <: Product]

case class BeginPath[D <: Product]() extends PlotterCmd[D]
case class MoveTo[D <: Product](x: D) extends PlotterCmd[D]
case class LineTo[D <: Product](x: D) extends PlotterCmd[D]
case class Stroke[D <: Product]() extends PlotterCmd[D]
case class Fill[D <: Product]() extends PlotterCmd[D]
case class StrokeStyle[D <: Product](style: String) extends PlotterCmd[D]
case class FillStyle[D <: Product](style: String) extends PlotterCmd[D]
case class LineWidth[D <: Product](style: Double) extends PlotterCmd[D]

object Ctx2DRenderer {
  type PlotterCmd2D = PlotterCmd[(Double, Double)]
  def drawFrame(ctx: CanvasRenderingContext2D, cmds: Seq[PlotterCmd2D]) = {
    cmds.foreach {
      case LineWidth(width) => ctx.lineWidth = width
      case BeginPath() => ctx.beginPath()
      case MoveTo(v) => ctx.moveTo(v._1, v._2)
      case LineTo(v) => ctx.lineTo(v._1, v._2)
      case Stroke() => ctx.stroke()
      case StrokeStyle(style) => ctx.strokeStyle = style
      case Fill() => ctx.fill()
      case FillStyle(style) => ctx.fillStyle = style
    }
  }

  def animate(ctx: CanvasRenderingContext2D, animation: Double => Drawing[PlotterCmd2D]): Unit = {
    var run = true

    def paint(t: Double): Unit = {
      drawFrame(ctx, animation(t))
      // TODO we can probably express this in pure Animaxe manipulation of the
      // animation TimeVaryingFunction
      // TODO we should return a subscription to stop this too
      if (run) org.scalajs.dom.window.requestAnimationFrame(paint)
    }

    org.scalajs.dom.window.requestAnimationFrame(paint)
  }
}


