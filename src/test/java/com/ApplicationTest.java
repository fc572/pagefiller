package com;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApplicationTest {

    @Test
    public void isFileOpen(){
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        Assert.assertFalse(openfile.readFromFile(path, 0,0).isEmpty());
        System.out.println(openfile.readFromFile(path, 0, 0));
    }


    @Test
    public void doIhaveTheCorrectNumberOfPages() throws Throwable {
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(path);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);

        Assert.assertTrue("There are this number of pages " + pages.size(), pages.size() == 2);
    }

    @Test
    public void haveIGotElements() throws Throwable {
        String path = "src/main/resources/data/new_customers.xlsx";
        String url = "file:///Users/44022649/Desktop/varioous/tryHtml1.html";

        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(path);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);
        createPages.buildPageElements(url, wb, pages);

        System.out.println(pages.get(0));

    }
}