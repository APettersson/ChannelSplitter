import com.sun.corba.se.spi.ior.Writeable;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		ListView ls = new ListView();



		File images = new File("Images");
		File[] imagesContent = images.listFiles();
		for(File image : imagesContent){
			ls.getItems().add(image.getName());
		}

		VBox vbox = new VBox(ls);
		Scene scene = new Scene(vbox, 450, 450);

		ls.setOnMouseClicked(event -> {
			try {
				String img_name = (String) ls.getSelectionModel().getSelectedItems().get(0);
				File temp = new File("Images/"+img_name);
				Image image = new Image(temp.toURI().toURL().toString());

				ImageView img = new ImageView(image);



				WritableImage[] channels = ChannelSplitter.getChannels(image);
				ImageView red = new ImageView(channels[0]);
				ImageView green = new ImageView(channels[1]);
				ImageView blue = new ImageView(channels[2]);

				Scene img_scene = new Scene(new VBox(new HBox(img, red), new HBox(green,blue)));


				img_scene.setOnMouseClicked(event1 -> {
					System.out.println("Handled!");
					primaryStage.setScene(scene);
					primaryStage.show();
				});


				primaryStage.setScene(img_scene);
				primaryStage.show();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		});


		primaryStage.setTitle("Channels");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args){
		launch(args);
	}
}
