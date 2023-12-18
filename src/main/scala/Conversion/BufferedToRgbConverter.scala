package Conversion

import Image.RGBImage

import java.awt.image.BufferedImage

/**
 * Utility class for converting a BufferedImage to the custom Image class.
 */
object BufferedToRgbConverter {

  /**
   * Converts a BufferedImage to a custom Image class.
   *
   * This method extracts the pixel data from the BufferedImage and uses it
   * to create an instance of the custom Image class.
   *
   * @param bufferedImage The BufferedImage to convert.
   * @return An instance of the custom Image class.
   */
  def convert(bufferedImage: BufferedImage): RGBImage = {
    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight
    val pixels = new Array[Int](width * height)
    bufferedImage.getRGB(0, 0, width, height, pixels, 0, width)

    new RGBImage(width, height, pixels)
  }
}
