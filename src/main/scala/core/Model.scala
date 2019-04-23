package core

object Model {

  type Drawing[CMD] = Seq[CMD]
  type Animation[Time,  D] = Time => Drawing[D]

  // Turn a function into a time varying one
  def lift[T, I, R](f: Function1[I, R]): (T => I) => (T => R) =
    (fi:T => I) => (t: T) => f(fi(t))

  // Wrap a constant into a time varying
  def lift[T, R](v: R): (T => _) => (T => R) =
    (fi:T => _) => (t: T) => v
}


