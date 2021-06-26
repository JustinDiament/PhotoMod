# Image Processing

CS3500 Object-Oriented Design. _Northeastern University Summer 1 2021_

## Running the Application

The _res/_ directory will be submitted as a zip file _res.zip_ within the root _/_ directory. In
order for the JAR file or any of the test classes within the _test/_ directory to run
successfully, **THE CONTENTS OF _res.zip_ MUST BE EXTRACTED AND PLACED IN A _res/_ DIRECTORY LOCATED
IN THE ROOT _/_ DIRECTORY.** The _res/_ directory was compressed to a zip file in order to
accommodate the size restrictions of the Handins server.

A JAR file, ImageProcessing.jar, can be found in the _res/_ directory. A user may run this file by
navigating to this directory within a command terminal and entering one of three valid commands:

1. `java -jar ImageProcessing.jar -script path-of-script-file`  
   The program will execute the script file at the provided _path-of-script-file_
2. `java -jar ImageProcessing.jar -text`  
   The program will allow the user to type and execute batch commands through the command terminal
3. `java -jar ImageProcessing.jar -interactive`  
   The program will open the GUI

In order to run the example script file, _res/Script.txt_, the JAR file must be moved from the _
res/_
directory to the root _/_ directory. **ATTEMPTING TO RUN THE EXAMPLE SCRIPT FILE WILL FAIL IF THE
JAR FILE IS NOT IN THE ROOT DIRECTORY.**

## Using the GUI

<img src="https://user-images.githubusercontent.com/52764831/123495605-6cda9b80-d5f2-11eb-8598-e55d0da365ea.jpg" width="400" alt="Screenshot of the program with an image loaded">

The GUI contains a panel for displaying the image, a menu bar containing all the image processing
operations, and a panel containing buttons for quicker access to the operations. The image panel
contains a border title displaying the currently visible layer, and displays the topmost visible
layer to the user, or a blank panel of there are no visible layers. The button panel contains a
border title displaying the currently selected layer that operations will be applied to. Referencing
both border titles in conjunction can provide insight into whether the layer being displayed to the
user is also the selected layer to be operated on.

- Loading an image
    - Loading a single image
        - Select the menu item File > Import > JPEG, PNG, or PPM, depending on the file type of the
          image being imported, then select the desired file from the file explorer
        - Click the Import Layer(s) button with the JPEG, PNG, or PPM extension selected within the
          dropdown, depending on the file type of the image being imported, then select the desired
          file from the file explorer
    - Loading a multi-layered image
        - Select the menu item File > Import > TXT, then select the desired file from the file
          explorer
        - Click the Import Layer(s) button with the TXT extension selected within the dropdown, then
          select the desired file from the file explorer
- Saving the topmost visible layer
    - Select the menu item File > Export > JPEG, PNG, or PPM, depending on the file type of the
      image to be exported, then navigate to the desired directory and enter the new file name
    - Click the Export Topmost Visible Layer button with the JPEG, PNG, or PPM extension selected
      within the dropdown, depending on the file type of the image being exported, then navigate to
      the desired directory and enter the new file name
- Saving all layers
    - Select the menu item File > Export All > JPEG, PNG, or PPM, depending on the file types of the
      images to be exported, then navigate to the desired directory and enter the new file name
      without any extension
    - Click the Export All Layers button with the JPEG, PNG, or PPM extension selected within the
      dropdown, depending on the file types of the images being exported, then navigate to the
      desired directory and enter the new file name without any extension
- Executing a script
    - Select the menu item File > Script, then select the desired file from the file explorer
    - Click the Execute Script button, then select the desired file from the file explorer
- Applying filters and color transformations
    - Blur
        - Select the menu item Filter > Blur to blur the currently selected layer
        - Click the Blur button to blur the currently selected layer
    - Sharpen
        - Select the menu item Filter > Sharpen to sharpen the currently selected layer
        - Click the Sharpen button to sharpen the currently selected layer
    - Sepia
        - Select the menu item Filter > Sepia to apply the sepia transformation to the currently
          selected layer
        - Click the Sepia button to apply the sepia transformation to the currently selected layer
    - Monochrome
        - Select the menu item Filter > Monochrome to apply the monochrome transformation to the
          currently selected layer
        - Click the Monochrome button to apply the monochrome transformation to the currently
          selected layer
    - Downscale
        - Select the menu item Filter > Downscale, then enter the desired x and y scales as
          percentages between 1-100 to downscale all layers
        - Click the Downscale button, then enter the desired x and y scales as percentages between
          1-100 to downscale all layers
    - Mosaic
        - Select the menu item Filter > Mosaic, then enter the desired number of seeds to mosaic the
          currently selected layer
        - Click the Mosaic button, then enter the desired number of seeds to mosaic the currently
          selected layer
- Creating a new layer
    - Select the menu item Layer > Create, then enter the name of the new layer to add
    - Click the Create Layer button, then enter the name of the new layer to add
- Setting a layer as the current layer
    - Select the menu item Layer > Current, then select the name of the layer to set as the current
      layer from the dropdown
    - Click the Set Current Layer button, then select the name of the layer to set as the current
      layer from the dropdown
