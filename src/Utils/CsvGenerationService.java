package Utils;

import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.util.List;

public class CsvGenerationService {

    private static StudentRepository studentRepository = new StudentRepository();
    private static String OUTPUT_DOCUMENTS_PATH = "web/files/";

    public static void createCSVListByStudentStatus(String outputFileName, StudentStatus status) throws Exception {
        File dir = new File(OUTPUT_DOCUMENTS_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }
        File outputFile = new File(OUTPUT_DOCUMENTS_PATH + outputFileName);
        FileOutputStream fos = null;
        OutputStreamWriter out = null;
        List<Student> students = studentRepository.readAllByStatus(status);

        StringBuilder data = new StringBuilder();
        try {

            fos = new FileOutputStream(outputFile);
            int studentCounter = 0;
            for (Student student : students) {

                int roomNumber = studentRepository.getRoomNumberByStudentId(student.getStudentId());
                int blockNumber = studentRepository.getBlockNumberByStudentId(student.getStudentId());
                int dormitoryNumber = studentRepository.getDormitoryNumberByStudentId(student.getStudentId());

                data.append(++studentCounter); data.append(";");
                data.append(student.getLastName()); data.append(";");
                data.append(student.getFirstName()); data.append(";");
                data.append(student.getMidName()); data.append(";");
                data.append(student.getGroupNumber()); data.append(";");
                if (roomNumber == 0) { data.append(""); }
                else { data.append(roomNumber); }
                data.append(";");
                if (blockNumber == 0) { data.append(""); }
                else { data.append(blockNumber); }
                data.append(";");
                if (dormitoryNumber == 0) { data.append(""); }
                else { data.append(dormitoryNumber); }
                data.append(";");
                Date date = student.getDateOfSettlement();
                if (date == null) { data.append(""); }
                else { data.append(date); }
                data.append(";");
                data.append("\r\n");
            }
            out = new OutputStreamWriter(fos, "Windows-1251");
            out.write(data.toString());
            out.flush();
        } finally {
            if (fos != null) { fos.close(); }
            if (out != null) { out.close(); }
        }
    }

}
