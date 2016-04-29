package Repositories;

import Model.Student;
import Model.StudentStatus;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StudentRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        String expectedFirstName = "Русский";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-11-09");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        StudentStatus expectedStatus = StudentStatus.Default;
        int expectedUserId = 1;

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        int last_inserted_id = StudentRepository.create(expectedStudent);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(last_inserted_id);

        Assert.assertEquals(last_inserted_id, actualStudent.getStudentId());
        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(expectedStatus, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateGetStudentByUserIdDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-12-16");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        StudentStatus expectedStatus = StudentStatus.Default;
        int expectedUserId = 1;

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        Student actualStudent = StudentRepository.getStudentByUserId(expectedStudent.getUserId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(expectedStatus, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateReadAllCountDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-11-06");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setUserId(expectedUserId);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAll();

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        Assert.assertTrue(actualStudents.size() >= 2);
    }

    @Test
    public void testCreateReadAllCheckContainsDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-10-13");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        ArrayList<Student> expectedStudents = new ArrayList<Student>();
        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent1);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent2);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAll();

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        for(Student actStudent:actualStudents){
            for(Student expStudent:expectedStudents){
                if (actStudent.getStudentId() == expStudent.getStudentId()) {
                    Assert.assertEquals(expStudent.getFirstName(), actStudent.getFirstName());
                    Assert.assertEquals(expStudent.getMidName(), actStudent.getMidName());
                    Assert.assertEquals(expStudent.getLastName(), actStudent.getLastName());
                    Assert.assertEquals(expStudent.getDateOfBirth().toString(), actStudent.getDateOfBirth().toString());
                    Assert.assertEquals(expStudent.getGroupNumber(), actStudent.getGroupNumber());
                    Assert.assertEquals(expStudent.getStudentStatus(), actStudent.getStudentStatus());
                    Assert.assertEquals(expStudent.getUserId(), actStudent.getUserId());
                }
            }
        }
    }

    @Test
    public void testCreateReadAllByStatusCountDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-19");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        StudentStatus expectedStatus = StudentStatus.BodyCheckPassed;
        int expectedUserId = 1;

        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setStudentStatus(expectedStatus);
        expectedStudent1.setUserId(expectedUserId);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setStudentStatus(expectedStatus);
        expectedStudent2.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByStatus(expectedStatus);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        Assert.assertTrue(actualStudents.size() >= 2);
    }

    @Test
    public void testCreateReadAllByStatusCheckContainsDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-11-11");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        StudentStatus expectedStatus = StudentStatus.Candidate;
        int expectedUserId = 1;

        ArrayList<Student> expectedStudents = new ArrayList<Student>();
        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setStudentStatus(expectedStatus);
        expectedStudent1.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent1);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setStudentStatus(expectedStatus);
        expectedStudent2.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent2);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByStatus(expectedStatus);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        for(Student actStudent:actualStudents){
            for(Student expStudent:expectedStudents){
                if (actStudent.getStudentId() == expStudent.getStudentId()) {
                    Assert.assertEquals(expStudent.getFirstName(), actStudent.getFirstName());
                    Assert.assertEquals(expStudent.getMidName(), actStudent.getMidName());
                    Assert.assertEquals(expStudent.getLastName(), actStudent.getLastName());
                    Assert.assertEquals(expStudent.getDateOfBirth().toString(), actStudent.getDateOfBirth().toString());
                    Assert.assertEquals(expStudent.getGroupNumber(), actStudent.getGroupNumber());
                    Assert.assertEquals(expStudent.getStudentStatus(), actStudent.getStudentStatus());
                    Assert.assertEquals(expStudent.getUserId(), actStudent.getUserId());
                }
            }
        }
    }

    @Test
    public void testCreateReadAllByStatusCheckStatusDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-08-17");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        StudentStatus expectedStatus = StudentStatus.BodyCheckNotPassed;
        int expectedUserId = 1;

        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setStudentStatus(expectedStatus);
        expectedStudent1.setUserId(expectedUserId);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setStudentStatus(expectedStatus);
        expectedStudent2.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByStatus(expectedStatus);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        for(Student actStudent:actualStudents){
            Assert.assertEquals(expectedStatus, actStudent.getStudentStatus());
        }
    }

    @Test
    public void testCreateReadAllByLastNameCountDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-03-02");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setUserId(expectedUserId);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByLastName(expectedLastName);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        Assert.assertTrue(actualStudents.size() >= 2);
    }

    @Test
    public void testCreateReadAllByLastNameCheckContainsDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-05-14");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        ArrayList<Student> expectedStudents = new ArrayList<Student>();
        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent1);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setUserId(expectedUserId);
        expectedStudents.add(expectedStudent2);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByLastName(expectedLastName);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        for(Student actStudent:actualStudents){
            for(Student expStudent:expectedStudents){
                if (actStudent.getStudentId() == expStudent.getStudentId()) {
                    Assert.assertEquals(expStudent.getFirstName(), actStudent.getFirstName());
                    Assert.assertEquals(expStudent.getMidName(), actStudent.getMidName());
                    Assert.assertEquals(expStudent.getLastName(), actStudent.getLastName());
                    Assert.assertEquals(expStudent.getDateOfBirth().toString(), actStudent.getDateOfBirth().toString());
                    Assert.assertEquals(expStudent.getGroupNumber(), actStudent.getGroupNumber());
                    Assert.assertEquals(expStudent.getStudentStatus(), actStudent.getStudentStatus());
                    Assert.assertEquals(expStudent.getUserId(), actStudent.getUserId());
                }
            }
        }
    }

    @Test
    public void testCreateReadAllByLastNameCheckLastNameDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-03");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        Student expectedStudent1 = new Student();
        expectedStudent1.setFirstName(expectedFirstName);
        expectedStudent1.setMidName(expectedMidName);
        expectedStudent1.setLastName(expectedLastName);
        expectedStudent1.setDateOfBirth(expectedDateOfBirth);
        expectedStudent1.setGroupNumber(expectedGroupNumber);
        expectedStudent1.setUserId(expectedUserId);

        Student expectedStudent2 = new Student();
        expectedStudent2.setFirstName(expectedFirstName);
        expectedStudent2.setMidName(expectedMidName);
        expectedStudent2.setLastName(expectedLastName);
        expectedStudent2.setDateOfBirth(expectedDateOfBirth);
        expectedStudent2.setGroupNumber(expectedGroupNumber);
        expectedStudent2.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent1);
        StudentRepository.create(expectedStudent2);
        ArrayList<Student> actualStudents = StudentRepository.readAllByLastName(expectedLastName);

        StudentRepository.delete(expectedStudent1.getStudentId());
        StudentRepository.delete(expectedStudent2.getStudentId());

        for(Student actStudent:actualStudents){
            Assert.assertEquals(expectedLastName, actStudent.getLastName());
        }
    }

    @Test
    public void testCreateUpdateStatusReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-12-12");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        StudentStatus newExpectedStatus = StudentStatus.DeaneryPassed;

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateStatus(expectedStudent.getStudentId(), newExpectedStatus);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(newExpectedStatus, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateUpdateStatementReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-11");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        String newExpectedStatement = "Test5";

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateStatement(expectedStudent.getStudentId(), newExpectedStatement);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(StudentStatus.Default, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(newExpectedStatement, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateUpdateDateOfSettlementReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-10-2");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        String newExpectedDateOfSettlement = "1996-08-13";
        parsed = formatter.parse(newExpectedDateOfSettlement);
        Date dateOfSettlement = new java.sql.Date(parsed.getTime());

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateDateOfSettlement(expectedStudent.getStudentId(), dateOfSettlement);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(StudentStatus.Default, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(newExpectedDateOfSettlement, actualStudent.getDateOfSettlement().toString());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateUpdateOrderReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-11");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        String newExpectedOrder = "Test5";

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateOrder(expectedStudent.getStudentId(), newExpectedOrder);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(StudentStatus.Default, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(newExpectedOrder, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateUpdateContractReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-11");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        String newExpectedContract = "Test5";

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateContract(expectedStudent.getStudentId(), newExpectedContract);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(StudentStatus.Default, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(newExpectedContract, actualStudent.getContract());
        Assert.assertEquals(0, actualStudent.getRoomId());
    }

    @Test
    public void testCreateUpdateRoomIdReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-06-11");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        int newExpectedRoomId = 1;

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        StudentRepository.create(expectedStudent);
        StudentRepository.updateRoomId(expectedStudent.getStudentId(), newExpectedRoomId);
        Student actualStudent = StudentRepository.read(expectedStudent.getStudentId());
        StudentRepository.delete(expectedStudent.getStudentId());

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(StudentStatus.Default, actualStudent.getStudentStatus());
        Assert.assertEquals(expectedUserId, actualStudent.getUserId());

        Assert.assertEquals(null, actualStudent.getStatement());
        Assert.assertEquals(null, actualStudent.getDateOfSettlement());
        Assert.assertEquals(null, actualStudent.getOrder());
        Assert.assertEquals(null, actualStudent.getContract());
        Assert.assertEquals(newExpectedRoomId, actualStudent.getRoomId());
    }

    @Test
    public void testCreateDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse("1996-11-09");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        int expectedUserId = 1;

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setUserId(expectedUserId);

        int last_inserted_id = StudentRepository.create(expectedStudent);
        StudentRepository.delete(last_inserted_id);
        Student actualStudent = StudentRepository.read(last_inserted_id);

        Assert.assertEquals(null, actualStudent);
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }

}