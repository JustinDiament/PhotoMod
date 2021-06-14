package model.image.layer;

import model.image.Image;

public interface Layer {

  Image getImage();
  String getName();
  void setVisibility(boolean visibility);
  boolean getVisibility();
}