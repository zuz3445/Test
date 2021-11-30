import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v85.systeminfo.model.Size;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * @author YANikitina
 */
public class FNSTests {
    @Test
    public void regionTest() {
        open("https://www.nalog.gov.ru/");
        $(By.id("select2-ctl00_ctl00_ddlRegion_firstpage-container")).click();
        $(By.className("select2-search__field")).setValue("18 Удмуртская Республика").pressEnter();
    }

    @Test
    public void searchTest() throws InterruptedException {
        $(By.id("tb_main_search_f")).setValue("налоги").pressEnter();
        $(By.id("checkIndexAll")).isSelected();
        $(By.id("ctl00_ctl03_ctl02_MyRegion-styler")).isSelected();
       //$$("#ctl00_ctl03_ctl02_searchResultList").shouldHave(size(10));
        Thread.sleep(3000);
        $(By.id("checkIndexAll-styler")).click();
        $(By.id("documents-styler")).click();
        $(By.id("dt_news-styler")).click();
        $(By.className("form__buttons")).click();
        Thread.sleep(3000);

    }

    @Test
    public void testForum() throws IOException {
        $(By.linkText("Форум")).click();
        $(By.xpath("//*[@id=\"category_109\"]/div/div/table/tbody/tr[32]/td[2]")).click();
        $(By.className("last")).click();
        List<String> lines = Collections
                .singletonList(Arrays.toString(new ElementsCollection[]{$$("#forum_table > tbody")}));
        Files.write(Paths.get("lastPage.txt"), lines, StandardOpenOption.CREATE);

    }
}
