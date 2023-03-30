import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Command implements Serializable {
    private ArrayList<HardDrive> driveList = new ArrayList<HardDrive>();
    private ArrayList<PhysicalVolume> PVList = new ArrayList<PhysicalVolume>();
    private ArrayList<VolumeGroup> VGList = new ArrayList<VolumeGroup>();
    private ArrayList<LogicalVolume> LVList = new ArrayList<LogicalVolume>();
    public Command()
    {
    }
    public void installDrive(String name, int size)
    {
        boolean installed = false;
        for (int i =0;i<driveList.size();i++)
        {
            if (driveList.get(i).getName().equals(name))
            {
                installed = true;
            }
        }
        if (installed)
        {
            System.out.println("Error, drive already installed");
        }
        else
        {
            HardDrive h = new HardDrive(name,size);
            driveList.add(h);
            System.out.println("Drive "+ name + " installed");
        }
    }
    public void listDrives()
    {
        for (int i =0;i<driveList.size();i++)
        {
            HardDrive current = driveList.get(i);
            System.out.println(current.getName()+" ["+current.getSize()+"G]["+current.getID()+"]");
        }
    }
    public void createPV(String PVName, String driveName)
    {
        boolean installed = false;
        for (int i =0;i<PVList.size();i++)
        {
            if (PVList.get(i).getName().equals(PVName))
            {
                installed = true;
                System.out.println("Error, PV already installed");
            }
            if(PVList.get(i).getAssociatedHD().getName().equals(driveName))
            {
                installed = true;
                System.out.println("Error, drive already associated");
            }
        }
        boolean notExists = true;
        HardDrive theDrive = null;
        for (int i =0;i<driveList.size();i++)
        {
            if (driveList.get(i).getName().equals(driveName))
            {
                notExists = false;
                theDrive = driveList.get(i);
            }
        }
        if (notExists)
        {
            System.out.println("Error, drive does not exist");
        }
        else if(!installed)
        {
            PhysicalVolume p = new PhysicalVolume(PVName,theDrive);
            PVList.add(p);
            System.out.println(PVName + " created");
        }
    }
    public void listPVs()
    {
        for (int i =0;i<PVList.size();i++)
        {
            PhysicalVolume current = PVList.get(i);
            System.out.println(current.getName()+": ["+current.getAssociatedHD().getSize()+"G] ["+current.getID()+"]");
        }
    }
    public void createVG(String VGName, String PVName)
    {
        PhysicalVolume pv = null;
        boolean acceptable = false;
        for(int i = 0;i<PVList.size();i++)
        {
            if (PVList.get(i).getName().equals(PVName))
            {
                if (PVList.get(i).getAssociatedVG()==null)
                {
                    acceptable = true;
                    pv = PVList.get(i);
                }
            }
        }
        boolean repeat = false;
        for (int i = 0;i<VGList.size();i++)
        {
            if (VGList.get(i).getName().equals(VGName))
            {
                repeat = true;
                System.out.println("Error, this Volume Group already exists");
            }
        }
        if (!acceptable)
        {
            System.out.println("Error, this Physical Volume does not exist or is already associated with a Volume Group.");
        }
        if (acceptable&&!repeat)
        {
            VolumeGroup v = new VolumeGroup(VGName);
            pv.setAssociatedVG(v);
            v.addPV(pv);
            VGList.add(v);
            System.out.println(VGName+" created");
        }
    }
    public void extendVG(String VGName, String PVName)
    {
        VolumeGroup v = null;
        PhysicalVolume p = null;
        VolumeGroup associatedVG = null;
        boolean PVExists = false;
        boolean VGExists = false;
        for(int i = 0;i<PVList.size();i++)
        {
            if (PVList.get(i).getName().equals(PVName))
            {
                p = PVList.get(i);
                associatedVG = p.getAssociatedVG();
                PVExists = true;
            }
        }
        for(int i = 0;i<VGList.size();i++)
        {
            if (VGList.get(i).getName().equals(VGName))
            {
                v = VGList.get(i);
                VGExists = true;
            }
        }
        if (associatedVG==null)
        {
            if(!PVExists)
            {
                System.out.println("Error, Physical Volume does not exist.");
            }
            else if(!VGExists)
            {
                System.out.println("Error, Volume Group does not exists.");
            }
            else
            {
                System.out.println("Volume Group " + VGName+ " extended");
                v.addPV(p);
            }
        }
        else
        {
            System.out.println("Error, Physical Volume already associated with a Volume Group.");
        }
    }
    public void listVGs()
    {
        for (int i = 0;i<VGList.size();i++)
        {
            VolumeGroup current = VGList.get(i);
            System.out.println(current.getName()+": total:["+current.getSize()+"G] available:["+current.getFreeSpace()+"G] ["+current.getPVs()+"] ["+current.getID()+"]");
        }
    }
    public void createLV(String LVName, String size, String VGName)
    {
        int intSize = Integer.parseInt(size.substring(0,size.length()-1));
        VolumeGroup v = null;
        VolumeGroup associatedVG = null;
        boolean VGExists = false;
        boolean LVExists = false;
        for(int i = 0;i<VGList.size();i++)
        {
            if (VGList.get(i).getName().equals(VGName))
            {
                v = VGList.get(i);
                VGExists = true;
            }
        }
        for(int i = 0;i<LVList.size();i++)
        {
            if (LVList.get(i).getName().equals(LVName))
            {
                LVExists = true;
            }
        }
        if (!VGExists)
        {
            System.out.println("The Volume Group does not exist!");
        }
        else if (LVExists)
        {
            System.out.println("This Logical Volume already exists!");
        }
        else if (intSize>v.getFreeSpace())
        {
            System.out.println("The Volume Group does not have enough free space!");
        }
        else
        {
            LogicalVolume lv = new LogicalVolume(LVName, v, intSize);
            v.addLV(lv);
            LVList.add(lv);
            System.out.println(LVName + " created");
        }
    }
    public void listLV()
    {
        for (int i = 0;i<LVList.size();i++)
        {
            LogicalVolume current = LVList.get(i);
            System.out.println(current.getName()+": ["+current.getSize()+"G] ["+current.getAssociatedVG().getName()+"] ["+current.getID()+"]");
        }
    }
    public static void save(Command com)throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data.dat"));
        oos.writeObject(com);
        oos.close();
        System.out.println("Saved Data.");
    }

    public static Command useData(Command com) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("Data.dat"));
        com = (Command) input.readObject();
        input.close();
        System.out.println("Data loaded.");
        return com;
    }
}
