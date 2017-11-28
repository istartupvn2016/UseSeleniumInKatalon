

import com.kms.katalon.core.testobject.TestObject

import java.lang.Integer

import org.openqa.selenium.WebElement


def static "customkeywords.CustomWebKeywords.getCellValue"(
    	TestObject to	
     , 	Integer rowIndex	
     , 	Integer columnIndex	) {
    (new customkeywords.CustomWebKeywords()).getCellValue(
        	to
         , 	rowIndex
         , 	columnIndex)
}

def static "customkeywords.CustomWebKeywords.getTableRowCount"(
    	TestObject to	) {
    (new customkeywords.CustomWebKeywords()).getTableRowCount(
        	to)
}

def static "customkeywords.CustomWebKeywords.getTextAtCell"(
    	WebElement tableWebElement	
     , 	Integer rowIndex	
     , 	Integer columnIndex	) {
    (new customkeywords.CustomWebKeywords()).getTextAtCell(
        	tableWebElement
         , 	rowIndex
         , 	columnIndex)
}

def static "com.table.keywords.CustomWebKeywords.getCellValue"(
    	TestObject to	
     , 	Integer rowIndex	
     , 	Integer columnIndex	) {
    (new com.table.keywords.CustomWebKeywords()).getCellValue(
        	to
         , 	rowIndex
         , 	columnIndex)
}
