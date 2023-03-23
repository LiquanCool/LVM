import java.util.ArrayList;

public class Command {
    private ArrayList<HardDrive> driveList = new ArrayList<HardDrive>();
    private ArrayList<PhysicalVolume> PVList = new ArrayList<PhysicalVolume>();
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
    public void createPV(String PVname, String driveName)
    {
        boolean installed = false;
        for (int i =0;i<PVList.size();i++)
        {
            if (PVList.get(i).getName().equals(PVname))
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
            PhysicalVolume p = new PhysicalVolume(PVname,theDrive);
            PVList.add(p);
            System.out.println(PVname + " created");
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
}
