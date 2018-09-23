package queue;

import beans.Client;
import beans.Queue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private static Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }

    private static Stage primaryStage;
    private static Stage addClient;
    static Stage iostage;

    //private static ObservableList<Client> clients = FXCollections.observableArrayList();
    private static Queue queue = new Queue();

    public TableView<Client> clientsTable;
    public TableColumn<Client, String> columnName;
    public TableColumn<Client, String> columnService;
    public TableColumn<Client, String> columnDate;

    public static void setPrimaryStage(Stage primaryStage) {
        Controller.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        instance = this;
        columnName.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );
        columnService.setCellValueFactory(
            new PropertyValueFactory<>("service")
        );
        columnDate.setCellValueFactory(
            new PropertyValueFactory<>("createdAt")
        );
        clientsTable.setItems(queue.getClients());
    }

    public static Queue getQueue() {
        return queue;
    }

    public static void setQueue(Queue queue) {
        Controller.queue = queue;
        instance.clientsTable.setItems(queue.getClients());
    }

    public void addVisitor(ActionEvent actionEvent) throws IOException {
        addClient = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/add.fxml"));
        addClient.setTitle("Haircut queue");
        addClient.setScene(new Scene(root, 600, 200));
        addClient.show();
    }

    void insertClientIntoTable(Client client) {
        queue.getClients().add(client);
        addClient.close();
    }

    public void markCompleted() {
        queue.getClients().remove(clientsTable.getSelectionModel().getFocusedIndex());
    }

    public void saveFile(ActionEvent actionEvent) throws IOException {
        Stage iostage = new Stage();
        FlowPane fp = FXMLLoader.load(getClass().getResource("../fxml/output.fxml"));
        iostage.setTitle("Save file");
        iostage.setScene(new Scene(fp));
        iostage.show();
        Controller.iostage = iostage;
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
        Stage iostage = new Stage();
        FlowPane fp = FXMLLoader.load(getClass().getResource("../fxml/input.fxml"));
        iostage.setTitle("Open file");
        iostage.setScene(new Scene(fp));
        iostage.show();
        Controller.iostage = iostage;

    }

    public void close(ActionEvent actionEvent) {
        primaryStage.close();
    }
}
