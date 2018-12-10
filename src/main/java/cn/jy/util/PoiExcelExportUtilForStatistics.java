package cn.jy.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出excel 工具类
 *
 * @author
 * @date 2018/10/24
 */
@Slf4j
public class PoiExcelExportUtilForStatistics<T> {
    /**
     * excel导出名称
     */
    private String fileName;
    /**
     * excel 表头
     */
    private String[] heads;
    /**
     * excel 列
     */
    private String[] cols;
    /**
     * excel 表头
     */
    private String[] heads_total;
    /**
     * excel 列
     */
    private String[] cols_total;
    /**
     * 设置数值型的列 从0开始计数
     */
    private int[] numerics;
    /**
     * list集合
     */
    private List<T> list;
    /**
     * 输出流
     */
    private OutputStream out;

    /**
     * 构造函数
     */
    public PoiExcelExportUtilForStatistics(String fileName, String[] heads, String[] cols,String[] heads_total, String[] cols_total, List<T> list, OutputStream out) {
        this.fileName = fileName;
        this.heads = heads;
        this.cols = cols;
        this.heads_total = heads_total;
        this.cols_total = cols_total;
        this.list = list;
        this.out = out;
    }

    // 构造函数 带数字类型
    public PoiExcelExportUtilForStatistics(String fileName, String[] heads, String[] cols,String[] heads_total, String[] cols_total, List<T> list, int[] numerics, OutputStream out) {
        this.fileName = fileName;
        this.heads = heads;
        this.cols = cols;
        this.heads_total = heads_total;
        this.cols_total = cols_total;
        this.list = list;
        this.numerics = numerics;
        this.out = out;
    }

    public void exportExcel() {
        // 创建一个excel对象
        HSSFWorkbook hssfworkbook = new HSSFWorkbook();

        //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
        //获取列头样式对象
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(hssfworkbook);
        //单元格样式对象
        HSSFCellStyle style = this.getStyle(hssfworkbook);
        for (int i = 0; i <= (list.size() / 65535); i++) {
            // 工作表
            HSSFSheet hssfsheet = hssfworkbook.createSheet();

            // 工作表名称
            hssfworkbook.setSheetName(i, fileName.replace(".xls", "") + "(第" + (i + 1) + "页)");
            int beginRows = 65535 * i+3;
            int endRows = (list.size() > 65535 * (i + 1)) ? 65535 * (i + 1) - 1 : list.size() - 1;

            //创建一行heads_total
            HSSFRow hssfrowHead_totalhead = hssfsheet.createRow(0);
            if (heads_total != null && heads_total.length > 0) {
                for (int u = 0; u < heads_total.length; u++) {
                    HSSFCell hssfcell = hssfrowHead_totalhead.createCell(u, Cell.CELL_TYPE_STRING);
                    hssfcell.setCellValue(heads_total[u]);
                    hssfcell.setCellStyle(columnTopStyle);
                }
            }
            //创建一行cols_total
            HSSFRow hssfrowHead_totalcols = hssfsheet.createRow(1);
            if (cols_total != null && cols_total.length > 0) {
                for (int r = 0; r < cols_total.length; r++) {
                    HSSFCell hssfcell = hssfrowHead_totalcols.createCell(r, Cell.CELL_TYPE_STRING);
                    hssfcell.setCellValue(cols_total[r]);
                    hssfcell.setCellStyle(style);
                }
            }
            //创建一行空数据
            hssfsheet.createRow(2);

            HSSFRow hssfrowHead = hssfsheet.createRow(3);
            // 输出excel 表头
            if (heads != null && heads.length > 0) {
                for (int h = 0; h < heads.length; h++) {
                    HSSFCell hssfcell = hssfrowHead.createCell(h, Cell.CELL_TYPE_STRING);
                    hssfcell.setCellValue(heads[h]);
                    hssfcell.setCellStyle(columnTopStyle);
                }
            }
            // 要设置数值型 列表
            // 是否是数值型
            boolean isnum = false;
            // 输出excel 数据
            for (int curRow = beginRows; curRow <= endRows; curRow++) {
                // 获取数据
                BeanToMapUtil<T> btm = new BeanToMapUtil<>();
                Map<String, Object> hm = btm.getMap(list.get(curRow));
                // 创建excel行 表头1行 导致数据行数 延后一行
                HSSFRow hssfrow = hssfsheet.createRow(curRow % 65535 + 1);
                // 读取数据值
                for (int k = 0; k < cols.length; k++) {
                    HSSFCell hssfcell = hssfrow.createCell(k);
                    // hssfcell.setCellValue(hm.get(cols[k])==null?"":hm.get(cols[k]).toString());
                    isnum = false;
                    if (null != numerics) {
                        for (int z = 0; z < numerics.length; z++) {
                            if (numerics[z] == k) {
                                isnum = true;
                                break;
                            }
                        }
                    }

                    if (isnum) {
                        if (k == 0) {
                            hssfcell.setCellValue(curRow + 1);
                        } else {
                            if (hm.get(cols[k]) != null || !hm.get(cols[k]).equals("")) {
                                hssfcell.setCellValue(Double.parseDouble(
                                        hm.get(cols[k]) == null ? "" : hm.get(cols[k]).toString().replace(",", "")));
                            }
                        }
                    } else {
                        hssfcell.setCellValue(hm.get(cols[k]) == null ? "" : hm.get(cols[k]).toString());
                    }
                    hssfcell.setCellStyle(style);
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < heads.length; colNum++) {
                int columnWidth = hssfsheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < hssfsheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (hssfsheet.getRow(rowNum) == null) {
                        currentRow = hssfsheet.createRow(rowNum);
                    } else {
                        currentRow = hssfsheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    hssfsheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    hssfsheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }
        }
        // excel生成完毕，写到输出流
        try {
            hssfworkbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("poi:error==" + e);
            e.printStackTrace();
        }
    }

    /**
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    /**
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //数据格式
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("##0"));

        return style;
    }
}