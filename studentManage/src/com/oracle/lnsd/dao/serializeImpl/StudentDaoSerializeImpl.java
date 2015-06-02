package com.oracle.lnsd.dao.serializeImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;

public class StudentDaoSerializeImpl implements StudentDao {


	@Override
	public void saveEntity(Student student) {
		File studentFile = getFile(student.getName());
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(studentFile))){
			output.writeObject(student);
		} catch (IOException e) {
	        e.printStackTrace();
        }
	}
	/**
	 * ȡ�����л��ļ�
	 * @param name
	 * @return
	 */
	private File getFile(String name){
		File userFile = new File(getDir(), name);
		return userFile;
	}
	/**
	 * ȡ�����л��ļ�·��
	 * @return
	 */
	private File getDir() {
		File dir= new File("c:/student-dir");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	@Override
    public boolean isEntityExists(String name) {
	    File studentFile = getFile(name);
	    return studentFile.exists();
    }

	@Override
    public List<Student> listStudent() {
		File[] studentFiles = getDir().listFiles();
		List<Student> studentList = new ArrayList<>();
		for (File file : studentFiles) {
			//���η����л�ÿ���ļ�������һ��Student������ӵ�studentList��
	        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))){
				Student student = (Student) input.readObject();
				studentList.add(student);
			} catch (IOException e) {
	            e.printStackTrace();
            } catch (ClassNotFoundException e) {
	            e.printStackTrace();
            }
        }
	    return studentList;
    }
}
