package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import domain.data.FileData;

public class FileDataImp implements FileData {

	private static FileDataImp dataImp;
	
	private FileDataImp() {
	}

	public static FileDataImp getInstance() {
		if (dataImp == null) {
			dataImp = new FileDataImp();
		}
		return dataImp;
	}

	@Override
	public void write(String filename, String data) {

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
			writer.write(data);
		} catch (IOException e) {
			e.getMessage();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
	}

	@Override
	public String read(String filename) {

		BufferedReader reader = null;
		String fileString = "";
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				fileString += line;
			}

		} catch (IOException e) {
			e.getMessage();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
		return fileString;
	}



}
