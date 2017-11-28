package customkeywords

import javax.imageio.metadata.IIOMetadataFormatImpl.Element
import org.openqa.selenium.WebElement
import org.jsoup.select.Elements

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import org.jsoup.nodes.Document
import org.jsoup.Jsoup

import org.jsoup.select.Elements


public class CustomWebKeywords {

	private KeywordLogger logger = KeywordLogger.getInstance()

	
	@Keyword
	def getCellValue(TestObject to, Integer rowIndex, Integer columnIndex) {

		if (rowIndex < 1) {
			stepFailed("Row index is out bound")
		}
		WebElement webElement = WebUiBuiltInKeywords.findWebElement(to)
		Elements rows = getRows(webElement, "tbody")

		if ((rows == null) || (rows.size() == 0)) {
			stepFailed("This object does not have any row")
		}

		int rIndex = 1
		for (Element row : rows) {
			Elements columns = getColumns(row, "td")
			if (!columns.isEmpty()) {
				if (rIndex < rowIndex) {
					rIndex++
				}
				else if (rIndex == rowIndex) {
					if ((columnIndex < 1) || (columnIndex > columns.size())) {
						stepFailed("Column index is out bound")
					}
					Element cell = columns.get(columnIndex - 1)
					try {						
						return cell.text()
					} catch (NoSuchElementException ex) {
						stepFailed("This cell does not contain image")
					}
				}
			}
		}
		stepFailed("Row index is out bound")
	}
	
	def Elements getColumns(Element row, String tagName) {
		try {
			Elements selectedColumns = new Elements()
			Elements columns = row.getElementsByTag(tagName)
			for (Element column : columns) {
				if (column.parent().equals(row)) {
					selectedColumns.add(column)
				}
			}
			return selectedColumns
		} catch (Exception e) {
			stepFailed(e.getMessage())
		}
	}
	
	@Keyword
	def getTableRowCount(TestObject to) {
		try {
			WebElement webElement = WebUiBuiltInKeywords.findWebElement(to)
			Elements rows = getRows(webElement, "tbody")
			return rows.size()
			
		} catch (Exception e) {
			stepFailed("Cannot get table row count. Exception error: " + e.getMessage())
		}
	}

	
	def Elements getRows(WebElement webElement, String tagName) {
		String html = webElement.getAttribute("outerHTML")
		Document doc = Jsoup.parse(html)

		Element baseTag = null
		try {
			baseTag = doc.getElementsByTag(tagName).first()
		} catch (Exception e) {
			stepFailed("This object does not have '"+ tagName +"' tag")
		}

		try {
			Elements selectedRows = new Elements()
			Elements rows = baseTag == null ? doc.getElementsByTag("tr") : baseTag.getElementsByTag("tr")
			for (Element row : rows) {
				if (row.parent().equals(baseTag)) {
					selectedRows.add(row)
				}
			}
			return selectedRows
		} catch (Exception e) {
			stepFailed(e.getMessage())
		}
	}
	
	
	@Keyword
	def getTextAtCell(WebElement tableWebElement, Integer rowIndex, Integer columnIndex) {
		if (rowIndex < 1) {
			stepFailed("Row index is out bound")
		}		
		Elements rows = getRows(tableWebElement, "tbody")
		if ((rows == null) || (rows.size() == 0)) {
			stepFailed("This object does not have any row")
		}
		logger.logInfo('Getting text at row #'+ rowIndex + ' and column #' + columnIndex)
		int rIndex = 1
		for (Element row : rows) {
			Elements columns = getColumns(row, "td")
			if (!columns.isEmpty()) {
				if (rIndex < rowIndex) {
					rIndex++
				}
				else if (rIndex == rowIndex) {
					if ((columnIndex < 1) || (columnIndex > columns.size())) {
						stepFailed("Column index is out bound")
					}
					String textValue = ((Element) columns.get(columnIndex - 1)).text()
					logger.logPassed('Text value is ' + textValue)
					return textValue
				}
			}
		}
		stepFailed("Row index is out bound")
	}
}
