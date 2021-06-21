package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.ImageUtil;
import model.image.Image;
import model.image.file.PNG;

// todo: make this implement interface
public class ImageViewImpl extends JFrame {

  private final JPanel mainPanel;
  private final JScrollPane mainScrollPane;
  private final JButton blurButton;
  private final JButton monochromeButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;

  // need associated text box(es)
  private final JButton downscaleButton;
  private final JTextField downscaleXTextField;
  private final JTextField downscaleYTextField;

  private final JButton mosaicButton;
  private final JTextField mosaicSeedTextField;

  private final JButton createCheckerboardButton;
  private final JTextField checkerboardSizeTextField;
  private final JTextField checkerboardSquaresTextField;
  private final JComboBox<String> checkerboardColorOneDropdown;
  private final JComboBox<String> checkerboardColorTwoDropdown;

  // file explorer stuff
  private final JButton importButton;
  private final JButton exportButton;
  private final JButton exportAllButton;

  private final JButton createLayerButton;
  private final JTextField createLayerTextField;

  // these two buttons use the same dropdown
  private final JButton setCurrentLayerButton;
  private final JButton removeLayerButton;
  private final JComboBox<String> layerNamesDropdown;

  private final JCheckBox visibilityCheckBox; // check to toggle invisible








  public ImageViewImpl() {
    super();
    setTitle("Swing features");
    setSize(500, 500);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new GridLayout(1, 2));
//    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Currently viewing layer:"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    JLabel label = new JLabel();
    JScrollPane scrollPane = new JScrollPane(label);
    PNG png = new PNG();
    Image i = png.importFile("res//mosaic//popeyes_original.png");
    label.setIcon(new ImageIcon(ImageUtil.convertImage(i)));
    scrollPane.setPreferredSize(new Dimension(100, 200));
    imagePanel.add(scrollPane);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

    this.mainPanel.add(buttonPanel);

    JPanel operationPanel = new JPanel();
    this.blurButton = new JButton("Blur");
    this.monochromeButton = new JButton("Monochrome");
    this.monochromeButton.setPreferredSize(new Dimension(150, 20));
//    this.monochromeButton.setMargin(new Insets(1, 0, 1, 0));
    this.sepiaButton = new JButton("Sepia");
    this.sharpenButton = new JButton("Sharpen");
    operationPanel.add(this.blurButton);
    operationPanel.add(this.monochromeButton);
    operationPanel.add(this.sepiaButton);
    operationPanel.add(this.sharpenButton);

    buttonPanel.add(operationPanel);


    JPanel operationTextPanel = new JPanel();
    JPanel downscalePanel = new JPanel();
    this.downscaleButton = new JButton("Downscale");
    this.downscaleXTextField = new JTextField(30);
    this.downscaleYTextField = new JTextField(30);

    this.mosaicButton = new JButton("Mosaic");
    this.mosaicSeedTextField = new JTextField(30);

    this.createCheckerboardButton = new JButton("Checkerboard");
    this.checkerboardSizeTextField = new JTextField(30);
    this.checkerboardSquaresTextField = new JTextField(30);
    this.checkerboardColorOneDropdown = new JComboBox<>();
    this.checkerboardColorTwoDropdown = new JComboBox<>();



    this.importButton = new JButton("Import");
    this.exportButton = new JButton("Export Topmost Visible Layer");
    this.exportAllButton = new JButton("Export All Layers");

    this.createLayerButton = new JButton("Create Layer");
    this.createLayerTextField = new JTextField(30);

    this.setCurrentLayerButton = new JButton("Set Current Layer");
    this.removeLayerButton = new JButton("Remove Layer");
    this.layerNamesDropdown = new JComboBox<>();

    this.visibilityCheckBox = new JCheckBox();


  }

  public static void main(String[] args) {
    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    ImageViewImpl frame = new ImageViewImpl();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

