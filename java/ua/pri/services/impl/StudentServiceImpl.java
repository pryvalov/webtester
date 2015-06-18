package ua.pri.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import ua.pri.dao.TestDao;
import ua.pri.ent.Test;
import ua.pri.services.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class);
	@Autowired
	TestDao testDao;
	
	@Override
	public List<Test> listTests(){
		
		return testDao.getList();
		
	}
	@Override
	public List<Test> listTests(int offset, int limit){
		
		return testDao.getList(offset, limit);
		
	}
	
	@Override
	public Test loadTest(int id_test){
		return testDao.loadTest(id_test);
		
	}
	
}
