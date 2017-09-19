package com;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class CreatePages {

    public List<Page> createPagesFromSheet(XSSFWorkbook wb) {
        int sheets = wb.getNumberOfSheets();

        List<Page> pages = new ArrayList<Page>();
        for (int i = 0; i < sheets; i++) {
            pages.add(new Page());
        }
        return pages;
    }

    public void buildPageElements(XSSFWorkbook wb, List<Page> pages) {
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            createThePageModel(wb.getSheetAt(i), page);
        }
    }

    private void createThePageModel(XSSFSheet xs, Page page) {
        for (int i = 0; i < xs.getLastRowNum(); i++) {
            page.createElement(xs.getRow(0).getCell(i).getStringCellValue());
        }
    }
}
