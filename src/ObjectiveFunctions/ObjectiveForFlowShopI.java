/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectiveFunctions;

/**
 *
 * @author User
 */
public interface ObjectiveForFlowShopI {
     public void SetScheduleData(int Machines, int Jobs, int[] DueDate, int[][] ProccessingTime) ;
     public void CalcObjective();
     public boolean[][] GetTardy();
     public double[][] GetDueDateAns();
     public int[][] GetCiarray();
}
