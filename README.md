# Image Processing

CS3500 Object-Oriented Design. _Northeastern University Summer 1 2021_


| Original | Blur | Sharpen | Sepia | Monochrome |  
|----------|------|---------|-------|------------|  
|<img src="https://user-images.githubusercontent.com/52764831/121444843-3e0bc680-c95e-11eb-9f5b-586b514b4dab.png" width="100" height="75">|<img src="https://user-images.githubusercontent.com/52764831/121445311-3698ed00-c95f-11eb-95cd-4297f268ddb7.png" width="100" height="75">|<img src="https://user-images.githubusercontent.com/52764831/121445393-5d572380-c95f-11eb-9c8d-d4a58edaf307.png" width="100" height="75">|<img src="https://user-images.githubusercontent.com/52764831/121445418-68aa4f00-c95f-11eb-899b-d105eac94d57.png" width="100" height="75">|<img src="https://user-images.githubusercontent.com/52764831/121445440-74961100-c95f-11eb-94d7-6eb8f9168f8d.png" width="100" height="75">|


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
