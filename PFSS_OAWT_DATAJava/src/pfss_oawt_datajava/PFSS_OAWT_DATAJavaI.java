/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfss_oawt_datajava;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author user
 */
public interface PFSS_OAWT_DATAJavaI {
    public void Calculation(String Path) throws IOException;
    public String readFile(String path, Charset encoding) throws IOException;
    public int[] CustomRandom(int number);
    public void DefaultValue();
    public void SetParameters();
    public void Output();
    public void TestList();
    
}
