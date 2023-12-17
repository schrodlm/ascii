package Main

import Config.{AsciiArtConfig, PathImageSource}
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SaveGrayscale}
import Image.{AsciiArt, GrayscaleImage, RGBImage, RGBImageFactory}
import InputParser.CommandLineParser


object Main{

  def main(args: Array[String]) {

    //Parse Input
    val parser: CommandLineParser = new CommandLineParser(args);

    val config: AsciiArtConfig = parser.parse().getOrElse(null)
    if (config == null) return

    val image: RGBImage = RGBImageFactory.createRGBImage(config.image_source)
    val grayscaleImg : GrayscaleImage = new RgbToGrayscaleConverter().convert(image)
    val asciiArt : AsciiArt = new GrayscaleToAsciiConverter().convert(grayscaleImg, config.table)

    config.image_output.save(asciiArt)


    SaveGrayscale.saveGrayscaleImage(grayscaleImg, "D:\\dev\\university\\images_for_asciiart\\test_output.png")

  }
}