package com.learntest.pdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.pdfcleanup.autosweep.PdfAutoSweep;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author yanglin
 * @date 2020/12/24 15:54
 */
public class PdfTest {

    private final static String src = "D:\\软件安装包";

    public static void main(String[] args) {
        try (PdfDocument pdf = new PdfDocument(new PdfReader(src+"\\Pages from Alice-redact.pdf"),new PdfWriter(new File(src,"redact.pdf")))){
            RegexBasedCleanupStrategy alice = new RegexBasedCleanupStrategy(Pattern.compile("By", Pattern.CASE_INSENSITIVE)).setRedactionColor(ColorConstants.PINK);
            PdfAutoSweep pdfAutoSweep = new PdfAutoSweep(alice);
            pdfAutoSweep.cleanUp(pdf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
