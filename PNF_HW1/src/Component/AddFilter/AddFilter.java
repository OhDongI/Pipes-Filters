/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Component.AddFilter;
import java.io.IOException;
import Framework.CommonFilterImpl;
public class AddFilter extends CommonFilterImpl{
	  @Override
	    public boolean specificComputationForFilter() throws IOException {
	        int idx = 0;
	        byte[] buffer = new byte[64];   
	        int byte_read = 0;
	        while (true) { 
	            while (byte_read != '\n' && byte_read != -1) {
	                byte_read = in.read();
	                if (byte_read != -1) buffer[idx++] = (byte) byte_read;
	            }
	            String data = new String(buffer, 0, idx);
	            if (!data.contains("12345")) {
	                data += " 12345";
	            }
	            if (!data.contains("23456")) {
	                data += " 23456";
	            }
	            System.out.println("Modified Data: " +data);
	            for (char ch : data.toCharArray()) { //
	                out.write((int) ch); 
	            }
	            out.write('\n');
	            if (byte_read == -1) return true;
	            idx = 0;
	            byte_read = '\0';
	        }
	    }
	}