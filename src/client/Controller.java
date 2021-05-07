package client;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Controller {

    class Cat{
        public String Name;
        public int Age;

        public Cat(String name, int age) {
            Name = name;
            Age = age;
        }
    }


    private void ShowDialog(String message) {
        new Alert(Alert.AlertType.CONFIRMATION, message).showAndWait();
    }

    private HttpURLConnection httpConnection = null;
    private URL url = null;
    private Scanner scanner = null;

    public void buttonGetServerName_Click(MouseEvent mouseEvent) throws IOException {
        url = new URL("http://127.0.0.1:34562/api/name");
        httpConnection = (HttpURLConnection) url.openConnection();

        InputStream responseStream = httpConnection.getInputStream();
        scanner = new Scanner(responseStream).useDelimiter("\\A");

        String response = scanner.next();

        Cat cat = new Gson().fromJson(response, Cat.class);

        //ShowDialog(response);
        ShowDialog(cat.Name+"   "+cat.Age);
    }
}
