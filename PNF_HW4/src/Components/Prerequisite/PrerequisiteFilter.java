package Components.Prerequisite;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.*;

import Framework.CommonFilterImpl;

public class PrerequisiteFilter extends CommonFilterImpl {
    private List<String> studentData = new ArrayList<String>();
    private List<String> courseData = new ArrayList<String>();
    private Map<String, Set<String>> prerequisites = new HashMap<String, Set<String>>();

    @Override
    public boolean specificComputationForFilter() throws IOException {
        processDataFromInputs();
        validatePrerequisitesAndRouteData();
        return true;
    }

    private void processDataFromInputs() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder lineBuilder = new StringBuilder();

        while ((bytesRead = in.read(buffer)) != -1) {
            String dataString = new String(buffer, 0, bytesRead);
            lineBuilder.append(dataString);
            int newLineIndex;
            while ((newLineIndex = lineBuilder.indexOf("\n")) != -1) {
                String line = lineBuilder.substring(0, newLineIndex).trim();
                if (!line.isEmpty()) {
                    if (line.split(" ")[0].length() == 5) {
                        courseData.add(line);
                    } else {
                        studentData.add(line);
                    }
                }
                lineBuilder.delete(0, newLineIndex + 1);
            }
        }

        parseCoursePrerequisites();
    }

    private void parseCoursePrerequisites() {
        for (String courseLine : courseData) {
            String[] parts = courseLine.split(" ");
            String courseId = parts[0];
            Set<String> requiredCourses = new HashSet<String>();

            if (parts.length > 3) {
                requiredCourses.addAll(Arrays.asList(parts).subList(3, parts.length));
            }

            prerequisites.put(courseId, requiredCourses);
        }
    }
    private void validatePrerequisitesAndRouteData() throws IOException {
        for (String studentLine : studentData) {
            String[] studentInfo = studentLine.split(" ");
            List<String> coursesTaken = Arrays.asList(studentInfo).subList(3, studentInfo.length);

            boolean prerequisitesMet = true;
            for (String course : coursesTaken) {
                Set<String> requiredCourses = prerequisites.get(course);
                if (requiredCourses != null) {
                    for (String required : requiredCourses) {
                        if (!coursesTaken.contains(required)) {
                            prerequisitesMet = false;
                            break;
                        }
                    }
                }
                if (!prerequisitesMet) break;
            }

            if (prerequisitesMet) {
                writeToOutputPipes(studentLine, out);
            } else {
                writeToOutputPipes(studentLine, getErrorOutputPipe());
            }
        }
    }
    private void writeToOutputPipes(String data, PipedOutputStream output) throws IOException {
        String dataWithNewLine = data + "\n";
        output.write(dataWithNewLine.getBytes());
        output.flush();
    }

    private PipedOutputStream getErrorOutputPipe() {
        return this.out; // 기존 출력 파이프를 반환
    }
}