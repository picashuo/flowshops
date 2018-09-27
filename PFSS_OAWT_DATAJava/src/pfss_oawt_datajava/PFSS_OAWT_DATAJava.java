/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfss_oawt_datajava;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PFSS_OAWT_DATAJava implements PFSS_OAWT_DATAJavaI {

    // <editor-fold  desc="預設變數">
    String Path;
    double[] Weight;
    boolean[][] Tardy;
    int Jobs, Machines;
    double[][] DueDayAns;
    int[] Revenue, DueDay;
    String[] PathDataArrays;
    int[][] ProccessingTime, Ciarray;
    int[] Sequence;
    ArrayList<String> TempList;
    // </editor-fold>

    @Override
    public void Calculation(String Path) throws IOException {
        PathDataArrays = readFile(Path, Charset.defaultCharset()).split("\t| |\n");
        this.Path = Path;
        TestList();
        DefaultValue();
        SetParameters();
        Output();
    }

    @Override
    public String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public int[] CustomRandom(int number) {
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

    @Override
    public void DefaultValue() {
        Jobs = Integer.parseInt(PathDataArrays[0].trim());
        Machines = Integer.parseInt(PathDataArrays[1].trim());
        Revenue = new int[Jobs];
        DueDay = new int[Jobs];
        Weight = new double[Jobs];
        Ciarray = new int[Machines][Jobs];
        Tardy = new boolean[Machines][Jobs];
        DueDayAns = new double[Machines][Jobs];
        ProccessingTime = new int[Machines][Jobs];
        Sequence = CustomRandom(Jobs);
    }

    @Override
    public void SetParameters() {
        for (int i = 0; i < Jobs; i++) {
            Revenue[i] = Integer.parseInt(PathDataArrays[2 + i * 3].trim());
            DueDay[i] = Integer.parseInt(PathDataArrays[3 + i * 3].trim());
            Weight[i] = Double.parseDouble(PathDataArrays[4 + i * 3].trim());
        }
        for (int i = 0; i < Machines; i++) {
            for (int j = 0; j < Jobs; j++) {
                ProccessingTime[i][j] = Integer.parseInt(PathDataArrays[((2 + i) + Jobs * 3) + j * 5].trim());
            }
            for (int j = 0; j < Sequence.length; j++) {
                int ran = Sequence[j];
                int tmci = ProccessingTime[i][ran] + Ciarray[i][(j == 0 ? 0 : j - 1)];
                double tmDueDay = DueDay[ran] / 10;
                Ciarray[i][j] = tmci;
                DueDayAns[i][j] = tmDueDay;
                Tardy[i][j] = (tmci > tmDueDay ? true : false);
            }
        }
    }

    @Override
    public void Output() {
        System.out.println("==================================");
        System.out.println("路徑：" + Path);
        System.out.println("Jobs：" + Jobs);
        System.out.println("DueDay：" + Arrays.toString(DueDay));
        System.out.println("Weight：" + Arrays.toString(Weight));
        System.out.println("Revenue：" + Arrays.toString(Revenue));
        System.out.println("==================================");
        for (int i = 0; i < Machines; i++) {
            System.out.println("Machines：" + (i + 1));
            System.out.println("Sequence：" + Arrays.toString(Sequence));
            System.out.println("Ci：" + Arrays.toString(Ciarray[i]));
            System.out.println("DueDay：" + Arrays.toString(DueDayAns[i]));
            System.out.println("Tardy：" + Arrays.toString(Tardy[i]));
            System.out.println("==================================");
        }
    }
    @Override
    public void TestList() {
        TempList.add("test");
        TempList.get(1);
        TempList.remove(1);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        PFSS_OAWT_DATAJava pfss = new PFSS_OAWT_DATAJava();
//        FileDialog dialog = new FileDialog((Frame) null, "開啟檔案");
//        dialog.setMode(FileDialog.LOAD);
//        dialog.setVisible(true);
//        String file = dialog.getFile();
//        System.out.println(dialog.getDirectory()+dialog.getFile());
        pfss.TestList();
        pfss.Calculation("src\\File\\p10x5_1.txt");
    }

}
