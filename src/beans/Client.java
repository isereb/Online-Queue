package beans;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty service = new SimpleStringProperty();
    private SimpleStringProperty createdAt = new SimpleStringProperty();

    public Client() {}

    public Client(String name, String service, Date date) {
        this.name = new SimpleStringProperty(name);
        this.service = new SimpleStringProperty(service);
        this.createdAt = new SimpleStringProperty(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
        );
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getService() {
        return service.get();
    }

    public SimpleStringProperty serviceProperty() {
        return service;
    }

    public void setService(String service) {
        this.service.set(service);
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public SimpleStringProperty createdAtProperty() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdAt));
    }
}
