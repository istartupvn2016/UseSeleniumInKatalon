package com.table.keywords

import javax.imageio.metadata.IIOMetadataFormatImpl.Element

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.util.List

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords


public class CustomWebKeywords {

	private KeywordLogger logger = KeywordLogger.getInstance()
	
	
	@Keyword
	def getCellValue(TestObject to, Integer rowIndex, Integer columnIndex) {

		if (rowIndex < 1) {
			print("Row index is out bound")
		}
		WebElement webElement = WebUiBuiltInKeywords.findWebElement(to)
		
		def rows = webElement.findElements(By.tagName("tr"))
		
		def counts = ((Elements)rows).size()
		
		println('Size = ' + counts.toString())
		
		def ele = rows[2]
		
		def cols = ele.findElements(By.tagName("td"))
		
		def countcols = ((Elements)cols).size()
		
		def eleCol = cols[2]
		
		def text = eleCol.getText() 
		
		println('>>>>>>>  Show me : ' + text)
	
	}	
	
}
