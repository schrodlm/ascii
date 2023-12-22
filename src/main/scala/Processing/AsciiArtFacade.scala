package Processing


import Config.Tables.Table
import Config.{AsciiArtConfig, ImageSource}
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SourceToRgbImage}
import Filter.Filter
import Image.{AsciiArt, GrayscaleImage, Image, RGBImage}

class AsciiArtFacade(config: AsciiArtConfig) {

  def process(): AsciiArt = {


    val rgbImage = loadRgbImage(config.imageSource)
    val filteredRgbImage = applyFilters(rgbImage, config.rgbImageFilters)

    val grayscaleImage = convertToGrayscale(filteredRgbImage)
    val filteredGrayscaleImage = applyFilters(grayscaleImage, config.grayscaleFilters)

    val asciiArt = convertToAsciiArt(filteredGrayscaleImage, config.table)
    val filteredAsciiArt = applyFilters(asciiArt, config.asciiFilters)

    filteredAsciiArt
  }

  private def convertToGrayscale(image: RGBImage): GrayscaleImage = {
    new RgbToGrayscaleConverter(image).convert()
  }

  private def convertToAsciiArt(image: GrayscaleImage, table: Table): AsciiArt = {
    new GrayscaleToAsciiConverter(image, config.table).convert()
  }

  private def loadRgbImage(source: ImageSource): RGBImage = {
    new SourceToRgbImage(config.imageSource).convert()
  }

  private def applyFilters[T <: Image](image: T, filters: Array[_ <: Filter[T]]): T = {
    filters.foldLeft(image) { (currentImage, filter) =>
      filter.apply(currentImage)
    }
  }
}
