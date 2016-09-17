package br.com.studies.algorithms.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.util.ResourceUtils;

public final class FileUtils {

	private FileUtils(){}
	
	public static List<String> readAllLines(String path){
		List<String> lines = null;
		try {
			File f = ResourceUtils.getFile(String.format("classpath:%s", path));
			lines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
}
