package ua.pri.services;

import java.util.List;

import ua.pri.ent.Test;

public interface StudentService {

	public abstract List<Test> listTests();

	public abstract List<Test> listTests(int offset, int limit);

	public abstract Test loadTest(int id_test);

}