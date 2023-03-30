import java.io.Serializable;

public class PhysicalVolume extends LVM implements Serializable {
    private HardDrive associatedHD;
    private VolumeGroup associatedVG;
    public PhysicalVolume(String n, HardDrive a)
    {
        super(n);
        associatedHD = a;
        //associatedVG = null;
    }

    public void setAssociatedHD(HardDrive associatedHD) {
        this.associatedHD = associatedHD;
    }

    public HardDrive getAssociatedHD() {
        return associatedHD;
    }

    public void setAssociatedVG(VolumeGroup associatedVG) {
        this.associatedVG = associatedVG;
    }

    public VolumeGroup getAssociatedVG() {
        return associatedVG;
    }
}
