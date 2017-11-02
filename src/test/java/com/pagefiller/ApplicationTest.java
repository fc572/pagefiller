package com.pagefiller;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApplicationTest {

    final private String TESTDATAXLSX = "src/test/resources/testData/testData.xlsx";

    @Test
    public void findWhichOsIsRunningMe() {
        System.out.println(System.getProperty("os.name"));
    }

    @Test
    public void isFileOpen(){
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        Assert.assertFalse(openfile.readFromFile(TESTDATAXLSX, 0, 0,0).isEmpty());
        System.out.println(openfile.readFromFile(TESTDATAXLSX, 0, 0, 0));
    }


    @Test
    public void doIhaveTheCorrectNumberOfPages() throws Throwable {
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(TESTDATAXLSX);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);

        Assert.assertTrue("There are this number of pages " + pages.size(), pages.size() == 2);

        pages.get(0).close();
    }

    @Test
    public void whatIsTheNumberOfColumnsInTheSheet() {
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(TESTDATAXLSX);
        System.out.println("getLastCellNum " + wb.getSheetAt(0).getRow(3).getLastCellNum());
    }

    @Test
    public void haveIGotElements() throws Throwable {
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(TESTDATAXLSX);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);
        createPages.buildPageElements(wb, pages);

        Assert.assertTrue(pages.size() > 0);
        pages.get(0).close();
    }
}