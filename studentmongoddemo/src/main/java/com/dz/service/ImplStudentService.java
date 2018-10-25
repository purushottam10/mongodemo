package com.dz.service;

import com.dz.dao.ImplStudentDAO;
import com.dz.model.Student;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
 * Operational Class
 */
public class ImplStudentService implements  StudentService{

    private Student student;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private ImplStudentDAO studentDao;


    public void addStudent() throws IOException{
        String name = null;
        Integer roll_no = 1;
        Integer age = 0;

        ImplStudentDAO studentDao = new ImplStudentDAO();
        Student student = new Student();
        List<Document> cursor = studentDao.display();
        for (Document doc : cursor){
            roll_no= doc.getInteger("roll_no")+1;
        }
      //  System.out.println("enter the student Roll NO ");
       /* try {
            roll_no = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("enter the Student Name");

        try {
            name = bufferedReader.readLine();

            System.out.println("enter the age of student ");

            age = Integer.parseInt(bufferedReader.readLine());

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        student.setRoll_no(roll_no);
        student.setName(name);
        student.setAge(age);

        studentDao.addStudent(student);

    }// addStudent method end


    public void display() {

        studentDao = new ImplStudentDAO();
        List<Document> cursor = studentDao.display();
        if(cursor.isEmpty()){
            System.err.println("no data in Document ");
        }
        try {
            for (Document doc : cursor) {

                System.out.println(doc.getInteger("roll_no")+"   "+doc.getString("name")+" "+doc.getInteger("age"));
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }// method end


    /**
     * show specific Detail
     */
    public void DisplayById(int roll_no) {

        studentDao = new ImplStudentDAO();

        List<Document> cursor = studentDao.display();
        for (Document doc : cursor) {
         if(doc.getInteger("roll_no")==roll_no){
             System.out.println(doc.getInteger("roll_no")+"   "+doc.getString("name")+" "+doc.getInteger("age"));
         }
        }

    }
//method end


    public void deleteById(int roll_no) {
        studentDao = new ImplStudentDAO();
        studentDao.deleteById(roll_no);
    }//method end

    /**
     * Update record by id
     */
    public void updateById(int roll_no) throws IOException {
        /*
         */
        studentDao = new ImplStudentDAO();
        String name = null;
        Integer age = 0;
        student = new Student();
        System.out.println("enter the Student Name");
        name = bufferedReader.readLine();


        try {
            System.out.println("enter the age of student ");
            age = Integer.parseInt(bufferedReader.readLine());

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        student.setRoll_no(roll_no);
        student.setName(name);
        student.setAge(age);
        studentDao.updateById( student);


    }//update method end


}//class end