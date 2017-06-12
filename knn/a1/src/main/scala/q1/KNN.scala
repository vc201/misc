package q1

import common.{Data, DataSet}

import scala.util.Random
import scala.util.control.Breaks._


/**
  * Created by user on 13/01/17.
  */
object KNN {

  // performs KNN using k, returns label
  def evaluate(data: Data, dataSet: DataSet, k: Int): Int = {

    val sorted = dataSet.getData.map(d => {
      (Data.l2(data, d), d.getLabel)
    })
      .toList
      .sortWith((a, b) => {
        a._1 < b._1
      })

    val threshold = sorted(k - 1)._1
    var drop = 0
    var keep = 0

    var i = 0
    breakable {
      while (i < k - 1) {
        if (sorted(i)._1 != threshold) {
          drop += 1
        } else {
          break
        }
        i += 1
      }
    }

    i = k
    breakable {
      while (i < sorted.size) {
        if (sorted(i)._1 == threshold) {
          keep += 1
        } else {
          break
        }
        i += 1
      }
    }

    // random tie breaker
    val shuffledList = Random.shuffle(sorted.drop(drop).take(keep + (k - drop)))

    val classified = (sorted.take(drop) ++ shuffledList.take(k - drop))
      .map(_._2)
      .groupBy(i => i)
      .mapValues(_.size)
      .toList
      .sortWith((a, b) => {
        a._2 > b._2
      })

    if (classified.size < 2) {
      classified.head._1
    }
    else {
      if (classified(0)._2 == classified(1)._2) {
        Random.shuffle(classified).head._1 // second randomized tie breaker
      }
      else {
        classified.head._1
      }
    }
  }
}
