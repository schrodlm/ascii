import Config.AsciiArtConfig
import InputParser.CommandLineParser
import Processing.AsciiArtFacade


object Main{

  def main(args: Array[String]): Unit = {

    //Parse Input
    val parser: CommandLineParser = new CommandLineParser(args)
    val config: AsciiArtConfig = parser.parse().getOrElse(return)


    val asciiArtFacade = new AsciiArtFacade()
    asciiArtFacade.processAsciiArt(config)
  }
}