package Components.Delete;
import java.io.IOException;
import Framework.CommonFilterImpl;
public class DeleteFilter extends CommonFilterImpl {
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
            if (data.startsWith("2013") && !data.contains("CS")) {
                if (data.contains("17651")) {
                    data = data.replace("17651", "").trim();
                }
                if (data.contains("17652")) {
                    data = data.replace("17652", "").trim();
                }

                System.out.println("Modified Data (2013, non-CS): " + data);
                for (char ch : data.toCharArray()) {
                    out.write((int) ch);
                }
                out.write('\n'); 
            }
            if (byte_read == -1) return true;
            idx = 0;
            byte_read = '\0';
        }
    }
}