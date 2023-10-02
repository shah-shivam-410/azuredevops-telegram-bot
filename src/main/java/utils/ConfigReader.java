package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private ConfigReader() {}

	private static Properties props = new Properties();

	public static Properties loadProps() {
		if (props.isEmpty()) {
			synchronized (props) {
				try (FileInputStream fis = new FileInputStream(
						new File(".//src//main//resources//config.properties"))) {
					props.load(fis);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

}
