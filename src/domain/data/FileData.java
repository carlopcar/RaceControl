package domain.data;


public interface FileData {

	public void write(String filename, String data);

	public String read(String filename);
}
