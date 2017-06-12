package q1

import common.DataSet

import scala.collection.mutable.ListBuffer

/**
  * Created by user on 13/01/17.
  */
object Main {
  def main(args: Array[String]): Unit = {

    val fmtPath = "/home/user/shared/knn-dataset/%s%d.csv"

    val dataBaseName = "data"
    val labelsBaseName = "labels"
    var dataSets = new ListBuffer[DataSet]

    for (i <- 1 to 10) {
      dataSets += DataSet.makeDataSet(fmtPath.format(dataBaseName, i), fmtPath.format(labelsBaseName, i))
    }

    var testSets = new ListBuffer[DataSet]
    var trainingSets = new ListBuffer[DataSet]

    for (i <- dataSets.indices) {
      testSets += dataSets(i)
      trainingSets += DataSet.mergeDataSets((dataSets.take(i) ++ dataSets.takeRight(9 - i)).toArray)
    }


    for (k <- 1 to 30) {
      var correct = 0.0
      var total = 0.0
      //10-fold validation
      for (i <- 0 to 9) {
        val testSet = testSets(i).getData
        val trainingSet = trainingSets(i)

        for (j <- testSet.indices) {
          total += 1
          if (KNN.evaluate(testSet(j), trainingSet, k) == testSet(j).getLabel) {
            correct += 1
          }
        }
      }
      println(s"For k = $k: Accuracy of $correct/$total (${correct / total})")
    }
  }
}

/*
For k = 1: Accuracy of 814.0/1110.0 (0.7333333333333333)
For k = 2: Accuracy of 809.0/1110.0 (0.7288288288288288)
For k = 3: Accuracy of 877.0/1110.0 (0.7900900900900901)
For k = 4: Accuracy of 870.0/1110.0 (0.7837837837837838)
For k = 5: Accuracy of 875.0/1110.0 (0.7882882882882883)
For k = 6: Accuracy of 859.0/1110.0 (0.7738738738738739)
For k = 7: Accuracy of 881.0/1110.0 (0.7936936936936937)
For k = 8: Accuracy of 878.0/1110.0 (0.790990990990991)
For k = 9: Accuracy of 878.0/1110.0 (0.790990990990991)
For k = 10: Accuracy of 894.0/1110.0 (0.8054054054054054)
For k = 11: Accuracy of 894.0/1110.0 (0.8054054054054054)
For k = 12: Accuracy of 887.0/1110.0 (0.7990990990990992)
For k = 13: Accuracy of 895.0/1110.0 (0.8063063063063063)
For k = 14: Accuracy of 874.0/1110.0 (0.7873873873873873)
For k = 15: Accuracy of 896.0/1110.0 (0.8072072072072072)
For k = 16: Accuracy of 883.0/1110.0 (0.7954954954954955)
For k = 17: Accuracy of 886.0/1110.0 (0.7981981981981981)
For k = 18: Accuracy of 892.0/1110.0 (0.8036036036036036)
For k = 19: Accuracy of 898.0/1110.0 (0.809009009009009)
For k = 20: Accuracy of 901.0/1110.0 (0.8117117117117117)
For k = 21: Accuracy of 891.0/1110.0 (0.8027027027027027)
For k = 22: Accuracy of 898.0/1110.0 (0.809009009009009)
For k = 23: Accuracy of 906.0/1110.0 (0.8162162162162162)
For k = 24: Accuracy of 900.0/1110.0 (0.8108108108108109)
For k = 25: Accuracy of 894.0/1110.0 (0.8054054054054054)
For k = 26: Accuracy of 894.0/1110.0 (0.8054054054054054)
For k = 27: Accuracy of 898.0/1110.0 (0.809009009009009)
For k = 28: Accuracy of 895.0/1110.0 (0.8063063063063063)
For k = 29: Accuracy of 900.0/1110.0 (0.8108108108108109)
For k = 30: Accuracy of 886.0/1110.0 (0.7981981981981981)
 */