- Removing a layer
    - Select the menu item Layer > Remove, then select the name of the layer to remove from the
      dropdown
    - Click the Remove Layer button, then select the name of the layer to remove from the dropdown
- Changing the visibility of the current layer
    - Select the menu item Layer > Visible to change the visibility of the current layer from
      visible to invisible or vice versa
    - Click the Toggle Current Layer visibility button to change the visibility of the current layer
      from visible to invisible or vice versa
- Create a checkerboard image programmatically
    - Select the menu item Image > Checkerboard, then enter the number of squares and checkerboard
      size and select the two colors to use from the respective dropdowns to set the current layer
      image as the configured checkerboard
    - Click the Checkerboard button, then enter the number of squares and checkerboard size and
      select the two colors to use from the respective dropdowns to set the current layer image as
      the configured checkerboard
        - Note that if a layer already exists within the image, a checkerboard can only be added to
          a new layer if it matches the dimensions of the existing layers. For examples of square
          images, reference _res/test/quetzal.jpg_, _res/test/quetzal.png_, and _
          res/test/quetzal.ppm_. This applies to creating checkerboards through the menu as well

## Script Commands

The following batch script commands are supported by this image processing application.

###### Command Template:

**command name** _[additional arguments]_: description  
_Example_  
Conditions

1. **blur**: blurs the current layer of the image  
   _Example: blur_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
2. **sharpen**: sharpens the current layer of the image  
   _Example: sharpen_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
3. **sepia**: applies the sepia effect on the current layer of the image  
   _Example: sepia_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
4. **monochrome**: applies the monochrome effect on the current layer of the image  
   _Example: monochrome_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
5. **downscale** _[x_scale y_scale]_: downscales the current layer of the image by the given x and y
   scaling factors
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
    3. _x_scale_ and _y_scale_ must be integers between 1 and 100, inclusive
6. **mosaic** _[seeds]_: mosaics the current layer of the image using the given number of seeds
   placed randomly within the image
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
    3. _seeds_ must be a natural number
5. **current** _[layer_name]_: changes the current layer to the layer with the given name  
   _Example: current layerOne_
    1. A layer must already be created that has the given name
6. **createlayer** _[layer_name]_: adds a new layer to the top of the layers with the given name  
   _Example: createlayer layerOne_
    1. A layer must not already exist with the same name
    2. Newly created layers have no image and are visible by default
7. **removelayer** _[layer_name]_: removes the layer with the given name from the image  
   _Example: removelayer layerOne_
    1. A layer must already be created that has the given name
    2. After a layer is removed successfully, no layer will be selected, and the current layer will
       need to be set
8. **visibility** _[visibility]_: sets the visibility of the current layer  
   _Example: visibility invisible_
    1. A layer must already be created and selected as the current layer
    2. _visibility_ must be either "visible" or "invisible"
9. **createcheckerboard** _[size num_squares color_1 color_2]_: sets the image of the current layer
   to a programmatically generated checkerboard  
   _Example: createcheckerboard 16 4 black white_
    1. _size_ must be a positive integer
    2. _num_squares_ must be a positive integer
    3. _color_1_ must be a valid color supported by Java's Color library, as a lowercase single word
    4. _color_2_ must be a valid color supported by Java's Color library, as a lowercase single word
    5. A layer must already be created and selected as the current layer
10. **import** _[filepath extension]_: imports the image file at the given file path to the current
    layer  
    _Example: import res//test//layer//test.jpg jpg_  
    _Example: import res//test//layer//test.txt txt_
    1. _filepath_ must be a valid absolute or relative file path
    2. _filepath_ must point to a file of a supported image file type or text file
        1. If _filepath_ points to a text file, the text file must be in the correct format for a
           multi-layered image of _[image_file_path image_file_extension]_ and all the file paths
           and extensions must be valid. Importing a multi-layered image will automatically create
           new layers at the top of the image in the order listed in the text file and set the
           current layer to the topmost layer
        2. If _filepath_ points to a supported image file, a layer must already be created and
           selected as the current layer
    3. _extension_ must be a supported image file type, as a lowercase single word
11. **exportall** _[filepath extension]_: exports all layers of a multi-layer image to a new
    directory containing all layers as image files of the given file type, and a text file in the
    correct format for a multi-layered image of _[image_file_path image_file_extension]_ for each
    layer in the image  
    _Example: exportall res//test//layer//test png_
    1. _filepath_ should not be given an extension, as it represents the name of the directory to
       which the multi-layered image will be saved.
    2. _extension_ must be a supported image file type, as a lowercase single word
12. **export** _[filepath extension]_: exports the topmost visible layer to an image file with the
    given filepath and extension  
    _Example: export res//test//layer//test.ppm ppm_
    1. _filepath_ should point to a supported image file
    2. _extension_ must be a supported image file type, as a lowercase single word
    3. A visible layer must exist within the image
13. **quit**: immediately stops running the application when entered  
    _Example: quit_
    