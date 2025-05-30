/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;    
        //파일을 읽어옴
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(sourceFile)));
        while(true) {
            byte_read = br.read(); //한 바이트씩 읽음
            if (byte_read == -1) return true;
            out.write(byte_read);
        }
    }
}
