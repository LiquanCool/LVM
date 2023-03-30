import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        String input = "";
        Command main = new Command();
        System.out.println("Welcome to the LVM system");
        while(input!="exit")
        {
            System.out.print("cmd#: ");
            input = n.nextLine();
            String[] splitInput = input.split(" ");
            //for (int i =0;i<splitInput.length;i++)
            //{
            //    System.out.println(splitInput[i]);
            //}
            if (splitInput[0].equals("install-drive"))
            {
                //System.out.println("working");
                main.installDrive(splitInput[1],Integer.parseInt(splitInput[2].substring(0,splitInput[2].length()-1)));
            }
            else if(splitInput[0].equals("list-drives"))
            {
                main.listDrives();
            }
            else if(splitInput[0].equals("pvcreate"))
            {
                main.createPV(splitInput[1],splitInput[2]);
            }
            else if(splitInput[0].equals("pvlist"))
            {
                main.listPVs();
            }
            else if(splitInput[0].equals("vgcreate"))
            {
                main.createVG(splitInput[1], splitInput[2]);
            }
            else if(splitInput[0].equals("vgextend"))
            {
                main.extendVG(splitInput[1], splitInput[2]);
            }
            else if(splitInput[0].equals("lvcreate"))
            {
                main.createLV(splitInput[1], splitInput[2], splitInput[3]);
            }
            else if(splitInput[0].equals("lvlist"))
            {
                main.listLV();
            }
            else if(splitInput[0].equals("exit"))
            {
                //save
            }

        }
    }
}
