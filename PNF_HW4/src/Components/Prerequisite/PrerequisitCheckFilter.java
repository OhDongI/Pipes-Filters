package Components.Prerequisite;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.*;

import Framework.CommonFilterImpl;

public class PrerequisitCheckFilter extends CommonFilterImpl {
    private List<String> studentData = new ArrayList<String>();
    private List<String> courseData = new ArrayList<String>();
    private Map<String, Set<String>> prerequisites = new HashMap<String, Set<String>>();

    private PipedOutputStream output1 = new PipedOutputStream();
    private PipedOutputStream output2 = new PipedOutputStream();

    @Override
    public boolean specificComputationForFilter() throws IOException {
        processDataFromInputs();
        validatePrerequisites();
        return true;
    }

    private void processDataFromInputs() throws IOException {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.split(" ")[0].length() == 5) {
                courseData.add(line);
            } else {
                studentData.add(line);
            }
        }
        parseCoursePrerequisites();
    }

    private void parseCoursePrerequisites() {
        for (String courseLine : courseData) {
            String[] parts = courseLine.split(" ");
            prerequisites.put(parts[0], new HashSet<String>(Arrays.asList(parts).subList(3, parts.length)));
        }
    }

    private void validatePrerequisites() throws IOException {
        for (String studentLine : studentData) {
            String[] studentInfo = studentLine.split(" ");
            List<String> coursesTaken = Arrays.asList(studentInfo).subList(3, studentInfo.length);

            boolean prerequisitesMet = true;
            for (String course : coursesTaken) {
                Set<String> required = prerequisites.get(course);
                if (required != null && !coursesTaken.containsAll(required)) {
                    prerequisitesMet = false;
                    break;
                }
            }

            if (prerequisitesMet) {
                output1.write((studentLine + "\n").getBytes());
            } else {
                output2.write((studentLine + "\n").getBytes());
            }
        }
        output1.flush();
        output2.flush();
    }

    public PipedOutputStream getOutput1() {
        return output1;
    }

    public PipedOutputStream getOutput2() {
        return output2;
    }
}
