package InputParser

import Config.{AsciiArtConfigBuilder, ConsoleImageOutput, PathImageOutput, PathImageSource, RandomImageSource}
import Filter.{BrightnessFilter, FlipFilter, FontAspectRatioFilter, InvertFilter, RotationFilter, ScaleFilter}

import scala.sys.exit

/**
 * A command line parser for configuring ASCII art generation.
 *
 * This parser interprets command line arguments to configure various aspects of ASCII art generation,
 * including image sources, filters, and output options.
 *
 * @param args the command line arguments
 */
class CommandLineParser(val args: Array[String]) extends Parser {

  val usage =
    """
      Usage: parrentmatching [--arg1 num] [--arg2 num] filename
    """

  /**
   * Parses the command line arguments to configure ASCII art generation.
   *
   * This method processes each command line argument and applies the corresponding
   * configuration to the AsciiArtConfigBuilder.
   */
  override def parse(): Unit = {

    if (args.length == 0) println(usage)

    val configBuilder: AsciiArtConfigBuilder = new AsciiArtConfigBuilder()
    val options = nextArg(configBuilder, args.toList)
    println(options)
  }

  /**
   * Recursively processes command line arguments to configure the ASCII art generator.
   *
   * @param configBuilder the current state of the ASCII art configuration builder
   * @param list          the remaining command line arguments to process
   * @return the updated configuration builder
   */
  def nextArg(configBuilder: AsciiArtConfigBuilder, list: List[String]): AsciiArtConfigBuilder = {

    list match {
      case Nil => configBuilder

      case "--image-random" :: tail => {
        nextArg(configBuilder.withImageSource(RandomImageSource()), tail)
      }

      case "--image" :: path :: tail => {
        nextArg(configBuilder.withImageSource(PathImageSource(path)), tail)
      }

      case "--table" :: name :: tail => {
        nextArg(configBuilder.withTable(name), tail)
      }
      case "--custom-table" :: chars :: tail => {
        val table_chars: Array[Char] = chars.toCharArray()
        nextArg(configBuilder.withTable(table_chars), tail)
      }
      case "--rotate" :: degrees :: tail => {
        val rotationFilter: RotationFilter = new RotationFilter(degrees.toInt)
        nextArg(configBuilder.addFilter(rotationFilter), tail)
      }
      case "--scale" :: value :: tail => {
        val scaleFilter: ScaleFilter = new ScaleFilter(value.toInt)
        nextArg(configBuilder.addFilter(scaleFilter), tail)
      }
      case "--invert" :: tail => {
        val invertFilter: InvertFilter = new InvertFilter()
        nextArg(configBuilder.addFilter(invertFilter), tail)
      }
      case "--flip" :: axis :: tail => {
        val flipFilter: FlipFilter = new FlipFilter(axis)
        nextArg(configBuilder.addFilter(flipFilter), tail)
      }
      case "--brightness" :: value :: tail => {
        val brightnessFilter: BrightnessFilter = new BrightnessFilter(value.toInt)
        nextArg(configBuilder.addFilter(brightnessFilter), tail)
      }
      case "--font-aspect-ratio" :: ratio :: tail => {
        parseFontAspectRatio(ratio) match {
          case Some((x, y)) => {
            val fontAspectRatioFilter: FontAspectRatioFilter = new FontAspectRatioFilter(x, y)
            nextArg(configBuilder.addFilter(fontAspectRatioFilter), tail)
          }
          case _ =>
            throw new IllegalArgumentException("Font aspect ration must be in the format 'x:y'.")
        }
      }
      case "--output-console" :: tail => {
        nextArg(configBuilder.withImageOutput(ConsoleImageOutput()), tail)
      }
      case "--output-file" :: path :: tail => {
        nextArg(configBuilder.withImageOutput(PathImageOutput(path)), tail)
      }

      case unknown :: _ =>
        println("Unknown option " + unknown)
        exit(1);
    }
  }

  /**
   * Parses a string representing font aspect ratio in the format "x:y".
   *
   * @param input the input string to parse
   * @return an Option containing a tuple (Int, Int) if parsing is successful, None otherwise
   */
  def parseFontAspectRatio(input: String): Option[(Int, Int)] = {
    input.split(":") match {
      case Array(a, b) =>
        try {
          Some((a.toInt, b.toInt))
        } catch {
          case _: NumberFormatException => None
        }
      case _ => None
    }
  }

}
