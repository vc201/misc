package common

import com.opencsv.CSVParser

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by user on 13/01/17.
  */
class DataSet(data: Array[Data]) {
  private val this.data = data

  def getData: Array[Data] = {
    data
  }
}

object DataSet {
  def makeDataSet(attrs: String, labels: String): DataSet = {
    val parser = new CSVParser()

    var attrArray = new ListBuffer[Array[Int]]
    var labelArray = new ListBuffer[Array[Int]]

    for (line <- Source.fromFile(attrs).getLines()) {
      attrArray += parser.parseLine(line).toList.map(_.toInt).toArray
    }

    for (line <- Source.fromFile(labels).getLines()) {
      labelArray += parser.parseLine(line).toList.map(_.toInt).toArray
    }

    var dataList = new ListBuffer[Data]

    for (i <- attrArray.indices) {
      dataList += new Data(attrArray(i), labelArray(i).head)
    }

    new DataSet(dataList.toArray)
  }

  def mergeDataSets(dataSets: Array[DataSet]): DataSet = {
    var dataArray = new Array[Data](0)

    dataSets.foreach(d => {
      dataArray ++= d.getData
    })

    new DataSet(dataArray)
  }
}


