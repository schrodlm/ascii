package Conversion
import Image.{GrayscaleImage, Image, RGBImage}

class RgbToGrayscaleConverter(){

  def convert(image : RGBImage) : GrayscaleImage = {

    val width = image.getWidth()
    val height = image.getHeight()

    val grayscaleData = image.getData().map { pixel =>
      val (alpha, red, green, blue) = image.getPixelColor(pixel)

      // Calculate grayscale value and ensure it's within the 0-255 range
      val grayscalePixel = (0.3 * red + 0.59 * green + 0.11 * blue).toInt
      val clampedGrayscalePixel = grayscalePixel max 0 min 255

      // Assuming the grayscale image format also includes an alpha channel
      (alpha << 24) | (clampedGrayscalePixel << 16) | (clampedGrayscalePixel << 8) | clampedGrayscalePixel
    }

    new GrayscaleImage(width,height, grayscaleData)
  }
}
