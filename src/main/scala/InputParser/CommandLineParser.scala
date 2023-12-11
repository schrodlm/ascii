package InputParser

import Config.AsciiArtConfig

import java.nio.file.Path
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

      case "--image-random" :: tail =>
      {
        nextArg(map ++ Map("image-random" -> true), tail)
      }

      case "--image" :: path :: tail =>
      {
        nextArg(map ++ Map("image" -> path), tail)
      }

      case "--table" :: name :: tail =>
      {
        nextArg(map ++ Map("table" -> name), tail)
      }
      case "--custom-table" :: Array

      case unknown :: _ =>
        println("Unknown option " + unknown)
        exit(1);
    }
  }

  def checkNecessaryInputs(): Boolean ={
    true
  }

}
