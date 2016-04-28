package Utils;

import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

public class ExcelGenerationService {

    private static StudentRepository studentRepository = new StudentRepository();
    private static String OUTPUT_DOCUMENTS_PATH = "web/files/";

    public static void createStudentsSheetsByStatuses(String outputFileName, List<StudentStatus> statuses) throws Exception {
        FileOutputStream os = null;
        Workbook book = null;
        try {
            book = new XSSFWorkbook();
            Row row;
            Cell cell;
            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("dd.MM.yyyy"));

            for(StudentStatus status:statuses) {
                List<Student> students = studentRepository.readAllByStatus(status);
                Sheet sheet = book.createSheet(getSheetNameByStatus(status));
                String[] names = {"№", "Фамилия", "Имя", "Отчество", "Группа", "№ комнаты", "№ блока", "№ общежития", "Дата заселения"};

                int rowNumber = 0;
                row = sheet.createRow(rowNumber++);
                for (int i = 0; i < names.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(names[i]);
                }
                for (Student student : students) {

                    int roomNumber = studentRepository.getRoomNumberByStudentId(student.getStudentId());
                    int blockNumber = studentRepository.getBlockNumberByStudentId(student.getStudentId());
                    int dormitoryNumber = studentRepository.getDormitoryNumberByStudentId(student.getStudentId());

                    row = sheet.createRow(rowNumber);
                    cell = row.createCell(0);
                    cell.setCellValue(rowNumber);
                    cell = row.createCell(1);
                    cell.setCellValue(student.getLastName());
                    cell = row.createCell(2);
                    cell.setCellValue(student.getFirstName());
                    cell = row.createCell(3);
                    cell.setCellValue(student.getMidName());
                    cell = row.createCell(4);
                    cell.setCellValue(student.getGroupNumber());
                    cell = row.createCell(5);
                    if (roomNumber == 0) { cell.setCellValue(""); }
                    else { cell.setCellValue(roomNumber); }
                    cell = row.createCell(6);
                    if (blockNumber == 0) { cell.setCellValue(""); }
                    else { cell.setCellValue(blockNumber); }
                    cell = row.createCell(7);
                    if (dormitoryNumber == 0) { cell.setCellValue(""); }
                    else { cell.setCellValue(dormitoryNumber); }
                    cell = row.createCell(8);
                    cell.setCellStyle(dateStyle);
                    Date date = student.getDateOfSettlement();
                    if (date == null) { cell.setCellValue(""); }
                    else { cell.setCellValue(date); }
                    rowNumber++;
                }
                for (int i = 0; i < names.length; i++) {
                    sheet.autoSizeColumn(i);
                }
            }
            File dir = new File(OUTPUT_DOCUMENTS_PATH);
            if (!dir.exists()){
                dir.mkdir();
            }
            os = new FileOutputStream(new File(OUTPUT_DOCUMENTS_PATH + outputFileName));
            book.write(os);

        } finally {
            if (os != null) { os.close(); }
            if (book != null) { book.close(); }
        }
    }

    private  static String getSheetNameByStatus(StudentStatus status){
        switch (status){
            case Default:
                return "Зарегистрированные";
            case Candidate:
                return "Кандидаты";
            case DeaneryPassed:
                return "Прошли деканат";
            case DeaneryNotPassed:
                return "Не прошли деканат";
            case BodyCheckPassed:
                return "Прошли мед.осмотр";
            case BodyCheckNotPassed:
                return "Не прошли мед.осмотр";
            case Settled:
                return "Заселенные";
            case NotSettled:
                return "Не заселенные";
            default:
                return "Другое";
        }
    }

}
