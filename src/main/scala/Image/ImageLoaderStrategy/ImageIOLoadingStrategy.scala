package Image.ImageLoaderStrategy

import Image.Image

import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategy(path : String) extends LoadingImageStrategy {

  override def load(): Image = {
    val img = ImageIO.read(new File(path))
    val width: Int = img.getWidth()
    val height: Int = img.getHeight()

    //Initialize appropriate sized array
    val pixelArray: Array[Int] = Array.ofDim[Int](height * width)

    img.getRGB(0, 0, width, height, pixelArray, 0, width);

    new Image(width, height, pixelArray);
  }
}
