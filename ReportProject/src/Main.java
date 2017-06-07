import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

import java.io.IOException;

/**
 * Created by mz on 28/05/17.
 */
public class Main {
    public static void main(String[] args) {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDPageContentStream content = null;
        try {
            content = new PDPageContentStream(doc, page);
            content.beginText();
            content.newLineAtOffset(250, 750);
            content.setFont(PDType1Font.HELVETICA, 26);
            content.showText("Registration form");
            content.endText();

            content.close();
            doc.save("PDFTest.pdf");
            doc.close();
            System.out.println("File created in: " + System.getProperty("user.dir"));
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            try {
                doc.close();
                if(content != null) {
                    content.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
