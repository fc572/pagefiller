package com.pagefiller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class CreatePages {

    List<Page> createPagesFromSheet(XSSFWorkbook wb) throws Throwable {
        int sheets = wb.getNumberOfSheets();

        List<Page> pages = new ArrayList<Page>();
        WebDriver driver = ChromeBrowser.buildChromeBrowser();
        List<WebElement> elements = new ArrayList<WebElement>();
        for (int i = 0; i < sheets; i++) {
            pages.add(new Page(driver, elements));
        }
        return pages;
    }

    void buildPageElements(XSSFWorkbook wb, List<Page> pages) throws Throwable {
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            createThePageModel(wb.getSheetAt(i), page);
        }
    }

    private void createThePageModel(XSSFSheet xs, Page page) throws Throwable {
        String url = xs.getRow(1).getCell(0).getStringCellValue().trim();
        if(!url.isEmpty()){
            page.openPageWithUrl(url);
        }
        int numberOfRows = xs.getLastRowNum();

        for (int i = 1; i <= numberOfRows; i++) {
            String selectorType = xs.getRow(i).getCell(2).getStringCellValue();
            String webElementName = xs.getRow(i).getCell(3).getStringCellValue();
            String cellValue = xs.getRow(i).getCell(4).getStringCellValue();
            page.addElementToThePage(selectorType, webElementName, cellValue);
        }
    }
}
