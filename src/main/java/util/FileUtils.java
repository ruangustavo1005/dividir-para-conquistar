package util;

import exception.ExceptionArquivoJaExiste;
import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
    
    public static File createNewFile(String fileName) throws IOException, ExceptionArquivoJaExiste {
        File file = new File(fileName);
        if (file.createNewFile()) {
            return file;
        }
        else {
            throw new ExceptionArquivoJaExiste("Arquivo " + fileName + " já existe");
        }
    }
    
}
