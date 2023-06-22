package Controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Services {
    public void openPage(ActionEvent event, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void alertWarnning(String title, String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(text);

        alert.showAndWait();
    }

    public void alertSuccess(String text) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(text);

        alert.showAndWait();
    }

    public String uploadImage(ImageView image) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        String currentWorkingDir = System.getProperty("user.dir");
        String subdirectory = "BookCover";
        File initialDirectory = new File(currentWorkingDir, subdirectory);
        fileChooser.setInitialDirectory( initialDirectory );
        File selectedFile = fileChooser.showOpenDialog(image.getScene().getWindow());
        if (selectedFile != null) {
            String absolutePath = selectedFile.getAbsolutePath();
            String relativePath = absolutePath.replace(currentWorkingDir, "");
            relativePath = relativePath.replace(File.separator, "/");
            return relativePath;
        }
        else {
            System.out.println("Select Image cancelled");
            return "/BookCover/default-book.png";
        }
    }

    public String getPathImage(Image image) {
        String pathImage = image.getUrl();
        String relativePath = pathImage.replace(System.getProperty("user.dir"), "");
        relativePath = relativePath.replace(File.separator, "/");
        return relativePath;
    }

    public Image getImageWithPath(String path) {
        String currentWorkingDir = System.getProperty("user.dir");
        path = currentWorkingDir + path;
        Image image = new Image(path);
        return image;
    }
}
