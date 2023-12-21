package InputParser

import Config.{AsciiArtConfig, ConsoleImageOutput, PathImageOutput, PathImageSource, RandomImageSource}
import Filter.{BrightnessFilter, FlipFilter, FontAspectRatioFilter, InvertFilter, RotationFilter, ScaleFilter}


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
      |Usage: asciiart [options] [source]
      |
      |Mandatory Options:
      |  One of the following image source options must be specified:
      |    --image-random                 Use a random image as the source.
      |    --image [path]                 Specify the path of the image file to use as the source.
      |
      |  One of the following output options must be specified:
      |    --output-console               Output the ASCII art to the console.
      |    --output-file [path]           Output the ASCII art to a file at the specified path.
      |
      |Optional Options:
      |  --table [name]                 Specify the name of the predefined character table ('default', 'mathematical', 'nonlinear-default').
      |  --custom-table [chars]         Define a custom character table.
      |  --rotate [degrees]             Apply rotation to the image (degrees must be an integer). ! NOT IMPLEMENTED YET !
      |  --scale [value]                Scale the image by a specified factor (value must be an integer).
      |  --invert                       Apply an inversion filter to the image. ! NOT IMPLEMENTED YET !
      |  --flip [axis]                  Flip the image along a specified axis ('horizontal' or 'vertical').! NOT IMPLEMENTED YET !
      |  --brightness [value]           Adjust the brightness of the image (value must be an integer).
      |  --font-aspect-ratio [x:y]      Set the font aspect ratio with two integers (e.g., '1:2'). ! NOT IMPLEMENTED YET !
      |
      |Examples:
      |  asciiart --image /path/to/image.jpg --rotate 90 --output-console
      |  asciiart --image-random --brightness 5 --output-file /path/to/output.txt
      |
      |Note:
      |  Some options might require additional arguments.
   """.stripMargin

  /**
   * Parses the command line arguments to configure ASCII art generation.
   *
   * This method processes each command line argument and applies the corresponding
   * configuration to the AsciiArtConfigBuilder.
   */
  override def parse(): Option[AsciiArtConfig] = {

    if (args.length == 0 || args.contains("--help")) {
      println(usage)
      return None
    }

    val configBuilder : AsciiArtConfig.Builder =  nextArg(new AsciiArtConfig.Builder(), args.toList)
    Some(configBuilder.build())
  }

  /**
   * Recursively processes command line arguments to configure the ASCII art generator.
   *
   * @param configBuilder the current state of the ASCII art configuration builder
   * @param list          the remaining command line arguments to process
   * @return the updated configuration builder
   */
  def nextArg(configBuilder: AsciiArtConfig.Builder, list: List[String]): AsciiArtConfig.Builder = {

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
        throw new IllegalArgumentException("Unknown option " + unknown)
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
