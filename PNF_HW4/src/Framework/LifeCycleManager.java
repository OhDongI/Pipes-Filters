/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import Components.Prerequisite.PrerequisitCheckFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SourceFilter("Courses.txt");
            PrerequisitCheckFilter filter3 = new PrerequisitCheckFilter();
            SinkFilter filter4 = new SinkFilter("Output-1.txt");
            SinkFilter filter5 = new SinkFilter("Output-2.txt");

            filter1.connectOutputTo(filter3);
            filter2.connectOutputTo(filter3);
            filter3.getOutput1().connect(filter4.getPipedInputStream());
            filter3.getOutput2().connect(filter5.getPipedInputStream());

            new Thread(filter1).start();
            new Thread(filter2).start();
            new Thread(filter3).start();
            new Thread(filter4).start();
            new Thread(filter5).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}