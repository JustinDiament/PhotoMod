package model.image;

import java.util.List;
import model.image.layer.Layer;

public interface ImageLayerModel extends ImageProcessingModel {
  void addLayer(String name);

  void setCurrentLayer(int index);

  Layer getCurrentLayer() throws IllegalArgumentException;

  void setCurrentLayerVisibility(boolean visibility);

  List<String> getLayerNames();

  void removeLayer(int index) throws IllegalArgumentException;

  void setCurrentLayerImage(Image img);

  void exportTopImage(String filename) throws IllegalArgumentException;
}