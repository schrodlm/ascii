package Image.ImageLoaderStrategy

import Image.RGBImage

trait ImageLoadingStrategy {
  def load(): RGBImage
}
