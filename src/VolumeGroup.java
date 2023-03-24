import java.util.ArrayList;

public class VolumeGroup extends LVM {
    private ArrayList<PhysicalVolume> listPV;
    private ArrayList<LogicalVolume> listLV;
    public VolumeGroup(String n)
    {
        super(n);
    }
    public int getSize()
    {
        int total = 0;
        for (int i = 0;i<listPV.size();i++)
        {
            total += listPV.get(i).getAssociatedHD().getSize();
        }
        return total;
    }
    public int getFreeSpace()
    {
        int free = getSize();
        for (int i = 0;i<listLV.size();i++)
        {
            free -= listLV.get(i).getSize();
        }
        return free;
    }
    public void addPV(PhysicalVolume pv)
    {
        listPV.add(pv);
    }
    public void addLV(LogicalVolume lv)
    {
        listLV.add(lv);
    }
}
