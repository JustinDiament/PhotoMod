package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageUtil;
import model.image.Image;

/**
 * Represents an implementation of a GUI view for an image modification application. This view
 * consists of three main sections for displaying images and organizing image processing operations
 * into interactive components. The view also supports rendering messages to the user via popup
 * messages.
 */
public class ImageViewImpl extends JFrame implements ImageView {

  private final List<Features> featuresListeners;
  private final JPanel mainPanel;
  private final JPanel imagePanel;
  private final JLabel imageLabel;
  private final JScrollPane imageScrollPane;
  private final JPanel buttonPanel;
  private final JComboBox<String> extensionDropdown;
  private final JMenuBar menuBar;
  private final JPanel downscaleMenuPanel;
  private final JTextField downscaleXTextField;
  private final JTextField downscaleYTextField;
  private final JPanel mosaicMenuPanel;
  private final JTextField mosaicSeedTextField;
  private final JPanel createLayerMenuPanel;
  private final JTextField createLayerTextField;
  private final JPanel currentLayerMenuPanel;
  private final JComboBox<String> currentLayerNamesDropdown;
  private final JPanel removeLayerMenuPanel;
  private final JComboBox<String> removeLayerNamesDropdown;
  private final JPanel checkerboardMenuPanel;
  private final JTextField checkerboardSizeTextField;
  private final JTextField checkerboardSquaresTextField;
  private final JComboBox<String> checkerboardColorOneDropdown;
  private final JComboBox<String> checkerboardColorTwoDropdown;
  private String importExtension;
  private String exportExtension;
  private String exportAllExtension;

  /**
   * Constructs a view consisting of a GUI with an image panel that displays images, a button panel
   * that provides operations for processing images, and a popup menu that provides operations for
   * processing images.
   */
  public ImageViewImpl() {
    super();

    this.featuresListeners = new ArrayList<>();

    this.mainPanel = new JPanel();

    this.imagePanel = new JPanel();
    this.imageLabel = new JLabel();
    this.imageScrollPane = new JScrollPane(this.imageLabel);

    this.buttonPanel = new JPanel();
    this.extensionDropdown = new JComboBox<>();

    this.menuBar = new JMenuBar();

    this.downscaleMenuPanel = new JPanel();
    this.downscaleXTextField = new JTextField(5);
    this.downscaleYTextField = new JTextField(5);

    this.mosaicMenuPanel = new JPanel();
    this.mosaicSeedTextField = new JTextField(5);

    this.createLayerMenuPanel = new JPanel();
    this.createLayerTextField = new JTextField(10);

    this.currentLayerMenuPanel = new JPanel();
    this.currentLayerNamesDropdown = new JComboBox<>();

    this.removeLayerMenuPanel = new JPanel();
    this.removeLayerNamesDropdown = new JComboBox<>();

    this.checkerboardMenuPanel = new JPanel();
    this.checkerboardSizeTextField = new JTextField(5);
    this.checkerboardSquaresTextField = new JTextField(5);
    this.checkerboardColorOneDropdown = new JComboBox<>();
    this.checkerboardColorTwoDropdown = new JComboBox<>();

    this.initFrame();
  }

  @Override
  public void addViewEventListener(Features feature) throws IllegalArgumentException {
    this.featuresListeners.add(ImageUtil.requireNonNull(feature));
  }

  @Override
  public void renderImage(Image img) {
    ImageIcon icon = null;
    if (img != null) {
      icon = new ImageIcon(ImageUtil.convertImage(img));
    }

    imageLabel.setIcon(icon);
  }

  @Override
  public String getXScale() {
    return this.downscaleXTextField.getText();
  }

  @Override
  public String getYScale() {
    return this.downscaleYTextField.getText();
  }

  @Override
  public String getSeeds() {
    return this.mosaicSeedTextField.getText();
  }

  @Override
  public String getCheckerboardSize() {
    return this.checkerboardSizeTextField.getText();
  }

  @Override
  public String getNumSquares() {
    return this.checkerboardSquaresTextField.getText();
  }

  @Override
  public String getColorOne() {
    return (String) this.checkerboardColorOneDropdown.getSelectedItem();
  }

  @Override
  public String getColorTwo() {
    return (String) this.checkerboardColorTwoDropdown.getSelectedItem();
  }

  @Override
  public String getNewLayerName() {
    return this.createLayerTextField.getText();
  }

  @Override
  public void addNewLayerToDropdown(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.currentLayerNamesDropdown.addItem(layerName);
    this.removeLayerNamesDropdown.addItem(layerName);
  }

