import java.util.UUID;
public class LVM {
    private String name;
    private UUID ID;
    public LVM(String n)
    {
        name = n;
        ID = UUID.randomUUID();
    }

}
