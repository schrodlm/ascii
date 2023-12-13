package InputParser

import Config.{AsciiArtConfig, AsciiArtConfigBuilder, PathImageSource, RandomImageSource, Table}

import java.nio.file.Path
import scala.sys.exit

class CommandLineParser(val args: Array[String]) extends Parser{

  val usage =
    """
      Usage: parrentmatching [--arg1 num] [--arg2 num] filename
    """

  override def parse(): Unit = {

    if(args.length == 0) println(usage)

    val configBuilder : AsciiArtConfigBuilder = new AsciiArtConfigBuilder()
    val options = nextArg(configBuilder, args.toList)
    println(options)

    checkNecessaryInputs()
  }

  def nextArg(configBuilder: AsciiArtConfigBuilder, list: List[String]): AsciiArtConfigBuilder = {

    list match {
      case Nil => configBuilder

      case "--image-random" :: tail =>
      {
        nextArg(configBuilder.withImageSource(RandomImageSource()), tail)
      }

      case "--image" :: path :: tail =>
      {
        nextArg(configBuilder.withImageSource(PathImageSource(path)), tail)
      }

      case "--table" :: name :: tail =>
      {
        nextArg(configBuilder.withTable(name), tail)
      }
      case "--custom-table" :: chars :: tail =>
      {
        val table_chars : Array[Char] = chars.toCharArray()
        nextArg(configBuilder.withTable(table_chars), tail)
      }

      case "--rotate" :: degrees :: tail => {

      }
      case unknown :: _ =>
        println("Unknown option " + unknown)
        exit(1);
    }
  }

  def checkNecessaryInputs(): Boolean ={
    true
  }

}
