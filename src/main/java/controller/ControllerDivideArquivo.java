package controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import exception.ExceptionArquivoJaExiste;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import util.FileUtils;

public abstract class ControllerDivideArquivo {
    
    private final File fileOriginal;
    private final char separadorCSV;
    private File fileAtual;
    private ICSVWriter fileWriter;
    private int indexFile;
    private Date dataDivisao;

    public ControllerDivideArquivo(File fileOriginal, char separadorCSV) {
        this.fileOriginal = fileOriginal;
        this.separadorCSV = separadorCSV;
        this.indexFile = 1;
    }
    
    protected abstract boolean atingiuLimite();
    
    protected abstract void atualizaParametrosLimitador();
    
    protected abstract void resetaParametrosLimitador();
    
    public void divideArquivo() throws FileNotFoundException, IOException, ExceptionArquivoJaExiste, CsvValidationException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(this.separadorCSV).build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(this.fileOriginal)).withCSVParser(csvParser).build();
        
        String[] header = reader.readNext();
        if (header != null) {
            this.initNewFile(header);
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (this.atingiuLimite()) {
                    this.closeFileAndInitNew(header);
                    this.resetaParametrosLimitador();
                }
                
                this.fileWriter.writeNext(line);
                
                this.atualizaParametrosLimitador();
            }
            
            this.fileWriter.close();
            reader.close();
        }
    }

    private void initNewFile(String[] header) throws IOException, ExceptionArquivoJaExiste {
        String baseName = this.fileOriginal.getName().replace(".csv", "");
        String folderName = baseName + " particionado (" + (new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(this.getDataDivisao())) + ")";
        String newFileName = baseName + "_" + this.indexFile++ + ".csv";
        
        new File(this.fileOriginal.getParent() + "/" + folderName).mkdir();
        
        this.fileAtual = FileUtils.createNewFile(this.fileOriginal.getParent() + "/" + folderName + "/" + newFileName);
        
        this.fileWriter = new CSVWriterBuilder(new FileWriter(this.fileAtual)).withSeparator(this.separadorCSV).build();
        this.fileWriter.writeNext(header);
    }
    
    private void closeFileAndInitNew(String[] header) throws IOException, ExceptionArquivoJaExiste {
        this.fileWriter.close();
        this.initNewFile(header);
    }
    
    private Date getDataDivisao() {
        if (this.dataDivisao == null) {
            this.dataDivisao = Calendar.getInstance().getTime();
        }
        return this.dataDivisao;
    }
    
    public File getFileOriginal() {
        return fileOriginal;
    }

    public File getFileAtual() {
        return fileAtual;
    }
    
}
