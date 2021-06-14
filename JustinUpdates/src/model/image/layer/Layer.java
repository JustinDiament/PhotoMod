package model.image.layer;

import model.image.Image;

// todo: docstrings everywhere
public interface Layer {

  Image getImage();
  String getName();
  void setVisibility(boolean visibility);
  boolean getVisibility();
}