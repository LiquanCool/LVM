public class HardDrive extends LVM{
    private int size;
    public HardDrive(String n, int s)
    {
        super(n);
        size = s;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
