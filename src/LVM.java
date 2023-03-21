import java.util.UUID;
public class LVM {
    private String name;
    private UUID ID;
    public LVM(String n)
    {
        name = n;
        ID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }
}
