package jpa;


public interface ServiceInterface<T> {
	public void delete(T t);
	public void add(T t);
	public T update(T t);
	public T get(Long t);
}
