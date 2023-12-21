package Image.ImageLoaderStrategy

import Image.RGBImage

import java.awt.image.BufferedImage


class JPEGImageLoadingStrategy(path: String, converter: BufferedImage => RGBImage) extends ImageIOLoadingStrategy(path, converter) {
}
