/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectiveFunctions;

import java.util.Random;

/**
 *
 * @author User
 */
public class ObjectiveForFlowShop implements  ObjectiveForFlowShopI {

    int Machines;
    int[] Sequence, DueDate;
    int[][] ProccessingTime, Ciarray;
    double[][] DueDateAns;
    boolean[][] Tardy;

    public void SetScheduleData(int Machines, int Jobs, int[] DueDate, int[][] ProccessingTime) {
        this.Machines = Machines;
        this.ProccessingTime = ProccessingTime;
        this.DueDate =DueDate;
        Ciarray = new int[Machines][Jobs];
        Tardy = new boolean[Machines][Jobs];
        DueDateAns = new double[Machines][Jobs];
        Sequence = SetCustomRandom(Jobs);
        CalcObjective();
    }

    public void CalcObjective() {
        for (int i = 0; i < Machines; i++) {
            for (int j = 0; j < Sequence.length; j++) {
                int ran = Sequence[j];
                int tmci = ProccessingTime[i][ran] + Ciarray[i][(j == 0 ? 0 : j - 1)];
                double tmDueDate = DueDate[ran] / 10;
                Tardy[i][j] = (tmci > tmDueDate);
                Ciarray[i][j] = tmci;
                DueDateAns[i][j] = tmDueDate;
            }
        }
    }

    private int[] SetCustomRandom(int number) {
        int data[] = new int[number];
        Random rand = new Random();
        for (int n = 0; n < number; ++n) {
            data[n] = n;
        }
        for (int n = 0; n < number; ++n) {
            int pos = rand.nextInt(number);
            int tmp = data[n];
            data[n] = data[pos];
            data[pos] = tmp;
        }
        return data;
    }

    public boolean[][] GetTardy() {
        return Tardy;
    }

    public double[][] GetDueDateAns() {
        return DueDateAns;
    }

    public int[][] GetCiarray() {
        return Ciarray;
    }

}
