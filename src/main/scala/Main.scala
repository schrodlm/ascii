package Main

import Config.{AsciiArtConfig, PathImageSource}
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SaveGrayscale}
import Image.{AsciiArt, GrayscaleImage, RGBImage, RGBImageFactory}
import InputParser.CommandLineParser
import Processing.{AsciiArtFacade}


object Main{

  def main(args: Array[String]) {

    //Parse Input
    val parser: CommandLineParser = new CommandLineParser(args);
    val config: AsciiArtConfig = parser.parse().getOrElse(return)


    val asciiArtFacade = new AsciiArtFacade()
    asciiArtFacade.processAsciiArt(config)
  }
}