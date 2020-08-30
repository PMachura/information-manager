package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

//PMa todo remove - only for test
public class JsoupExtractionTest {

    public static void main(String[] args) throws IOException {
        File input = new File("information-manager-domain/src/main/resources/test.html");
        System.out.println(input.getAbsolutePath());
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements allElements = doc.getAllElements();
        allElements.forEach(element -> System.out.println("ELEMENT \n " + element + "\n"));
        doc.getElementsByTag("kupa").forEach(element -> System.out.println("KUPA ELEMENT \n " + element + "\n"));
    }
}
