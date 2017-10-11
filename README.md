This project consist in an application that read an excel file and puts the values in the specified fields on the
specified URL.

Let's say you have a form that needs to be filled in over and over.
Go to the URL and paste it in the URL field on the spreadsheet
Then on the page, find out the identifier of the field you want to fill in
on the spreadsheet write what indentifier you are using and put the value of the identifier
in the cell below that.

If you have more than one page on your form, sheet 1 is for page 1, sheet 2 is for page 2 and so on.

put the excel file and the jar on the same folder and type java -jar exceltransposer-1.0-SNAPSHOT-jar-with-dependencies.jar ./nameOfTheExcelFile.xlsx
also on the same folder create a directory called chromeDriverHome and put in the chromedriver (or other driver? I did not try this) that you want to use

The browser session will be left open, as this is suppose to be a tool that helps fill in form. Then you decide what to do with the form.