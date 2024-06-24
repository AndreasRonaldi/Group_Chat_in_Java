package Client;

public class Group {
    public String name;
    public String ownerUsername;
    public Integer id;

    public Group(String name, String oName, Integer id) {
        this.name = name;
        this.ownerUsername = oName;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " (" + ownerUsername + ")";
    }

    public Integer getId() {
        return id;
    }
}
