package Processing


import Config.{AsciiArtConfig, ImageOutput}
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SaveGrayscale, SourceToRgbImage}
import Filter.Filter
import Image.{AsciiArt, GrayscaleImage, Image, RGBImage}


class AsciiArtFacade {

  def processAsciiArt(config: AsciiArtConfig,
                      rgbConverter: SourceToRgbImage,
                      grayscaleConverter: RgbToGrayscaleConverter,
                      asciiConvertor: GrayscaleToAsciiConverter
                     ): Unit =
  {
    val image: RGBImage = rgbConverter.convert()
    val filteredImage: RGBImage = applyFilters(image, config.rgbImageFilters)

    val grayscaleImage: GrayscaleImage = grayscaleConverter.convert()
    val filteredGrayscaleImg: GrayscaleImage = applyFilters(grayscaleImage, config.grayscaleFilters)

    val asciiArt: AsciiArt = asciiConvertor.convert()
    val filteredAsciiArt: AsciiArt = applyFilters(asciiArt, config.asciiFilters)

    saveAsciiArt(filteredAsciiArt, config.imageOutput)
  }

  private def saveAsciiArt(image: AsciiArt, output: ImageOutput): Unit =
  {
    output.save(image)
  }

  private def applyFilters[T <: Image](image: T, filters: Array[_ <: Filter[T]]): T = {
    filters.foldLeft(image) { (currentImage, filter) =>
      filter.apply(currentImage)
    }
  }
}