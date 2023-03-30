import java.io.Serializable;

public class    LogicalVolume extends LVM implements Serializable {
    private VolumeGroup associatedVG;
    private int size;

    public void setAssociatedVG(VolumeGroup associatedVG) {
        this.associatedVG = associatedVG;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public VolumeGroup getAssociatedVG() {
        return associatedVG;
    }

    public int getSize() {
        return size;
    }

    public LogicalVolume(String n, VolumeGroup a, int s)
    {
        super(n);
        associatedVG = a;
        size =s;
    }
}
