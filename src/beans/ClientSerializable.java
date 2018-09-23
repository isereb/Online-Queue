package beans;

import java.io.Serializable;

public class ClientSerializable implements Serializable {

    private String name;
    private String service;
    private String createdAt;

    public ClientSerializable() {}

    public ClientSerializable(Client client) {
        parseClient(client);
    }

    public ClientSerializable(String name, String service, String createdAt) {
        this.name = name;
        this.service = service;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void parseClient(Client client) {
        setName(client.getName());
        setService(client.getService());
        setCreatedAt(client.getCreatedAt());
    }
}
