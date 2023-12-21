package Processing


import Config.AsciiArtConfig
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SaveGrayscale}
import Filter.Filter
import Image.{AsciiArt, GrayscaleImage, Image, RGBImage, RGBImageFactory}


class AsciiArtFacade {

  def processAsciiArt(config: AsciiArtConfig): Unit = {
    val image: RGBImage = RGBImageFactory.createRGBImage(config.image_source)
    val filteredImage: RGBImage = applyFilters(image, config.rgbImageFilters)

    val grayscaleImage: GrayscaleImage = new RgbToGrayscaleConverter().convert(filteredImage)
    val filteredGrayscaleImg: GrayscaleImage = applyFilters(grayscaleImage, config.grayscaleFilters)

    val asciiArt: AsciiArt = new GrayscaleToAsciiConverter().convert(filteredGrayscaleImg, config.table)
    val filteredAsciiArt: AsciiArt = applyFilters(asciiArt, config.asciiFilters)

    config.image_output.save(filteredAsciiArt)
    SaveGrayscale.saveGrayscaleImage(filteredGrayscaleImg, "D:\\dev\\university\\images_for_asciiart\\test_output1.png")
  }

  private def applyFilters[T <: Image](image: T, filters: Array[_ <: Filter[T]]): T = {
    filters.foldLeft(image) { (currentImage, filter) =>
      filter.apply(currentImage)
    }
  }
}