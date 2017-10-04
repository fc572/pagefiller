package com.pagefiller;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ApplicationTest {

    @Test
    public void isFileOpen(){
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        Assert.assertFalse(openfile.readFromFile(path, 0, 0,0).isEmpty());
        System.out.println(openfile.readFromFile(path, 0, 0, 0));
    }


    @Test
    public void doIhaveTheCorrectNumberOfPages() throws Throwable {
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(path);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);

        Assert.assertTrue("There are this number of pages " + pages.size(), pages.size() == 2);

        pages.get(0).close();
    }

    @Test
    public void whatIsTheNumberOfColumnsInTheSheet() {
        String path = "src/test/resources/testData/new_customers.xlsx";
        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(path);
        System.out.println("getLastCellNum " + wb.getSheetAt(0).getRow(3).getLastCellNum());
    }

    @Test
    public void haveIGotElements() throws Throwable {
        String path = "src/test/resources/testData/new_customers.xlsx";

        Openfile openfile = new Openfile();
        XSSFWorkbook wb = openfile.openFileAtLocation(path);

        CreatePages createPages = new CreatePages();
        List<Page> pages = createPages.createPagesFromSheet(wb);
        createPages.buildPageElements(wb, pages);

        for (Page page: pages) {
            List<WebElement> elements = page.getElements();

            for (WebElement elem : elements) {
                System.out.println("PAGE ELEM " + elem.toString());
            }
        }

        Assert.assertTrue(pages.size() > 0);
        pages.get(0).close();
    }
}