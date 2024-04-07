package docvel.hw_Aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class HwAspectApplication {

	public static void main(String[] args) {
		Path inPath = Path.of("src/main/resources/files/bootstrap.min.css");
		Path outPath = Path.of("src/main/resources/files/newBootstrap.min.css");

		ApplicationContext context = SpringApplication.run(HwAspectApplication.class, args);

		ConvertFile convertFile = context.getBean(ConvertFile.class);
		convertFile.setInPath(inPath);
		convertFile.setOutPath(outPath);
		List<String> list = convertFile.getStringsFromFiles();

		list = convertFile.convertList(list, "((?=\\})|(?<=\\{|\\}|;))");
		convertFile.saveListToFile(list);
	}

}
