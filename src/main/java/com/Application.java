package com;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class Application {

    public static void main(String[] args){
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(args[0]);

        //count workbooks for number of pages
        CreatePages createPages = new CreatePages();
        List<Page> pages =  createPages.createPagesFromSheet(wb);

        // count columns and build page
        createPages.buildPageElements(wb, pages);

        //read excel file and fill in pages

        openfile.readFromFile(args[0], 0, 0);


    }
}
