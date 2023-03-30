import java.io.Serializable;
import java.util.ArrayList;
//e
public class VolumeGroup extends LVM implements Serializable {
    private ArrayList<PhysicalVolume> listPV;
    private ArrayList<LogicalVolume> listLV;
    public VolumeGroup(String n)
    {
        super(n);
        listPV = new ArrayList<PhysicalVolume>();
        listLV = new ArrayList<LogicalVolume>();
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
    public String getPVs()
    {
        String ans = "";
        for (int i = 0;i<listPV.size()-1;i++)
        {
            ans+=listPV.get(i).getName()+",";
        }
        if (listPV.size()>0)
        {
            ans+=listPV.get(listPV.size()-1).getName();
        }
        return ans;
    }
}
