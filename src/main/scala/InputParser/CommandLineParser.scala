package InputParser

import scala.sys.exit

class CommandLineParser(val args: Array[String]) extends Parser{

  val usage =
    """
      Usage: parrentmatching [--arg1 num] [--arg2 num] filename
    """

  override def parse(): Unit = {

    if(args.length == 0) println(usage)

    val options = nextArg(Map(), args.toList)
    println(options)

    checkNecessaryInputs()
  }

  def nextArg(map: Map[String,Any], list: List[String]): Map[String,Any] = {

    list match {
      case Nil => map
      case "--arg1" :: value :: tail =>
        nextArg(map ++ Map("arg1" -> value.toInt), tail)
      case "--arg2" :: value :: tail =>
        nextArg(map ++ Map("arg2" -> value.toInt), tail)
      case unknown :: _ =>
        println("Unknown option " + unknown)
        exit(1);
    }
  }

  def checkNecessaryInputs(): Boolean ={
    true
  }

}
