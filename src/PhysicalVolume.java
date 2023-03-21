public class PhysicalVolume extends LVM{
    private HardDrive associatedHD;
    public PhysicalVolume(String n, HardDrive a)
    {
        super(n);
        associatedHD = a;
    }

    public void setAssociatedHD(HardDrive associatedHD) {
        this.associatedHD = associatedHD;
    }

    public HardDrive getAssociatedHD() {
        return associatedHD;
    }
}
