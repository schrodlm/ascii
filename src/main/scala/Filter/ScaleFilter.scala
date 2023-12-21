package Filter

import Image.AsciiArt

class ScaleFilter(val scale: Double) extends AsciiArtFilter {
  override def apply(image: AsciiArt): AsciiArt = {
    if (scale <= 0) throw new IllegalArgumentException("Scale must be positive")

    if (scale >= 1) {
      scaleDown(image)
    }

    else {
      scaleUp(image)
    }
  }

  private def scaleDown(image: AsciiArt): AsciiArt = {

    val newWidth = (image.getWidth() / scale).toInt
    val newHeight = (image.getHeight() / scale).toInt

    val newData = (0 until newHeight).flatMap { y =>
      (0 until newWidth).map { x =>
        val originalX = x * scale.toInt
        val originalY = y * scale.toInt
        image.getData()(originalY * image.getWidth() + originalX)
      }
    }.toArray

    new AsciiArt(newWidth, newHeight, newData)
  }

  private def scaleUp(image: AsciiArt): AsciiArt = {
    val pixelRatio = (1 / scale).toInt
    val newWidth = (image.getWidth() / scale).toInt
    val newHeight = (image.getHeight() / scale).toInt
    val newData = Array.ofDim[Int](newWidth * newHeight)

    (0 until image.getHeight()).foreach { y =>
      (0 until image.getWidth()).foreach { x =>
        (0 until pixelRatio).foreach { pixelCount =>
          newData((y * image.getWidth() + x) * pixelRatio + pixelCount) = image.getData()(y * image.getWidth() + x)
        }
      }
    }

    new AsciiArt(newWidth, newHeight, newData)
  }
}
