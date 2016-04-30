package Utils;

import Model.Contract;
import Model.Statement;
import Model.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PdfGenerationService {

    private static String TEMPLATES_PATH = "/documentTemplates/";
    private static String OUTPUT_DOCUMENTS_PATH = "/files/";
    private static String TPL_STATEMENT = "/templateStatement.pdf";
    private static String IMG_BACKGROUND = "/backgroundPDF-2.jpg";
    private static String TPL_CONTRACT = "/templateContract.pdf";
    private static String TPL_ORDER = "/templateOrder.pdf";
    private static int FONT_SIZE_SMALL = 10;
    private static int FONT_SIZE_NORMAL = 14;
    private static int FONT_SIZE_BIG = 16;
    private static int HEAD_LINE_OFFSET = 273;
    private static int READ_LINE_OFFSET = 55;
    private static int FOOT_LINE_OFFSET = 370;
    private static int VERTICAL_SPACE_TINY = 6;
    private static int VERTICAL_SPACE_SMALL = 20;
    private static int VERTICAL_SPACE_MEDIUM = 40;
    private static int VERTICAL_SPACE_BIG = 80;
    private static int HEIGHT_SMALL_LINE = FONT_SIZE_SMALL + 1;
    private static int HEIGHT_NORMAL_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY + 2;
    private static int HEIGHT_BIG_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY;

    synchronized public static void createStatementTemplate(String initialFolder) throws Exception {
        Document document = new Document();

        try {
            BaseFont bf = BaseFont.createFont(initialFolder + TEMPLATES_PATH + "fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, FONT_SIZE_NORMAL);
            Font font5 = new Font(bf, FONT_SIZE_BIG);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(initialFolder + TEMPLATES_PATH + TPL_STATEMENT));
            document.open();

            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance(initialFolder + TEMPLATES_PATH + IMG_BACKGROUND);
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);

            Paragraph headLine = new Paragraph();
            headLine.setFont(font);
            headLine.setFirstLineIndent(HEAD_LINE_OFFSET);
            headLine.add(new Chunk("Декану "));
            headLine.add(new Chunk(getEmptyLine(58)).setUnderline(1f, -2f));
            headLine.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(headLine);

            headLine = new Paragraph();
            headLine.setFont(font);
            headLine.setFirstLineIndent(HEAD_LINE_OFFSET);
            headLine.add(new Chunk("Студента(тки) гр. "));
            headLine.add(new Chunk(getEmptyLine(40)).setUnderline(1f, -2f));
            headLine.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(headLine);

            headLine = new Paragraph();
            headLine.setFont(font);
            headLine.setFirstLineIndent(HEAD_LINE_OFFSET);
            headLine.add(new Chunk("Ф.И.О. "));
            headLine.add(new Chunk(getEmptyLine(58)).setUnderline(1f, -2f));
            headLine.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(headLine);

            headLine = new Paragraph();
            headLine.setFont(font);
            headLine.setFirstLineIndent(HEAD_LINE_OFFSET);
            headLine.add(new Chunk(getEmptyLine(71)).setUnderline(1f, -2f));
            headLine.setSpacingAfter(FONT_SIZE_BIG);
            document.add(headLine);

            Paragraph bodyParagraph = new Paragraph("Заявление", font5);
            bodyParagraph.setAlignment(Element.ALIGN_CENTER);
            bodyParagraph.setSpacingAfter(FONT_SIZE_SMALL);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph("Прошу предоставить койко-место в студенческом общежитии БГУиР. ", font);
            bodyParagraph.setIndentationLeft(READ_LINE_OFFSET);
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("Проживаю в ");
            bodyParagraph.add(new Chunk(getEmptyLine(110)).setUnderline(1f, -2f));
            bodyParagraph.add(" области,");
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("город ");
            bodyParagraph.add(new Chunk(getEmptyLine(28)).setUnderline(1f, -2f));
            bodyParagraph.add(",  улица ");
            bodyParagraph.add(new Chunk(getEmptyLine(37)).setUnderline(1f, -2f));
            bodyParagraph.add(",  дом ");
            bodyParagraph.add(new Chunk(getEmptyLine(14)).setUnderline(1f, -2f));
            bodyParagraph.add(",  квартира ");
            bodyParagraph.add(new Chunk(getEmptyLine(14)).setUnderline(1f, -2f));
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("Форма обучения: ");
            bodyParagraph.add(new Chunk(getEmptyLine(70)).setUnderline(1f, -2f));
            bodyParagraph.add("  (документы прилагаются).");
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_BIG);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("Мобильный телефон: ");
            bodyParagraph.add(new Chunk(getEmptyLine(40)).setUnderline(1f, -2f));
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("Домашний телефон:   ");
            bodyParagraph.add(new Chunk(getEmptyLine(40)).setUnderline(1f, -2f));
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add("Ф.И.О.(родителей): ");
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add(new Chunk(getEmptyLine(78)).setUnderline(1f, -2f));
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_TINY);
            document.add(bodyParagraph);

            bodyParagraph = new Paragraph();
            bodyParagraph.setFont(font);
            bodyParagraph.add(new Chunk(getEmptyLine(78)).setUnderline(1f, -2f));
            bodyParagraph.setSpacingAfter(VERTICAL_SPACE_MEDIUM);
            document.add(bodyParagraph);

            Paragraph footParagraph = new Paragraph();
            footParagraph.setFont(font);
            footParagraph.setFirstLineIndent(FOOT_LINE_OFFSET);
            footParagraph.add(new Chunk("Дата: "));
            footParagraph.add(new Chunk(getEmptyLine(32)).setUnderline(1f, -2f));
            footParagraph.setSpacingAfter(VERTICAL_SPACE_SMALL);
            document.add(footParagraph);

            footParagraph = new Paragraph();
            footParagraph.setFont(font);
            footParagraph.setFirstLineIndent(FOOT_LINE_OFFSET);
            footParagraph.add(new Chunk("Подпись: "));
            footParagraph.add(new Chunk(getEmptyLine(25)).setUnderline(1f, -2f));
            document.add(footParagraph);

        } finally {
            document.close();
        }
    }

    public static void createStudentStatement(String initialFolder, String outputFileName, Statement statement) throws Exception {
        PdfReader reader = new PdfReader(new FileInputStream(initialFolder + TEMPLATES_PATH + TPL_STATEMENT));

        File dir = new File(initialFolder + OUTPUT_DOCUMENTS_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(initialFolder + OUTPUT_DOCUMENTS_PATH + outputFileName));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont(initialFolder + TEMPLATES_PATH + "fonts/timesi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        float pageWidth = reader.getPageSize(1).getWidth();
        float pageHeight = reader.getPageSize(1).getHeight();
        stream.setFontAndSize(font, FONT_SIZE_NORMAL);

        float verticalPos = pageHeight - 52;

        stream.setTextMatrix(HEAD_LINE_OFFSET + 90, verticalPos);
        stream.showText(statement.getDean());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(HEAD_LINE_OFFSET + 150, verticalPos);
        stream.showText(statement.getGroup());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(HEAD_LINE_OFFSET + 90, verticalPos);
        stream.showText(statement.getLastName());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(HEAD_LINE_OFFSET + 43, verticalPos);
        stream.showText(statement.getFirstName() + "  " + statement.getMidName());

        verticalPos -= 93;
        stream.setTextMatrix(125, verticalPos);
        stream.showText(statement.getRegion());

        verticalPos -= HEIGHT_NORMAL_LINE;
        int horizontalPos = 80;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getCity());

        horizontalPos += 146;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getStreet());

        horizontalPos += 165;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getHouse());

        horizontalPos += 117;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getFlat());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(150, verticalPos);
        stream.showText(statement.getTraining());

        horizontalPos = 172;
        verticalPos -= 96;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getMobilePhone());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getHomePhone());

        horizontalPos = 40;
        verticalPos -= 2 * HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getMother());

        verticalPos -= HEIGHT_NORMAL_LINE;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(statement.getFather());

        verticalPos -= 56;
        stream.setTextMatrix(pageWidth - 145, verticalPos);
        stream.showText(statement.getFillingDate());

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createStudentContract(String initialFolder, String outputFileName, Contract contract, Student student) throws Exception {
        PdfReader reader = new PdfReader(new FileInputStream(initialFolder + TEMPLATES_PATH + TPL_CONTRACT));

        File dir = new File(initialFolder + OUTPUT_DOCUMENTS_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(initialFolder + OUTPUT_DOCUMENTS_PATH + outputFileName));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont(initialFolder + TEMPLATES_PATH + "fonts/timesi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        float pageWidth = reader.getPageSize(1).getWidth();
        float pageHeight = reader.getPageSize(1).getHeight();
        stream.setFontAndSize(font, FONT_SIZE_NORMAL);

        float verticalPos = pageHeight - 60;

        stream.setTextMatrix(HEAD_LINE_OFFSET + 70, verticalPos);
        stream.showText(Integer.toString(student.getStudentId()));

        verticalPos -= VERTICAL_SPACE_MEDIUM + 15;
        stream.setTextMatrix(pageWidth - 135, verticalPos);
        stream.showText(contract.getFillingDate());

        stream.setFontAndSize(font, FONT_SIZE_SMALL);
        verticalPos -= VERTICAL_SPACE_MEDIUM + 9;
        stream.setTextMatrix(READ_LINE_OFFSET + 120, verticalPos);
        stream.showText("Афанасьева Игоря Михайловича");

        verticalPos -= 2 * HEIGHT_SMALL_LINE + 1;
        stream.setTextMatrix(READ_LINE_OFFSET + 150, verticalPos);
        stream.showText(student.getLastName());

        int horizontalPos = 55;
        verticalPos -= 2 * HEIGHT_SMALL_LINE + 1;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(student.getFirstName() + "  " + student.getMidName());

        verticalPos -= HEIGHT_SMALL_LINE + 5;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(contract.getFaculty());

        verticalPos -= 2 * HEIGHT_SMALL_LINE + 1;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(contract.getChair());

        String prorector = "Прохоров А.И.";
        String studentTownChief = "Городецкий В.Н.";
        String bookKeeper = "Мухова П.З.";
        String legalAdviser = "Синицын Э.В.";

        stream = stamper.getOverContent(2);
        stream.setColorFill(BaseColor.BLUE);
        stream.setFontAndSize(font, FONT_SIZE_SMALL);

        int localOffset = 135;
        verticalPos = 234;
        horizontalPos = 185;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(prorector);

        stream.setTextMatrix(horizontalPos + localOffset, verticalPos);
        stream.showText(student.getLastName() + "  " + student.getFirstName());

        verticalPos -= 3 * HEIGHT_SMALL_LINE;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(studentTownChief);

        stream.setTextMatrix(horizontalPos + localOffset, verticalPos + 2);
        stream.showText(student.getMidName());

        verticalPos -= 3 * HEIGHT_SMALL_LINE + 1;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(bookKeeper);

        stream.setTextMatrix(horizontalPos + localOffset, verticalPos + 12);
        stream.showText("г. " + contract.getCity());

        verticalPos -= 3 * HEIGHT_SMALL_LINE;
        stream.setTextMatrix(horizontalPos, verticalPos);
        stream.showText(legalAdviser);

        stream.setTextMatrix(horizontalPos + localOffset, verticalPos + 12);
        stream.showText("ул. " + contract.getStreet() + ",  д. " + contract.getHouse() + ",  кв. " + contract.getFlat());

        stream.setTextMatrix(horizontalPos + localOffset, verticalPos - 11);
        stream.showText(contract.getPassport() + ",  " + contract.getPassportDateOfIssue());

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createStudentOrder(String initialFolder, String outputFileName, Student student, int dormitoryNumber, int blockNumber, int roomNumber) throws Exception {
        PdfReader reader = new PdfReader(new FileInputStream(initialFolder + TEMPLATES_PATH + TPL_ORDER));

        File dir = new File(initialFolder + OUTPUT_DOCUMENTS_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(initialFolder + OUTPUT_DOCUMENTS_PATH + outputFileName));
        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont(initialFolder + TEMPLATES_PATH + "fonts/timesi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        float pageHeight = reader.getPageSize(1).getHeight();
        stream.setFontAndSize(font, FONT_SIZE_BIG);

        float verticalPos = pageHeight - 71;
        float horizontalPos = 240;

        stream.setTextMatrix(horizontalPos + 135, verticalPos);
        stream.showText(Integer.toString(dormitoryNumber));

        stream.setFontAndSize(font, FONT_SIZE_NORMAL);

        verticalPos -= HEIGHT_BIG_LINE;
        stream.setTextMatrix(horizontalPos + 65, verticalPos);
        stream.showText(Integer.toString(blockNumber));

        verticalPos -= HEIGHT_BIG_LINE - 1;
        stream.setTextMatrix(horizontalPos + 100, verticalPos);
        stream.showText(Integer.toString(roomNumber));

        verticalPos -= HEIGHT_BIG_LINE;
        stream.setTextMatrix(horizontalPos + 30, verticalPos);
        stream.showText(student.getLastName());

        verticalPos -= HEIGHT_BIG_LINE - 1;
        stream.setTextMatrix(horizontalPos + 30, verticalPos);
        stream.showText(student.getFirstName());

        verticalPos -= HEIGHT_BIG_LINE - 1;
        stream.setTextMatrix(horizontalPos + 30, verticalPos);
        stream.showText(student.getMidName());

        verticalPos -= HEIGHT_BIG_LINE - 1;
        stream.setTextMatrix(horizontalPos + 70, verticalPos);
        stream.showText(student.getGroupNumber());

        verticalPos -= HEIGHT_BIG_LINE + 1;
        stream.setTextMatrix(horizontalPos + 155, verticalPos);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(student.getDateOfSettlement());
        calendar.add(Calendar.MONTH, 5);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateDuration = format.format(calendar.getTime());

        stream.showText(dateDuration);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    private static String getEmptyLine(int symbolsCount){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < symbolsCount; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

}
