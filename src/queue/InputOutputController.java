package queue;

import beans.Client;
import beans.ClientSerializable;
import beans.Queue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InputOutputController {

    @FXML
    public FlowPane fp;
    public TextField filename;
    public Button saveButton;
    public Button openButton;


    @FXML
    public void save() {
        List nonSerializableClients = Controller.getQueue().getClients();
        ArrayList<ClientSerializable> clients = new ArrayList<>();
        nonSerializableClients.forEach(nsClient -> {
            Client c = (Client) nsClient;
            clients.add(new  ClientSerializable(c));
        });
        try {
            FileOutputStream fileOut =
                new FileOutputStream(filename.getText() + ".que");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(clients);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename.getText() + ".que");
        } catch (IOException i) {
            i.printStackTrace();
        }
        cancel();
    }

    @FXML
    public void cancel() {
        Controller.iostage.close();
    }

    @FXML
    public void open() {
        ArrayList<ClientSerializable> serializableClients = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename.getText() + ".que");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            serializableClients = (ArrayList<ClientSerializable>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("File not found");
            cnf.printStackTrace();
        }

        ArrayList<Client> clients = new ArrayList<>();
        serializableClients.forEach(sClient -> {
            try {
                clients.add(new Client(
                    sClient.getName(),
                    sClient.getService(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sClient.getCreatedAt())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Queue queue = new Queue();
        queue.addClients(clients.toArray(new Client[]{}));
        Controller.setQueue(queue);
        System.out.println("Input successfuly completed");
        cancel();
    }
}
