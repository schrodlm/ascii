package Image.ImageStrategy

import Image.Image

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

class RandomImageStrategy extends ImageStrategy {
  override def createImage(): Image = {

    val imageUrl = "https://source.unsplash.com/random"
    val connection = new URL(imageUrl).openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    val inputStream = connection.getInputStream
    val image = ImageIO.read(inputStream)
    inputStream.close()

    convertToImage(image)
  }

  def convertToImage(image : BufferedImage) : Image = {

    val width: Int = image.getWidth()
    val height: Int = image.getHeight()

    //Initialize appropriate sized array
    val pixelArray: Array[Int] = new Array[Int](height * width)

    image.getRGB(0, 0, width, height, pixelArray, 0, width);

    new Image(width, height, pixelArray);
  }

}
