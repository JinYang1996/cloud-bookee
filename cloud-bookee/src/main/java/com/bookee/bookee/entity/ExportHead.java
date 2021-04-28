package com.bookee.bookee.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinyang
 * @date 2021/4/28 9:42 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportHead {
    @ExcelProperty(value = "租户", index = 0)
    private String tenant ;

    @ExcelProperty(value = "JPG", index = 1)
    private Integer JPG ;

    @ExcelProperty(value = "JPEG", index = 2)
    private Integer JPEG ;

    @ExcelProperty(value = "PNG", index = 3)
    private Integer PNG ;

    @ExcelProperty(value = "GIF", index = 4)
    private Integer GIF ;

    @ExcelProperty(value = "SVG", index = 5)
    private Integer SVG ;

    @ExcelProperty(value = "BMP", index = 6)
    private Integer BMP ;

    @ExcelProperty(value = "TIF", index = 7)
    private Integer TIF ;

    @ExcelProperty(value = "TIFF", index = 8)
    private Integer TIFF ;

    @ExcelProperty(value = "HEIC", index = 9)
    private Integer HEIC ;

    @ExcelProperty(value = "MP4", index = 10)
    private Integer MP4 ;

    @ExcelProperty(value = "AVI", index = 11)
    private Integer AVI;

    @ExcelProperty(value = "MKV", index = 12)
    private Integer MKV ;

    @ExcelProperty(value = "MGB", index = 13)
    private Integer MGB ;

    @ExcelProperty(value = "RMBV", index = 14)
    private Integer RMBV ;

    @ExcelProperty(value = "MOV", index = 15)
    private Integer MOV ;

    @ExcelProperty(value = "M4A", index = 16)
    private Integer M4A ;

    @ExcelProperty(value = "RM", index = 17)
    private Integer RM ;

    @ExcelProperty(value = "MPG", index = 18)
    private Integer MPG ;

    @ExcelProperty(value = "FLV", index = 19)
    private Integer FLV ;

    @ExcelProperty(value = "M4V", index = 20)
    private Integer M4V ;

    @ExcelProperty(value = "TS", index = 21)
    private Integer TS ;

    @ExcelProperty(value = "PDF", index = 22)
    private Integer PDF ;

    @ExcelProperty(value = "DOC", index = 23)
    private Integer DOC ;

    @ExcelProperty(value = "DOCX", index = 24)
    private Integer DOCX ;

    @ExcelProperty(value = "PPT", index = 25)
    private Integer PPT ;

    @ExcelProperty(value = "PPTX", index = 26)
    private Integer PPTX ;

    @ExcelProperty(value = "WPS", index = 27)
    private Integer WPS ;

    @ExcelProperty(value = "PAGES", index = 28)
    private Integer PAGES ;

    @ExcelProperty(value = "KEY", index = 29)
    private Integer KEY ;

    @ExcelProperty(value = "TXT", index = 30)
    private Integer TXT ;

    @ExcelProperty(value = "MP3", index = 31)
    private Integer MP3 ;

    @ExcelProperty(value = "WAV", index = 32)
    private Integer WAV ;

    @ExcelProperty(value = "XLS", index = 33)
    private Integer XLS ;

    @ExcelProperty(value = "XLSX", index = 34)
    private Integer XLSX ;

    @ExcelProperty(value = "CSV", index = 35)
    private Integer CSV ;

    @ExcelProperty(value = "NUMBERS", index = 36)
    private Integer NUMBERS ;

    @ExcelProperty(value = "PSD", index = 37)
    private Integer PSD ;

    @ExcelProperty(value = "PSB", index = 38)
    private Integer PSB ;

    @ExcelProperty(value = "AI", index = 39)
    private Integer AI ;

    @ExcelProperty(value = "SKETCH", index = 40)
    private Integer SKETCH ;

    @ExcelProperty(value = "C4D", index = 41)
    private Integer C4D;

    @ExcelProperty(value = "PS", index = 42)
    private Integer PS ;

    @ExcelProperty(value = "CORELDRAW", index = 43)
    private Integer CORELDRAW ;

    @ExcelProperty(value = "TTF", index = 44)
    private Integer TTF ;

    @ExcelProperty(value = "TTC", index = 45)
    private Integer TTC ;

    @ExcelProperty(value = "OTF", index = 46)
    private Integer OTF ;

    @ExcelProperty(value = "ZIP", index = 47)
    private Integer ZIP ;

    @ExcelProperty(value = "RAR", index = 48)
    private Integer RAR ;

    @ExcelProperty(value = "7Z", index = 49)
    private Integer Z ;

    @ExcelProperty(value = "GZ", index = 50)
    private Integer GZ;


}
