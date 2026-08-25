package com.southwind.util;

import com.southwind.vo.PersonVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    public static String ExpPersonInfo(List<PersonVO> info, String path){
        POIFSFileSystem fs = null;
        int headRow = 2;
        String descfile = null;
        try {
            //复制文件
            String srcfile = path + "personInfo.xls";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = format.format(date);
            descfile = dateStr + ".xls";
            try {
                FileInputStream fis = new FileInputStream(srcfile);
                FileOutputStream fos = new FileOutputStream(path+descfile);
                byte [] buffer = new byte[1024*4];
                while(fis.read(buffer) != -1){
                    fos.write(buffer);
                }
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //写数据
            fs = new POIFSFileSystem(new FileInputStream(path + descfile));
            FileOutputStream fos = new FileOutputStream(path + descfile);
            HSSFWorkbook wb1 = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb1.getSheetAt(0);
            int size = info.size();
            int col = 0;
            HSSFCellStyle style = wb1.createCellStyle();
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            for(int i = 0;i < size;i++){
                col = 0;
                PersonVO p = info.get(i);
                HSSFRow row = sheet.createRow(i+headRow);
                HSSFCell cell = null;
                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getPersonId());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getCommunityName());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getTermName());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getHouseNo());
                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getUserName());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getSex());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getMobile());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getPersonType());

                cell = row.createCell(col++);
                cell.setCellStyle(style);
                cell.setCellValue(p.getRemark());

            }
            wb1.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return descfile;
    }

}
