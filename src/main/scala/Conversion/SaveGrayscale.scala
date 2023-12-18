package Conversion

import Image.GrayscaleImage

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object SaveGrayscale {


  def saveGrayscaleImage(image: GrayscaleImage, filename: String): Unit = {
    val width = image.getWidth()
    val height = image.getHeight()
    val grayscaleData = image.getData()

    // Create a BufferedImage
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    // Populate the BufferedImage with your grayscale data
    for (y <- 0 until height; x <- 0 until width) {
      val grayValue = grayscaleData(y * width + x)
      val argb = (0xFF << 24) | (grayValue << 16) | (grayValue << 8) | grayValue
      bufferedImage.setRGB(x, y, argb)
    }

    // Write the BufferedImage to a file
    ImageIO.write(bufferedImage, "png", new File(filename))
  }
}
