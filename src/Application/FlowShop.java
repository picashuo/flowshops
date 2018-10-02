/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ObjectiveFunctions.ObjectiveForFlowShop;
import ObjectiveFunctions.ObjectiveForFlowShopI;
import Read.ReadFlowShopData;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class FlowShop {

    double[] Weight;
    boolean[][] Tardy;
    int Jobs, Machines;
    double[][] DueDateAns;
    int[] Revenue, DueDate;
    String[] PathDataArrays;
    static String[] Files = new String[]{"p10x5_1.txt"};
    int[][] ProccessingTime, Ciarray;
    int[] Sequence;

    public void initiateVars(String Path) {
        ReadFlowShopData ReadFlowShop = new ReadFlowShopData();
        ObjectiveForFlowShopI ObjectiveForFlowShop = new ObjectiveForFlowShop();

        ReadFlowShop.SetData(Path);
        ReadFlowShop.GetDataFromFile();

        DueDate = ReadFlowShop.GetDueDate();
        Jobs = ReadFlowShop.GetJobs();
        Machines = ReadFlowShop.GetMachines();
        ProccessingTime = ReadFlowShop.GetPtime();
        Revenue = ReadFlowShop.GetRevenue();
        Weight = ReadFlowShop.GetWeight();

        ObjectiveForFlowShop.SetScheduleData(Machines, Jobs, DueDate, ProccessingTime);
        ObjectiveForFlowShop.CalcObjective();
        
        Ciarray = ObjectiveForFlowShop.GetCiarray();
        DueDateAns = ObjectiveForFlowShop.GetDueDateAns();
        Tardy = ObjectiveForFlowShop.GetTardy();
    }

    public void start() {
        System.out.println("==================================");
        //System.out.println("路徑：" + Path);
        System.out.println("Jobs：" + Jobs);
        System.out.println("DueDate：" + Arrays.toString(DueDate));
        System.out.println("Weight：" + Arrays.toString(Weight));
        System.out.println("Revenue：" + Arrays.toString(Revenue));
        System.out.println("==================================");
        for (int i = 0; i < Machines; i++) {
            System.out.println("Machines：" + (i + 1));
            System.out.println("Sequence：" + Arrays.toString(Sequence));
            System.out.println("Ci：" + Arrays.toString(Ciarray[i]));
            System.out.println("DueDate：" + Arrays.toString(DueDateAns[i]));
            System.out.println("Tardy：" + Arrays.toString(Tardy[i]));
            System.out.println("==================================");
        }
    }

    public static void main(String[] args) {
        String Path = "src\\File\\";
        FlowShop FlowShops = new FlowShop();
        for (int i = 0; i < Files.length; i++) {
            FlowShops.initiateVars(Path + Files[i]);
            FlowShops.start();
        }
    }
}
