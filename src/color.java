/**
 * Counts the different Colors of a picture.
 * Created by Thales on 2016-02-13.
 */
        import java.io.File;
        import java.nio.file.*;
        import java.util.*;
        import java.awt.image.BufferedImage;
        import javax.imageio.ImageIO;
        import java.io.IOException;
        import javax.swing.ImageIcon;
        import javax.swing.JFileChooser;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.SwingUtilities;

public class color {
    static JFrameWin jFrameWindow;

    static public BufferedImage image = null;
    static public String workingPath = "/image/image1.jpg";
    public static void main(String[] args) throws IOException {
        path();
        System.out.println(ls());
        String exportPath = getPath();
        Set<Integer> colors = new HashSet<>();
        //copyFile(exportPath, workingPath);

        BufferedImage image = getImage();

        int w = image.getWidth();
        int h = image.getHeight();
        int pixels = getPixels(w,h);

        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                colors.add(pixel);
            }
        }
        JOptionPane.showMessageDialog(null, "In this: "+ w + " by " + h + " image. \nPixels: " + pixels);
        SwingUtilities.invokeLater(runJFrameLater);
        JOptionPane.showMessageDialog(null, evaluate(colors.size()));
    }
    /*
    *  Evaluates the amount of colors
    * */
    static String evaluate(int colors)
    {
        if (colors < 16000000) {return "There are "+ colors +" colors, that's not enough";}
        else {return "There are "+ colors +" colors, that's a perfect amount";}
    }
    public static class JFrameWin extends JFrame{

        public JFrameWin(){

            String exportPath = getPath();
            image = getImage();
            this.setSize(image.getWidth(), image.getHeight());
            JLabel jLabel = new JLabel(new ImageIcon(image));

            JPanel jPanel = new JPanel();
            jPanel.add(jLabel);
            this.add(jPanel);
        }
    }
    /*
    *
    **/
    static BufferedImage getImage()
    {
        try {
           // System.out.println(exportPath);
            return image = ImageIO.read(color.class.getResource(workingPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

    /*
    *  copyFile gets exportPath from the getPath() method and copies that file in to the working java directory directory
    * */
    static void copyFile(String exportPath, String workingPath) throws IOException {
        /*
        * Path from = cfgFilePath.toPath(); //convert from File to Path
        * Path to = Paths.get(strTarget); //convert from String to Path
        *
        * */
        Path FROM = Paths.get(exportPath);
        Path TO = Paths.get(workingPath);
      //  overwrite existing file, if exists

        Files.copy(FROM, TO, StandardCopyOption.REPLACE_EXISTING);
    }

    /*
    *  getPath Creates a JFileChooser that finds a file and returns the path directory chosen.
    * */
    static String getPath()
    {
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose the file");
        fc.setAcceptAllFileFilterUsed(true);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println(fc.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }

        String exportPath = fc.getSelectedFile().getAbsolutePath();
        //exportPath = exportPath.replace('\\', '/');

        return exportPath;
    }
    /*
    * Get the path File and return a string
    *
    * */
    static String path()
    {
        File f = new File(color.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String of = f.toString() ;
        return of;
    }

    /*
    *  ls gets the current directory
    * */
    static String ls()
    {
        return "Current directory: " + System.getProperty("user.dir");
    }

    /*
    *  cd changes the current directory
    * */
    static void cd()
    {
        System.setProperty("user.dir", "");
    }

    /*
    *  getPixels gets the amount of pixels in an Image
    * */
    static int getPixels(int w, int h)
    {
        return w * h;
    }

    /*
    *  JFrame window is created
    * */
    static Runnable runJFrameLater = new Runnable() {

        @Override
        public void run() {
            jFrameWindow = new JFrameWin();
            jFrameWindow.setVisible(true);
        }
    };
}
