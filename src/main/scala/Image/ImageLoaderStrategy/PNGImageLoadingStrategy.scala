package Image.ImageLoaderStrategy

import Image.RGBImage

import java.awt.image.BufferedImage

/**
 * Strategy for loading PNG images from a file path.
 *
 * Extends `ImageIOLoadingStrategy` to specifically handle the loading of PNG images.
 * Utilizes a provided converter to transform the loaded `BufferedImage` into an `RGBImage`.
 *
 * @param path The file path of the PNG image to load.
 * @param converter A function to convert `BufferedImage` to `RGBImage`.
 */
class PNGImageLoadingStrategy(path: String, converter: BufferedImage => RGBImage) extends ImageIOLoadingStrategy(path, converter) {
}
