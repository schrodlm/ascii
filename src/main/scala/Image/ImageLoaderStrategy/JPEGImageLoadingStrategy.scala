package Image.ImageLoaderStrategy

import Image.RGBImage

import java.awt.image.BufferedImage


/**
 * Strategy for loading JPEG images from a file path.
 *
 * Inherits from `ImageIOLoadingStrategy` to specifically handle the loading of JPEG images.
 * Uses a provided converter to transform the loaded `BufferedImage` into an `RGBImage`.
 *
 * @param path The file path of the JPEG image to load.
 * @param converter A function to convert `BufferedImage` to `RGBImage`.
 */
class JPEGImageLoadingStrategy(path: String, converter: BufferedImage => RGBImage) extends ImageIOLoadingStrategy(path, converter) {
}
