package Image

import java.io.File
import javax.imageio.ImageIO

class ImageLoader() {

  def load(path: String): Unit = {


    val fileFormat = path.split("\\.").last


    fileFormat match {
      case "jpg" => {
        val img = ImageIO.read(new File(path))
        printf("Photo size is %d x %d\n", img.getWidth, img.getHeight);
        val width: Int = img.getWidth();
        val height: Int = img.getHeight();

        //Initialize appropriate sized array
        val pixelArray: Array[Int] = Array.ofDim[Int](height * width)

        img.getRGB(0, 0, width, height, pixelArray, 0, width);

        new JPGImage(width, height, pixelArray);

      }
      case "png" => println("JPG File format");
      case _ => println("Unsupported file format");
    }
  }
}
