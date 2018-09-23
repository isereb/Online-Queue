package beans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Queue implements Serializable {

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addClients(Client ... clients) { this.clients.addAll(clients); }

    public void markAsCompleted(Client client) {
        clients.remove(client);
    }

    public void update(Client oldClient, Client newClient) {
        clients.set(clients.indexOf(oldClient), newClient);
    }

}
