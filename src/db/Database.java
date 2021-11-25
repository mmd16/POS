package db;

public interface Database {
	public <T> void add(T p);

	public <T> void remove(T p);
}
