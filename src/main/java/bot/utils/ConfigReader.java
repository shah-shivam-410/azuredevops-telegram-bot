package bot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties props = new Properties();
	
	public static Properties loadProps() throws IOException {
		if(props.isEmpty()) {
			synchronized (props) {
				FileInputStream fis = new FileInputStream(new File(".//src//main//resources//botconfig.properties"));
				props.load(fis);
				fis.close();
			}
		}
		return props;
	}
	

}
