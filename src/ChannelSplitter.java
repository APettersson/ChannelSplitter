import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class ChannelSplitter {

	public static WritableImage[] getChannels(Image image){
		System.out.println("got here!");
		double width = image.getWidth();
		double height = image.getHeight();
		Canvas red = new Canvas(width, height);
		Canvas green = new Canvas(width, height);
		Canvas blue = new Canvas(width, height);



		GraphicsContext redgc = red.getGraphicsContext2D();
		PixelWriter redpw = redgc.getPixelWriter();

		GraphicsContext greengc = green.getGraphicsContext2D();
		PixelWriter greenpw = greengc.getPixelWriter();

		GraphicsContext bluegc = blue.getGraphicsContext2D();
		PixelWriter bluepw = bluegc.getPixelWriter();

		PixelReader pr = image.getPixelReader();

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Color color = pr.getColor(i, j);
				double red_px = color.getRed();
				double green_px = color.getGreen();
				double blue_px = color.getBlue();

				redpw.setColor(i, j, new Color(red_px, 0, 0, 1));
				greenpw.setColor(i, j, new Color(0, green_px, 0, 1));
				bluepw.setColor(i, j, new Color(0, 0, blue_px, 1));
			}
		}

		WritableImage red_channel = red.snapshot(new SnapshotParameters(), null);
		WritableImage green_channel = green.snapshot(new SnapshotParameters(), null);
		WritableImage blue_channel = blue.snapshot(new SnapshotParameters(), null);

		WritableImage[] channels = new WritableImage[]{red_channel, green_channel, blue_channel};
		return channels;
	}


}
