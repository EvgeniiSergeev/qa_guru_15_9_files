package guru.qa.model;

        import com.codeborne.pdftest.PDF;
        import com.codeborne.xlstest.XLS;
        import com.opencsv.CSVReader;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;

        import java.io.*;
        import java.util.List;
        import java.util.Objects;
        import java.util.zip.*;

        import static org.assertj.core.api.Assertions.assertThat;


    public class HomeWork {


    ClassLoader cl = HomeWork.class.getClassLoader();
    String zipFile = "TestFiles.zip";


    @DisplayName("ZipCsv test")
    @Test
    void zipCsvTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/"+ zipFile))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(1);
                        assertThat(row[0]).isEqualTo("kolya");
                        assertThat(row[1]).isEqualTo("bokov");
                    }
                }
            }
        }
    }

    @DisplayName("ZipPdf test")
    @Test
    void pdfTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/" + zipFile))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        System.out.println("");
                        assertThat("Kolobok.pdf").contains("Kolobok");
                    }
                }
            }
        }
    }


    @DisplayName("ZipXlsx test")
    @Test
    void zipXlsxTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/"+ zipFile))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(1)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("January");
                    }
                }
            }
        }
    }



}