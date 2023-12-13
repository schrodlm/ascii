package Main

import Config.{AsciiArtConfig, PathImageSource}
import Image.{Image, ImageFactory}
import InputParser.CommandLineParser


object Main{

  def main(args: Array[String]) {

    //Parse Input
    val parser: CommandLineParser = new CommandLineParser(args);

    val config: AsciiArtConfig = parser.parse().getOrElse(null)
    if (config == null) return

    val image: Image = ImageFactory.createImage(config.image_source)

    println(s"${image.getWidth()} x ${image.getHeight()}")

  }
}