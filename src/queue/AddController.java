package queue;

import beans.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Date;

public class AddController {


    public TextField clientName;
    public ComboBox<String> clientService;

    @FXML
    public void initialize() {
        ObservableList<String> services = FXCollections.observableArrayList();
        services.add("Haircut");
        services.add("Shaving");
        clientService.setItems(services);
    }

    public void addClient(ActionEvent actionEvent) {
        Client client = new Client();
        System.out.println(clientName.getText());
        client.setName(clientName.getText());
        client.setService(clientService.getValue());
        client.setCreatedAt(new Date());
        Controller.getInstance().insertClientIntoTable(client);
    }
}
