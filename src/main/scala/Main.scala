package Main

import Config.{AsciiArtConfig, PathImageSource}
import Conversion.{RgbToGrayscaleConverter, SaveGrayscale}
import Image.{GrayscaleImage, RGBImage, RGBImageFactory}
import InputParser.CommandLineParser


object Main{

  def main(args: Array[String]) {

    //Parse Input
    val parser: CommandLineParser = new CommandLineParser(args);

    val config: AsciiArtConfig = parser.parse().getOrElse(null)
    if (config == null) return

    val image: RGBImage = RGBImageFactory.createRGBImage(config.image_source)

    val grayscaleImg : GrayscaleImage = new RgbToGrayscaleConverter().convert(image)
    SaveGrayscale.saveGrayscaleImage(grayscaleImg, "D:\\dev\\university\\images_for_asciiart\\test_output.png")

  }
}