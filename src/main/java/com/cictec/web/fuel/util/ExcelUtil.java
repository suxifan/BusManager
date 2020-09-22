package com.cictec.web.fuel.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
/**
 * 
 * @author hehui
 *
 */
public class ExcelUtil {
	
	private static final Logger LOG = Logger.getLogger(ExcelUtil.class);
	
	public static void exportToExcel(String filePath, List<String> titles, List<List<String>> datas) {
		
		WritableWorkbook book = null;
		try {
			File excelFile = new File(filePath);
			if(!excelFile.exists()) {
				excelFile.createNewFile();
			}
			book = Workbook.createWorkbook(excelFile);
			WritableSheet sheet = book.createSheet("汇总表", 0);
			
            // 设置标题格式
            WritableFont wfTitle = new jxl.write.WritableFont(
               WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false);
            WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
            // 设置水平对齐方式
            wcfTitle.setAlignment(Alignment.CENTRE);
            // 设置垂直对齐方式
            wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 设置是否自动换行
            wcfTitle.setWrap(true);
            
			int titleColIndex = 0;
			for (String title : titles) {
				Label titleLabel = new Label(titleColIndex, 0, title, wcfTitle);
				sheet.addCell(titleLabel);
				titleColIndex++;
			}
			int rowIndex = 1;
			int colIndex = 0;
			for (List<String> rowData : datas) {
				colIndex = 0;
				for (String cellData : rowData) {
					Label cellLabel = new Label(colIndex, rowIndex, cellData);
					sheet.addCell(cellLabel);
					colIndex++;
				}
				rowIndex++;
			}
			book.write();
			
		} catch (Exception e) {
			LOG.error("导出excel文件：" + filePath + " 失败！" + e.toString());
		} finally {
			if(book != null) {
				try {
					book.close();
				} catch (Exception e) {
					LOG.error("关闭工作簿失败！" + e.toString());
				}
			}
		}
		
	}
	
    /**
    * 插入图片到EXCEL
    * 
    * @param picSheet sheet
    * @param pictureFile 图片file对象
    * @param cellRow 行数
    * @param cellCol 列数
    * @throws Exception 例外
    */
   public static void addPictureToExcel(WritableSheet picSheet, File pictureFile, int cellRow, int cellCol)
       throws Exception {
       // 开始位置
       double picBeginCol = cellCol - 1;
       double picBeginRow = cellRow - 1;
       // 图片时间的高度，宽度
       double picCellWidth = 0.0;
       double picCellHeight = 0.0;
       // 读入图片
       BufferedImage picImage = ImageIO.read(pictureFile);
       // 取得图片的像素高度，宽度
       int picWidth = picImage.getWidth();
       int picHeight = picImage.getHeight();
       
       // 计算图片的实际宽度
       int picWidth_t = picWidth * 32;
       for (int x = 0; x < 1234; x++) {
           int bc = (int) Math.floor(picBeginCol + x);
           // 得到单元格的宽度
           int v = picSheet.getColumnView(bc).getSize();
           double offset0_t = 0.0;
           if (0 == x)
               offset0_t = (picBeginCol - bc) * v;
           if (0.0 + offset0_t + picWidth_t > v) {
               // 剩余宽度超过一个单元格的宽度
               double ratio_t = 1.0;
               if (0 == x) {
                   ratio_t = (0.0 + v - offset0_t) / v;
               }
               picCellWidth += ratio_t;
               picWidth_t -= (int) (0.0 + v - offset0_t);
           } else { //剩余宽度不足一个单元格的宽度
               double ratio_r = 0.0;
               if (v != 0)
                   ratio_r = (0.0 + picWidth_t) / v;
               picCellWidth += ratio_r;
               break;
           }
       }        
       // 计算图片的实际高度
       int picHeight_t = picHeight * 15;
       for (int x = 0; x < 1234; x++) {
           int bc = (int) Math.floor(picBeginRow + x);
           // 得到单元格的高度
           int v = picSheet.getRowView(bc).getSize();
           double offset0_r = 0.0;
           if (0 == x)
               offset0_r = (picBeginRow - bc) * v;
           if (0.0 + offset0_r + picHeight_t > v) {
               // 剩余高度超过一个单元格的高度
               double ratio_q = 1.0;
               if (0 == x)
                   ratio_q = (0.0 + v - offset0_r) / v;
               picCellHeight += ratio_q;
               picHeight_t -= (int) (0.0 + v - offset0_r);
           } else {//剩余高度不足一个单元格的高度
               double ratio_m = 0.0;
               if (v != 0)
                   ratio_m = (0.0 + picHeight_t) / v;
               picCellHeight += ratio_m;
               break;
           }
       }
       //生成一个图片对象。
       WritableImage image = new WritableImage(picBeginCol, picBeginRow,
               picCellWidth, picCellHeight, pictureFile);
       // 把图片插入到sheet
       picSheet.addImage(image);
   }
	
}
