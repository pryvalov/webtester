package ua.pri.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T> {

	public abstract void save(T e);

	public abstract void update(T e);

	public abstract void delete(T e);

	public abstract T findById(Serializable id);

	public abstract List<T> getList();

}