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
        String expected="[{ field: \"ID\"},{ field: \"1\", title: \"????????????\"},{ field: \"2\" , title: \"Значение\"}]";
        String actual=jsonLogics.getColumnNames();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGettingFinalJson()throws Exception{
        jsonLogics.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/680031");
        JsonArray actual=jsonLogics.getResultJsonArray();
        JsonReader jsonReader=Json.createReader(new StringReader("[{\"ID\":0,\"1\":\"8122\",\"2\":9185998000},{\"ID\":1,\"1\":\"7620\",\"2\":500551000},{\"ID\":2,\"1\":\"7640\",\"2\":451606000},{\"ID\":3,\"1\":\"7630\",\"2\":269634000},{\"ID\":4,\"1\":\"7503\",\"2\":61945000},{\"ID\":5,\"1\":\"3101\",\"2\":8124735000}]"));
        JsonArray expected=jsonReader.readArray();
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
