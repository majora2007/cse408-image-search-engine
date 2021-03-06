import magick.*;
import magick.util.MagickViewer;
import java.awt.Frame;

public class ImageUtil {
    private static Frame frame = new Frame("CSE408 - Project 1");
    private static MagickViewer window = null;

    public static MagickImage load_image(String filename){
        System.setProperty ("jmagick.systemclassloader" , "no");
        ImageInfo current_image_info = null;
        MagickImage current_image = null;
        try {
            System.out.println("loading file: "+filename);
            current_image_info = new ImageInfo(filename);
            current_image = new MagickImage(current_image_info);
        } catch (MagickException ex) {
            System.out.println("Error loading file");
            System.err.println(ex.toString());
        } finally {
            if (current_image != null){
                System.out.println("File successfully Loaded");
            } else {
                System.out.println("Null MagickImage");
            }
            return current_image;
        }
    }

    public static void display_image(MagickImage image){
        if (window != null) {
            frame.removeNotify();
            window.setVisible(false);
            frame = new Frame("CSE408 - Project 1");
        }
        window = new MagickViewer();
        try{
            window.setImage(image);
            window.setSize(image.getDimension());
            frame.setSize(image.getDimension());
        } catch (MagickException ex) {
            System.out.println("not displayed");
            System.err.println(ex.toString());
        } finally {
            window.setVisible(true);
            frame.add(window);
            frame.setVisible(true);
        }
    }
    
    public static void save_image(String filename, MagickImage image){
        ImageInfo image_info;
        boolean saved = false;
        try{
            image_info = new ImageInfo(filename);
            image.setFileName(filename);
            saved = image.writeImage(image_info);
        } catch (MagickException ex) {
            saved = false;
        } finally {
            System.out.println("Image Saved Correctly: " + saved);
        }
    }
}
