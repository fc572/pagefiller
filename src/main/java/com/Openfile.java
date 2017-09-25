package com;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Openfile {

    public XSSFWorkbook openFileAtLocation(String path) {
        XSSFWorkbook wb = null;
        // Specify the path of file
        File src = new File(path);
        // load file
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(src);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Load workbook
        try {
             wb = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


    public String getTheCellValueFromWorkBook(XSSFWorkbook wb, int sheet, int row, int cell) {
        try {
             Sheet sh1 = wb.getSheetAt(sheet);
            return sh1.getRow(row).getCell(cell).getStringCellValue();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String readFromFile(String path, int sheet, int row, int cell) {
        XSSFWorkbook wb = openFileAtLocation(path);
        return getTheCellValueFromWorkBook(wb, sheet, row, cell);
    }
}
