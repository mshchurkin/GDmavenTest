package mshchurkin.Backend;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

@RunWith(Arquillian.class)
public class JsonLogicsTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(JsonLogics.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private JsonLogics jsonLogics =new JsonLogics();
    private APIWorker apiWorker=APIWorker.getInstance();

    @Before
    public void authorize() throws IOException {
        apiWorker.executeAuthorization();
    }

    @Test
    public void checkIfTestsWork(){
        Assert.assertEquals("check","check");
    }

    @Test
    public void testGettingString() throws Exception {

        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/679919");
        StringBuilder sb = jsonLogics.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(sb.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        JsonObject resObj= jsonLogics.getJsonArray(jsonObject,"NAME");
        String expected=String.valueOf(1270);
        String actual = resObj.getJsonString("value").getString();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGettingNames()throws Exception{
        ArrayList<String> expected=new ArrayList<>();
        expected.add("1270");
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680032");
        StringBuilder sbGot = jsonLogics.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(sbGot.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();
        JsonArray tempArr= jsonLogics.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values");
        ArrayList<String> actual = jsonLogics.getNames(tempArr,"rowId");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGettingArray()throws Exception{
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680032");
        StringBuilder sbGot = jsonLogics.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(sbGot.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();

        JsonObject actual= jsonLogics.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values").getJsonObject(0);

        JsonReader expected = Json.createReader(new StringReader("{\n" +
                "            \"formInstId\": 680032,\n" +
                "            \"rowId\": 679919,\n" +
                "            \"pokId\": 679929,\n" +
                "            \"nvalue\": 13018261000\n" +
                "          }"));
        JsonObject jsonObjectCheck=expected.readObject();
        expected.close();
        Assert.assertEquals(jsonObjectCheck,actual);
    }

    @Test
    public void testGettingColumnNames()throws Exception{
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680031");
        String expected="[{ field: \"ID\"},{ field: \"1\", title: \"Наименование\"},{ field: \"2\" , title: \"Значение\"}]";
        String actual=jsonLogics.getColumnNames();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGettingFinalJson()throws Exception{
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680632");
        String actual=jsonLogics.getResultJsonArray();
        String expected="[{ \"ID\":  0, \"1\":  \"Краткосрочная кредиторская задолженность\",\"2\":  540000 }, { \"ID\":  1, \"1\":  \"ИТОГО по разделу 5_\",\"2\":  9680000 }, { \"ID\":  2, \"1\":  \"Нераспределенная прибыль (непокрытый убыток)\",\"2\":  600000 }, { \"ID\":  3, \"1\":  \"Запасы\",\"2\":  9000 }, { \"ID\":  4, \"1\":  \"Прочие долгосрочные обязательства\",\"2\":  1500000 }, { \"ID\":  5, \"1\":  \"Итого по разделу I_\",\"2\":  7400000 }, { \"ID\":  6, \"1\":  \"Отложенные налоговые обязательства\",\"2\":  62380000 }, { \"ID\":  7, \"1\":  \"Финансовые вложения\",\"2\":  700000 }, { \"ID\":  8, \"1\":  \"Просроченная дебиторская задолженность\",\"2\":  5000000 }, { \"ID\":  9, \"1\":  \"Основные средства_\",\"2\":  10000 }, { \"ID\":  10, \"1\":  \"Нематериальные поисковые активы\",\"2\":  30000000 }, { \"ID\":  11, \"1\":  \"Добавочный капитал (без переоценки)\",\"2\":  1590000 }, { \"ID\":  12, \"1\":  \"Собственные акции, выкупленные у акционеров\",\"2\":  9000000 }, { \"ID\":  13, \"1\":  \"Финансовые вложения (за исключением денежных эквивалентов)\",\"2\":  4000000 }, { \"ID\":  14, \"1\":  \"ИТОГО ПАССИВЫ_\",\"2\":  80000 }, { \"ID\":  15, \"1\":  \"Итого активы_\",\"2\":  67879000 }, { \"ID\":  16, \"1\":  \"Прочие оборотные активы\",\"2\":  67879000 }, { \"ID\":  17, \"1\":  \"Краткосрочные заемные средства\",\"2\":  1029000 }, { \"ID\":  18, \"1\":  \"ИТОГО по разделу 4_\",\"2\":  470000 }, { \"ID\":  19, \"1\":  \"Прочие краткосрочные обязательства\",\"2\":  8930000 }, { \"ID\":  20, \"1\":  \"Нематериальные активы\",\"2\":  70000 }, { \"ID\":  21, \"1\":  \"Доходы будущих периодов\",\"2\":  10200000 }, { \"ID\":  22, \"1\":  \"Итого по разделу 3_\",\"2\":  8000000 }, { \"ID\":  23, \"1\":  \"Резервный капитал\",\"2\":  49269000 }, { \"ID\":  24, \"1\":  \"Налог на добавленную стоимость по приобретенным ценностям\",\"2\":  5000000 }, { \"ID\":  25, \"1\":  \"Оценочные обязательства\",\"2\":  190000 }, { \"ID\":  26, \"1\":  \"Долгосрочные заемные средства\",\"2\":  380000 }, { \"ID\":  27, \"1\":  \"Прочие внеоборотные активы\",\"2\":  450000 }, { \"ID\":  28, \"1\":  \"Материальные поисковые активы\",\"2\":  12000000 }, { \"ID\":  29, \"1\":  \"Переоценка внеоборотных активов\",\"2\":  790000 }, { \"ID\":  30, \"1\":  \"Уставный капитал\",\"2\":  1260000 }, { \"ID\":  31, \"1\":  \"Результаты исследований и разработок\",\"2\":  38000000 }, { \"ID\":  32, \"1\":  \"Дебиторская задолженность\",\"2\":  2800000 }, { \"ID\":  33, \"1\":  \"ИТОГО по разделу 2_\",\"2\":  1030000 }, { \"ID\":  34, \"1\":  \"Денежные средства и денежные эквиваленты\",\"2\":  5499000 }]";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGettingValue() throws Exception{
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680032");
        StringBuilder sbGot = jsonLogics.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(sbGot.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();
        JsonArray jsonArray= jsonLogics.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values");
        ArrayList<Long> expected=new ArrayList<>();
        Long longNum=13018261000L;
        expected.add(longNum);
        ArrayList<Long> actual= jsonLogics.getValues(jsonArray,"nvalue");
        Assert.assertEquals(expected,actual);
    }
}
