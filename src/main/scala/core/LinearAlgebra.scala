package core
object LinearAlgebra {

  import shapeless._
  import nat._
  import newtype._
  import ops.hlist.{Mapper, Transposer}
  import ops.product.ProductLength

  abstract class VecOps[N <: Nat, P <: Product](p: P) {
    type Self = Newtype[P, VecOps[N, P]]

    def tupled = p

    def +(other: Self): Self
  }

  object VecOps {
    type HomPair[T] = (T, T)

    object sum extends Poly1 {
      implicit def caseDouble = at[Double :: Double :: HNil] { case a :: b :: HNil => a + b }
    }

    implicit def pointOps1(p: Tuple1[Double]): VecOps[_1, Tuple1[Double]] = new VecOps[_1, Tuple1[Double]](p) {
      def +(other: Self): Self = newtype(Tuple1(p._1 + other.tupled._1))
    }

    implicit def pointOpsN[N <: Nat, LN <: HList, PN <: Product, ZLN <: HList]
    (implicit
     gen: Generic.Aux[PN, LN],
     zipper: Transposer.Aux[LN :: LN :: HNil, ZLN],
     mapper: Mapper.Aux[sum.type, ZLN, LN]): PN => VecOps[N, PN] =
      (p: PN) =>
        new VecOps[N, PN](p) {
          def +(other: Self): Self =
            newtype(gen.from((gen.to(p) :: gen.to(other.tupled) :: HNil).transpose.map(sum)))
        }
  }

  def Vec(p: Double) = newtype[Tuple1[Double], VecOps[_1, Tuple1[Double]]](Tuple1(p))

  def Vec[P <: Product, N <: Nat](p: P)(implicit ar: ProductLength.Aux[P, N]) = newtype[P, VecOps[N, P]](p)

  type V1 = Newtype[Tuple1[Double], VecOps[_1, Tuple1[Double]]]
  type V2 = Newtype[(Double, Double), VecOps[_2, (Double, Double)]]
  type V3 = Newtype[(Double, Double, Double), VecOps[_3, (Double, Double, Double)]]
}