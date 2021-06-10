package batchlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jberet.support.io.JsonItemReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import businessObjects.OpenInterest;

@Named
public class SimpleBatchLet extends AbstractBatchlet {
 
	 @Inject StepContext stepContext;
	 
	 @Inject JobContext jobContext;
	 
    @Override
    public String process() {
    	String source = stepContext.getProperties().getProperty("source");
        String destination = stepContext.getProperties().getProperty("destination");
        try {
        	System.out.println(source);
        	System.out.println(destination);
        	Gson g = new Gson();
        	
        	List<File> files = Files.list(Paths.get(source))
            .filter(Files::isRegularFile) .map(Path::toFile)
            .collect(Collectors.toList());
        	for(int l=0;l <files.size();l++) {
        		String fileNameWithoutExt = FilenameUtils.getBaseName(files.get(l).getName());
        	BufferedReader br = new BufferedReader(
        		     new FileReader(files.get(l)));
        	OpenInterest o = g.fromJson(br, OpenInterest.class);
        	
        	// Creating Workbook instances
	        Workbook workbook = new XSSFWorkbook();
	  
	        // An output stream accepts output bytes and sends them to sink.
	        OutputStream fileOut = new FileOutputStream(destination+fileNameWithoutExt+".xls");
	        Sheet sheet = workbook.createSheet("DataHC");
	        sheet.setColumnWidth(0, 6000);
	        sheet.setColumnWidth(1, 6000);
	        for(int i=0;i< o.getDatahc().size();i++)
			{
	        	List<Double> lst = o.getDatahc().get(i);
	        	Row field = sheet.createRow(i);
	        	for(int j =0; j < lst.size(); j++)
	        	{
	        		Double d = lst.get(j);
	        		Cell fieldCell = field.createCell(j);
		        	fieldCell.setCellValue(d);
	        	}
			}
	        sheet.setFitToPage(true);
	        sheet.shiftRows(0, o.getDatahc().size(), 1);
	        Row field0 = sheet.createRow(0);
	        Cell fieldCell0 = field0.createCell(9);
        	fieldCell0.setCellValue(o.getDatahc().size());
        	 Cell fieldCell02 = field0.createCell(10);
        	 fieldCell02.setCellValue(o.getDatahc().get(0).get(0));
         	 Cell fieldCell03 = field0.createCell(11);
         	fieldCell03.setCellValue(o.getDatahc().get(o.getDatahc().size()-1).get(0));
	        
	    	workbook.write(fileOut);
		      //closing the Stream  
		        fileOut.close();  
		        //closing the workbook  
		        workbook.close();
        	}
            System.out.println("File copied!");
            return 	BatchStatus.COMPLETED.toString();
        }
        catch(Exception e)
        {
        	 e.printStackTrace();
        }
        return BatchStatus.FAILED.toString();
    }
}