# Image Processing

CS3500 Object-Oriented Design. _Northeastern University Summer 1 2021_


| Original | Blur | Sharpen | Sepia | Monochrome | 
|----------|------|---------|-------|------------| 
|<img src="https://user-images.githubusercontent.com/52764831/121575831-cdb08400-c9f5-11eb-8177-933bc858cfa4.jpg" width="100" height="70">|<img src="https://user-images.githubusercontent.com/52764831/121575993-f8024180-c9f5-11eb-8090-98be3ac64ca8.png" width="100" height="70">|<img src="https://user-images.githubusercontent.com/52764831/121576177-23852c00-c9f6-11eb-8198-4485f348e31e.png" width="100" height="70">|<img src="https://user-images.githubusercontent.com/52764831/121576302-3b5cb000-c9f6-11eb-87d5-f05fc9e94f77.png" width="100" height="70">|<img src="https://user-images.githubusercontent.com/52764831/121576328-43b4eb00-c9f6-11eb-9469-d4ad8760caef.png" width="100" height="70">|
|<img src="https://user-images.githubusercontent.com/52764831/121576880-dfdef200-c9f6-11eb-8c39-ce98cb86c958.jpg" width="80" height="100">|<img src="https://user-images.githubusercontent.com/52764831/121576704-a3ab9180-c9f6-11eb-86b3-6d23cee90119.png" width="80" height="100">|<img src="https://user-images.githubusercontent.com/52764831/121576963-fd13c080-c9f6-11eb-9887-a6d59cad566b.png" width="80" height="100">|<img src="https://user-images.githubusercontent.com/52764831/121577018-0d2ba000-c9f7-11eb-8bde-8f14ed4bbc78.png" width="80" height="100">|<img src="https://user-images.githubusercontent.com/52764831/121577055-1c125280-c9f7-11eb-8350-da05c75aa907.png" width="80" height="100">|

## Design

#### ImageFile: An interface that represents an image file and handles file importing and exporting
- PPM: A class that represents the PPM image file format and imports and exports PPM files

#### ProgrammaticCreator: An interface that supports creating images programmatically
- CreateCheckerboard: A class that creates a checkerboard image with configurable values

#### Image: An interface that represents image data containing pixels and with defined dimensions
- ImageImpl: A class that represents an image as a 2D-List of pixels

#### ImageProcessingModel: An model interface that supports file handling and image processing
- ImageProcessingModelImpl: A class that supports image file handling, applies supported operations,
  and creates programmatic images

#### Pixel: An interface that represents a pixel of an image with red, green, and blue values
- PixelImpl: A class representing a pixel that clamps its RGB values between 0 and 255, inclusive

#### ImageOperation: An interface that processes the given image with a particular operation
- FilterOperation: An abstract class that promotes code reuse between filtering operations and 
  applies the specified kernel to filter an image
    - BlurOperation: A class that specifies the blur kernel to be used for filtering
    - SharpenOperation: A class that specifies the sharpen kernel to be used for filtering
- MonochromeOperation: A class that applies the sepia color transformation on an image
- SepiaOperation: A class that applies the monochrome/greyscale color transformation on an image

##### Operations: An enumeration of the supported image processing operations

##### ImageUtil: A class that contains utility helper methods to be used throughout the project

##### Main: A class that contains a main method to run the application


## Citations

The _res/_ directory contains two example image files, _popeyes.ppm_ and _quetzal.ppm_, along with 
image files containing their blurred, sharpened, sepia, and monochrome versions, named 
respectively and stored in corresponding zip files. All images are owned by Nathan Gong and 
authorized for use in this project. The _res/test/_ subdirectory contains sample files that are used 
for code testing purposes only.
