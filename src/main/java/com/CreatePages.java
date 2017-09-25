package com;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CreatePages {

    public List<Page> createPagesFromSheet(XSSFWorkbook wb) throws Throwable {
        int sheets = wb.getNumberOfSheets();

        List<Page> pages = new ArrayList<Page>();
        WebDriver driver = ChromeBrowser.buildChromeBrowser();
        List<WebElement> elements = new ArrayList<WebElement>();
        for (int i = 0; i < sheets; i++) {
            pages.add(new Page(driver, elements));
        }
        return pages;
    }

    public void buildPageElements(XSSFWorkbook wb, List<Page> pages) throws Throwable {
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            createThePageModel(wb.getSheetAt(i), page);
        }
    }

    private void createThePageModel(XSSFSheet xs, Page page) throws Throwable {
        page.openPageWithUrl(xs.getRow(0).getCell(1).getStringCellValue());
        for (int i = 1; i < xs.getLastRowNum(); i++) {
            String webElementName = xs.getRow(4).getCell(i).getStringCellValue();
            String cellValue = xs.getRow(5).getCell(i).getStringCellValue();
            System.out.println("webElementName " + webElementName + " cellValue " + cellValue);
            page.addElementsToThePage(webElementName, cellValue);
        }
    }
}