  @Override
  public String getImportFilePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
        "jpg", "jpeg", "png", "ppm", "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showOpenDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return null;
    }
  }

  @Override
  public String getExportFilePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
        "jpg", "jpeg", "png", "ppm", "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showSaveDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return null;
    }
  }

  @Override
  public String getScriptFilePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files",
        "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showOpenDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return null;
    }
  }

  @Override
  public String getFileTypeExtension() {
    String ext = (String) this.extensionDropdown.getSelectedItem();
    if (ext != null) {
      ext = ext.toLowerCase();
      ext = ext.equals("jpeg") ? "jpg" : ext;
    }
    return ext;
  }

  @Override
  public String getSelectedCurrentLayer() {
    String layerName = (String) this.currentLayerNamesDropdown.getSelectedItem();
    return (layerName == null) ? "" : layerName;
  }

  @Override
  public String getSelectedRemoveLayer() {
    String layerName = (String) this.removeLayerNamesDropdown.getSelectedItem();
    return (layerName == null) ? "" : layerName;
  }

  @Override
  public void changeCurrentLayerText(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.buttonPanel.setBorder(
        BorderFactory.createTitledBorder("Currently selected layer: " + layerName));
  }

  @Override
  public void changeCurrentVisibleLayerText(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.imagePanel.setBorder(
        BorderFactory.createTitledBorder("Currently visible layer: " + layerName));
  }

  @Override
  public void removeLayerName(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.currentLayerNamesDropdown.removeItem(layerName);
    this.removeLayerNamesDropdown.removeItem(layerName);
  }

  @Override
  public void renderErrorMessage(String str) throws IllegalArgumentException {
    ImageUtil.requireNonNull(str);
    JOptionPane.showMessageDialog(this, str, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public String getImportExtension() {
    return this.importExtension;
  }

  @Override
  public String getExportExtension() {
    return this.exportExtension;
  }

  @Override
  public String getExportAllExtension() {
    return this.exportAllExtension;
  }

  /**
   * Sets up the main frame of the GUI.
   */
  private void initFrame() {
    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("Image Processor");
    this.setSize(900, 600);
    this.setFocusable(true);
    this.requestFocus();
    this.initMainPanel();
    this.initMenuBar();
    this.pack();
  }

  /**
   * Sets up the main panel, which contains all of the other panels.
   */
  private void initMainPanel() {
    JScrollPane mainScrollPane = new JScrollPane(this.mainPanel);
    this.add(mainScrollPane);
    this.initImagePanel();
    this.initButtonPanel();
  }

  /**
   * Sets up the image panel, which displays the image layers and the name of the currently visible
   * layer and makes the image layers scrollable.
   */
  private void initImagePanel() {
    this.changeCurrentVisibleLayerText("None");
    this.imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.mainPanel.add(this.imagePanel);
    this.imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    this.imageScrollPane.setPreferredSize(new Dimension(500, 500));
    this.imagePanel.add(this.imageScrollPane);
  }

  /**
   * Sets up the menu bar, which displays popup menus that expose all of the features used to
   * process layered images.
   */
  private void initMenuBar() {
    this.setJMenuBar(this.menuBar);
    this.initFileMenu();
    this.initFilterMenu();
    this.initLayerMenu();
    this.initImageMenu();
  }

  /**
   * Sets up the File menu, which allows users to import image files and scripts, export the topmost
   * visible layer, or export all layers, as well as specify the image file type to import or export
   * layers as through menu items.
   */
  private void initFileMenu() {
    JMenu fileMenu = new JMenu("File");
    JMenu importMenuItem = new JMenu("Import");
    JMenuItem importJPEG = new JMenuItem("JPEG");
    importJPEG.addActionListener(e -> {
      this.importExtension = "jpg";
      for (Features feature : featuresListeners) {
        feature.handleImportEvent();
      }
    });
    JMenuItem importPNG = new JMenuItem("PNG");
    importPNG.addActionListener(e -> {
      this.importExtension = "png";
      for (Features feature : featuresListeners) {
        feature.handleImportEvent();
      }
    });
    JMenuItem importPPM = new JMenuItem("PPM");
    importPPM.addActionListener(e -> {
      this.importExtension = "ppm";
      for (Features feature : featuresListeners) {
        feature.handleImportEvent();
      }
    });
    JMenuItem importTXT = new JMenuItem("TXT");
    importTXT.addActionListener(e -> {
      this.importExtension = "txt";
      for (Features feature : featuresListeners) {
        feature.handleImportEvent();
      }
    });
    importMenuItem.add(importJPEG);
    importMenuItem.add(importPNG);
    importMenuItem.add(importPPM);
    importMenuItem.add(importTXT);

    JMenu exportMenuItem = new JMenu("Export");
    JMenuItem exportJPEG = new JMenuItem("JPEG");
    exportJPEG.addActionListener(e -> {
      this.exportExtension = "jpg";
      for (Features feature : featuresListeners) {
        feature.handleExportLayerEvent();
      }
    });
    JMenuItem exportPNG = new JMenuItem("PNG");
    exportPNG.addActionListener(e -> {
      this.exportExtension = "png";
      for (Features feature : featuresListeners) {
        feature.handleExportLayerEvent();
      }
    });
    JMenuItem exportPPM = new JMenuItem("PPM");
    exportPPM.addActionListener(e -> {
      this.exportExtension = "ppm";
      for (Features feature : featuresListeners) {
        feature.handleExportLayerEvent();
      }
    });
    exportMenuItem.add(exportJPEG);
    exportMenuItem.add(exportPNG);
    exportMenuItem.add(exportPPM);

    JMenu exportAllMenuItem = new JMenu("Export All");
    JMenuItem exportAllJPEG = new JMenuItem("JPEG");
    exportAllJPEG.addActionListener(e -> {
      this.exportAllExtension = "jpg";
      for (Features feature : featuresListeners) {
        feature.handleExportAllEvent();
      }
    });
    JMenuItem exportAllPNG = new JMenuItem("PNG");
    exportAllPNG.addActionListener(e -> {
      this.exportAllExtension = "png";
      for (Features feature : featuresListeners) {
        feature.handleExportAllEvent();
      }
    });
    JMenuItem exportAllPPM = new JMenuItem("PPM");
    exportAllPPM.addActionListener(e -> {
      this.exportAllExtension = "ppm";
      for (Features feature : featuresListeners) {
        feature.handleExportAllEvent();
      }
    });
    exportAllMenuItem.add(exportAllJPEG);
    exportAllMenuItem.add(exportAllPNG);
    exportAllMenuItem.add(exportAllPPM);

    JMenuItem executeScriptMenuItem = new JMenuItem("Script");
    executeScriptMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleExecuteScriptEvent();
      }
    });

    fileMenu.add(importMenuItem);
    fileMenu.add(exportMenuItem);
    fileMenu.add(exportAllMenuItem);
    fileMenu.add(executeScriptMenuItem);
    this.menuBar.add(fileMenu);
  }

  /**
   * Sets up the Filter menu, which allows users to apply various image processing filters and color
   * transformations to the image layer(s), as well as specify input fields for certain operations
   * as applicable through menu items.
   */
  private void initFilterMenu() {
    JMenu filterMenu = new JMenu("Filter");
    JMenuItem blurMenuItem = new JMenuItem("Blur");
    blurMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleBlurEvent();
      }
    });
    JMenuItem sharpenMenuItem = new JMenuItem("Sharpen");
    sharpenMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleSharpenEvent();
      }
    });
    JMenuItem sepiaMenuItem = new JMenuItem("Sepia");
    sepiaMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleSepiaEvent();
      }
    });
    JMenuItem monochromeMenuItem = new JMenuItem("Monochrome");
    monochromeMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleMonochromeEvent();
      }
    });
    this.downscaleMenuPanel.add(new JLabel("X Scale (1-100%):"));
    this.downscaleMenuPanel.add(this.downscaleXTextField);
    this.downscaleMenuPanel.add(new JLabel("Y Scale (1-100%):"));
    this.downscaleMenuPanel.add(this.downscaleYTextField);
    JMenuItem downscaleMenuItem = new JMenuItem("Downscale");
    downscaleMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.downscaleMenuPanel, "Downscale",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleDownscaleEvent();
        }
      }
    });
    this.mosaicMenuPanel.add(new JLabel("Seeds:"));
    this.mosaicMenuPanel.add(this.mosaicSeedTextField);
    JMenuItem mosaicMenuItem = new JMenuItem("Mosaic");
    mosaicMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.mosaicMenuPanel, "Mosaic", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleMosaicEvent();
        }
      }
    });

    filterMenu.add(blurMenuItem);
    filterMenu.add(sharpenMenuItem);
    filterMenu.add(sepiaMenuItem);
    filterMenu.add(monochromeMenuItem);
    filterMenu.add(downscaleMenuItem);
    filterMenu.add(mosaicMenuItem);
    this.menuBar.add(filterMenu);
  }

  /**
   * Sets up the Layer menu, which allows users to perform layer operations on the image, such as
   * creating and removing layers, specifying the current layer to manipulate, and changing the
   * visibility of the current layer through menu items.
   */
  private void initLayerMenu() {
    JMenu layerMenu = new JMenu("Layer");
    this.createLayerMenuPanel.add(new JLabel("Name:"));
    this.createLayerMenuPanel.add(this.createLayerTextField);
    JMenuItem createLayerMenuItem = new JMenuItem("Create");
    createLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.createLayerMenuPanel, "Create Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleAddLayerEvent();
        }
      }
    });

    this.currentLayerMenuPanel.add(new JLabel("Layers:"));
    this.currentLayerMenuPanel.add(this.currentLayerNamesDropdown);
    JMenuItem currentLayerMenuItem = new JMenuItem("Current");
    currentLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.currentLayerMenuPanel, "Set Current Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleCurrentLayerEvent();
        }
      }
    });

    this.removeLayerMenuPanel.add(new JLabel("Layers:"));
    this.removeLayerMenuPanel.add(this.removeLayerNamesDropdown);
    JMenuItem removeLayerMenuItem = new JMenuItem("Remove");
    removeLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.removeLayerMenuPanel, "Remove Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleRemoveLayerEvent();
        }
      }
    });

    JMenuItem visibleLayerMenuItem = new JMenuItem("Visible");
    visibleLayerMenuItem.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleVisibilityEvent();
      }
    });

    layerMenu.add(createLayerMenuItem);
    layerMenu.add(currentLayerMenuItem);
    layerMenu.add(removeLayerMenuItem);
    layerMenu.add(visibleLayerMenuItem);
    this.menuBar.add(layerMenu);
  }

  /**
   * Sets up the Image menu, which allows users to create images programmatically and set them as
   * the image of the current layer through menu items.
   */
  private void initImageMenu() {
    JMenu imageMenu = new JMenu("Image");
    String[] colors = {"red", "orange", "yellow", "green", "cyan", "blue", "magenta", "white",
        "gray", "black"};
    for (String color : colors) {
      this.checkerboardColorOneDropdown.addItem(color);
      this.checkerboardColorTwoDropdown.addItem(color);
    }
    this.checkerboardMenuPanel.add(new JLabel("Size:"));
    this.checkerboardMenuPanel.add(this.checkerboardSizeTextField);
    this.checkerboardMenuPanel.add(new JLabel("Number of Squares:"));
    this.checkerboardMenuPanel.add(this.checkerboardSquaresTextField);
    this.checkerboardMenuPanel.add(new JLabel("Color 1:"));
    this.checkerboardMenuPanel.add(this.checkerboardColorOneDropdown);
    this.checkerboardMenuPanel.add(new JLabel("Color 2:"));
    this.checkerboardMenuPanel.add(this.checkerboardColorTwoDropdown);
    JMenuItem checkerboardMenuItem = new JMenuItem("Checkerboard");
    checkerboardMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, checkerboardMenuPanel, "Checkerboard",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleCreateCheckerboardEvent();
        }
      }
    });

    imageMenu.add(checkerboardMenuItem);
    this.menuBar.add(imageMenu);
  }

  /**
   * Sets up the button panel, which allows users to interface with the provided image processing
   * operations through buttons rather than the popup menu.
   */
  private void initButtonPanel() {
    this.changeCurrentLayerText("None");
    this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(this.buttonPanel);
    this.initFilePanel();
    this.initOperationPanel();
    this.initOperationLayerPanel();
    this.initOperationTextPanel();
  }

  /**
   * Sets up the File panel, which allows users to import image files and scripts, export the
   * topmost visible layer, or export all layers, as well as specify the image file type to import
   * or export layers as through buttons.
   */
  private void initFilePanel() {
    JPanel filePanel = new JPanel();
    JPanel fileButtonPanel = new JPanel();
    fileButtonPanel.setLayout(new GridLayout(4, 1));
    filePanel.setBorder(BorderFactory.createTitledBorder("File Handling"));
    JButton importButton = new JButton("Import Layer(s)");
    importButton.addActionListener(e -> {
      this.importExtension = this.getFileTypeExtension();
      for (Features feature : featuresListeners) {
        feature.handleImportEvent();
      }
    });
    JButton exportButton = new JButton("Export Topmost Visible Layer");
    exportButton.addActionListener(e -> {
      this.exportExtension = this.getFileTypeExtension();
      for (Features feature : featuresListeners) {
        feature.handleExportLayerEvent();
      }
    });
    JButton exportAllButton = new JButton("Export All Layers");
    exportAllButton.addActionListener(e -> {
      this.exportExtension = this.getFileTypeExtension();
      for (Features feature : featuresListeners) {
        feature.handleExportLayerEvent();
      }
    });
    JButton executeScriptButton = new JButton("Execute Script");
    executeScriptButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleExecuteScriptEvent();
      }
    });

    extensionDropdown.addItem("JPEG");
    extensionDropdown.addItem("PNG");
    extensionDropdown.addItem("PPM");
    extensionDropdown.addItem("TXT");
    fileButtonPanel.add(importButton);
    fileButtonPanel.add(exportButton);
    fileButtonPanel.add(exportAllButton);
    fileButtonPanel.add(executeScriptButton);

    filePanel.add(fileButtonPanel);
    filePanel.add(this.extensionDropdown);
    buttonPanel.add(filePanel);
  }

  /**
   * Sets up the Operation menu, which allows users to apply various image processing filters and
   * color transformations to the image layer(s), as well as specify input fields for certain
   * operations as applicable through buttons.
   */
  private void initOperationPanel() {
    JPanel operationPanel = new JPanel();
    operationPanel.setLayout(new GridLayout(3, 2));
    operationPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
    JButton blurButton = new JButton("Blur");
    blurButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleBlurEvent();
      }
    });
    JButton monochromeButton = new JButton("Monochrome");
    monochromeButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleMonochromeEvent();
      }
    });
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleSepiaEvent();
      }
    });
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleSharpenEvent();
      }
    });
    JButton downscaleButton = new JButton("Downscale");
    downscaleButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.downscaleMenuPanel, "Downscale",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleDownscaleEvent();
        }
      }
    });
    JButton mosaicButton = new JButton("Mosaic");
    mosaicButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.mosaicMenuPanel, "Mosaic", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleMosaicEvent();
        }
      }
    });

    operationPanel.add(blurButton);
    operationPanel.add(sharpenButton);
    operationPanel.add(sepiaButton);
    operationPanel.add(monochromeButton);
    operationPanel.add(downscaleButton);
    operationPanel.add(mosaicButton);
    this.buttonPanel.add(operationPanel);
  }

  /**
   * Sets up the Operation Layer menu, which allows users to perform layer operations on the image,
   * such as creating and removing layers, specifying the current layer to manipulate, and changing
   * the visibility of the current layer through buttons.
   */
  private void initOperationLayerPanel() {
    JPanel operationLayerPanel = new JPanel();
    operationLayerPanel.setBorder(BorderFactory.createTitledBorder("Layer Operations"));
    operationLayerPanel.setLayout(new GridLayout(4, 1));
    JButton createLayerButton = new JButton("Create Layer");
    createLayerButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.createLayerMenuPanel, "Create Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleAddLayerEvent();
        }
      }
    });
    JButton setCurrentLayerButton = new JButton("Set Current Layer");
    setCurrentLayerButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.currentLayerMenuPanel, "Set Current Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleCurrentLayerEvent();
        }
      }
    });
    JButton removeLayerButton = new JButton("Remove Layer");
    removeLayerButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.removeLayerMenuPanel, "Remove Layer",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleRemoveLayerEvent();
        }
      }
    });
    JButton visibilityButton = new JButton("Toggle Current Layer Visibility");
    visibilityButton.addActionListener(e -> {
      for (Features feature : featuresListeners) {
        feature.handleVisibilityEvent();
      }
    });

    operationLayerPanel.add(createLayerButton);
    operationLayerPanel.add(setCurrentLayerButton);
    operationLayerPanel.add(removeLayerButton);
    operationLayerPanel.add(visibilityButton);
    this.buttonPanel.add(operationLayerPanel);
  }

  /**
   * Sets up the Operation Text menu, which allows users to create images programmatically and set
   * them as the image of the current layer through buttons.
   */
  private void initOperationTextPanel() {
    JPanel operationTextPanel = new JPanel();
    operationTextPanel.setLayout(new BoxLayout(operationTextPanel, BoxLayout.PAGE_AXIS));
    operationTextPanel.setBorder(BorderFactory.createTitledBorder("Programmatic Images"));
    JPanel checkerboardPanel = new JPanel();
    JButton createCheckerboardButton = new JButton("Checkerboard");
    createCheckerboardButton.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, this.checkerboardMenuPanel, "Checkerboard",
              JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : featuresListeners) {
          feature.handleCreateCheckerboardEvent();
        }
      }
    });

    checkerboardPanel.add(createCheckerboardButton);
    operationTextPanel.add(checkerboardPanel);
    this.buttonPanel.add(operationTextPanel);
  }
}
