package common

/**
  * Created by user on 13/01/17.
  */
class Data(attrs: Array[Int], label:Int) {
  private val this.attrs = attrs
  private val this.label = label

  def getLabel: Int = {
    label
  }

  def getAttrs: Array[Int] = {
    attrs
  }
}

object Data {
  def l2(a: Data, b: Data): Double = {
    Math.sqrt(a.getAttrs.zip(b.getAttrs)
      .map(p =>
        Math.pow(Math.abs(p._1 - p._2), 2.0))
      .sum)
  }
}
