package Processing


import Config.AsciiArtConfig
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SaveGrayscale, SourceToRgbImage}
import Filter.Filter
import Image.{AsciiArt, GrayscaleImage, Image, RGBImage}


class AsciiArtFacade {

  def processAsciiArt(config: AsciiArtConfig): Unit = {
    val image: RGBImage = new SourceToRgbImage(config.image_source).convert()
    val filteredImage: RGBImage = applyFilters(image, config.rgbImageFilters)

    val grayscaleImage: GrayscaleImage = new RgbToGrayscaleConverter(filteredImage).convert()
    val filteredGrayscaleImg: GrayscaleImage = applyFilters(grayscaleImage, config.grayscaleFilters)

    val asciiArt: AsciiArt = new GrayscaleToAsciiConverter(filteredGrayscaleImg, config.table).convert()
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