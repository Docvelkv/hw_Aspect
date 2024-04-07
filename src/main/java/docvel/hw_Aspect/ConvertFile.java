package docvel.hw_Aspect;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
public class ConvertFile {

    private Path inPath;
    private Path outPath;

    public List<String> getStringsFromFiles() {
        try {
            if (Files.exists(this.inPath)) return Files.readAllLines(this.inPath);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> convertList(List<String> inLst, String regex){
        List<String> lst = new ArrayList<>();
        for(String str : inLst){
            lst.addAll(Arrays.stream(str.split(regex)).toList());
        }
        return lst;
    }

    public void saveListToFile(List<String> lst){
        try {
            Files.write(this.outPath, lst);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
