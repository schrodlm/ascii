package Image.ImageLoaderStrategy

import Image.RGBImage

trait LoadingImageStrategy {
  def load(): RGBImage
}
