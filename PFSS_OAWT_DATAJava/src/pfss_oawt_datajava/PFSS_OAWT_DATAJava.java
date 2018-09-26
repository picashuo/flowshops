/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfss_oawt_datajava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author user
 */
public class PFSS_OAWT_DATAJava {
    
        int Jobs,machines;
        int[]Revenue ,DueDay;
        boolean[][]Tardy;
        double[]Weight;
        double[][]DueDayAns;
        int[][]ProccessingTime,ciarray;
        String[] arrays ;
        String Path;
        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        PFSS_OAWT_DATAJava pfss = new PFSS_OAWT_DATAJava(); 
        pfss.Calculation();
    }
    
    public void Calculation() throws IOException
    {
        Path = "src\\File\\p10x5_1.txt";
        arrays          = readFile(Path,Charset.defaultCharset()).split("\t| |\n");
        Jobs            = Integer.parseInt(arrays[0].trim());
        machines        = Integer.parseInt(arrays[1].trim());
        Revenue         = new int[Jobs];
        DueDay          = new int[Jobs];
        Weight          = new double[Jobs];
        ciarray         = new int[machines][Jobs];
        Tardy           = new boolean[machines][Jobs];
        DueDayAns       = new double[machines][Jobs];
        ProccessingTime = new int[machines][Jobs];
        
        int[] Sequence = Randoms();
        for (int i = 0; i < Jobs; i++) {
              Revenue[i] = Integer.parseInt(arrays[2+i*3].trim());
              DueDay[i]  = Integer.parseInt(arrays[3+i*3].trim());
              Weight[i]  = Double.parseDouble(arrays[4+i*3].trim());
        }
        //ProccessingTime
        for (int i = 0 ; i < machines; i++) {
           for (int j = 0; j < Jobs; j++) {
               ProccessingTime[i][j] =Integer.parseInt(arrays[((2+i)+Jobs*3)+j*5].trim());
           }
            for (int j = 0; j < Sequence.length; j++) {
              int      ran  = Sequence[j];
              int      tmci = ProccessingTime[i][ran]+ciarray[i][(j==0?0:j-1)];
           double  tmDueDay = DueDay[ran]/10;
                 ciarray[i][j] = tmci;
               DueDayAns[i][j] = tmDueDay;
                   Tardy[i][j] = (tmci>tmDueDay?true:false);
            }
        }
        //print
        for (int i = 0; i < machines; i++) {
            System.out.println("machines："+(i+1));
            System.out.println("Sequence："+Arrays.toString(Sequence));
            System.out.println("Ci："+Arrays.toString(ciarray[i]));
            System.out.println("DueDay："+Arrays.toString(DueDayAns[i]));
            System.out.println("Tardy："+Arrays.toString(Tardy[i]));
            System.out.println("==================================");
        }
    }
    
    public String readFile(String path, Charset encoding)  throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    private int[] Randoms (){
	int data[] = new int[Jobs]; 
	Random rand = new Random(); 
	for (int n = 0; n < Jobs; ++n) { 
	    data[n] = n ; 
	} 
        for (int n = 0; n < Jobs; ++n) { 
	    int pos = rand.nextInt(Jobs); 
	    int tmp = data[n]; 
	    data[n] = data[pos]; 
	    data[pos] = tmp; 
	} 
            return data;
    }
}
