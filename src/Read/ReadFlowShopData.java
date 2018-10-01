/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Read;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class ReadFlowShopData {

    public ReadFlowShopData() {
    }

    // <editor-fold  desc="預設變數">
    String Path;
    double[] Weight;
    int Jobs, Machines;
    int[] Revenue, DueDate;
    String[] PathDataArrays;
    int[][] ProccessingTime;
    // </editor-fold>

    public void SetData(String Path) {
        try {
            this.Path = Path;
            if (Path == null) {
                System.out.println("尚未輸入路徑");
                System.exit(1);
            } else {
                PathDataArrays = readFile(Path, Charset.defaultCharset()).split("\\s+");
                initiateVars();
            }
        } catch (IOException e) {

        }

    }

    public String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void initiateVars() {
        Jobs = Integer.parseInt(PathDataArrays[0].trim());
        Machines = Integer.parseInt(PathDataArrays[1].trim());
        ProccessingTime = new int[Machines][Jobs];
        Revenue = new int[Jobs];
        DueDate = new int[Jobs];
        Weight = new double[Jobs];
    }

    public void GetDataFromFile() {
        for (int i = 0; i < Jobs; i++) {
            Revenue[i] = Integer.parseInt(PathDataArrays[2 + i * 3].trim());
            DueDate[i] = Integer.parseInt(PathDataArrays[3 + i * 3].trim());
            Weight[i] = Double.parseDouble(PathDataArrays[4 + i * 3].trim());
        }
        for (int i = 0; i < Machines; i++) {
            for (int j = 0; j < Jobs; j++) {
                String ste =(PathDataArrays[((2 + i) + Jobs * 3) + j * 5].trim());
                ProccessingTime[i][j] = Integer.parseInt(PathDataArrays[((2 + i) + Jobs * 3) + j * 5].trim());
            }
        }
    }

    public int GetJobs() {
        return Jobs;
    }

    public int GetMachines() {
        return Machines;
    }

    public int[][] GetPtime() {
        return ProccessingTime;
    }

    public int[] GetDueDate() {
        return DueDate;
    }

    public int[] GetRevenue() {
        return Revenue;
    }

    public double[] GetWeight() {
        return Weight;
    }
}
