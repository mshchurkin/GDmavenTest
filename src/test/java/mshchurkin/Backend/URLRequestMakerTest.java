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
import java.net.URL;
import java.util.ArrayList;

@RunWith(Arquillian.class)
public class URLRequestMakerTest {
    @Inject
    URLRequestMaker urlRequestMaker=new URLRequestMaker();

    APIWorker apiWorker=APIWorker.getInstance();

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

        URL url = new URL("http://46.146.245.83/demo2/api/sys/dynamicObjects/679919");
        StringBuilder sb = apiWorker.executeGet(url, null);
        JsonReader jsonReader = Json.createReader(new StringReader(sb.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        JsonObject resObj=urlRequestMaker.getJsonArray(jsonObject,"NAME");
        String res = resObj.getJsonString("value").getString();

        Assert.assertEquals("1270",res);
    }

    @Test
    public void testGettingNames()throws Exception{
        URL url = new URL("http://46.146.245.83/demo2/api/sys/dynamicObjects/680032");
        StringBuilder sbGot = apiWorker.executeGet(url, null);
        JsonReader jsonReader = Json.createReader(new StringReader(sbGot.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();
        JsonArray tempArr=urlRequestMaker.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values");
        ArrayList<String> resNames = urlRequestMaker.getNames(tempArr,"pokId");
    }

    @Test
    public void testGettingArray()throws Exception{
        URL url = new URL("http://46.146.245.83/demo2/api/sys/dynamicObjects/680032");
        StringBuilder sbGot = apiWorker.executeGet(url, null);
        JsonReader jsonReader = Json.createReader(new StringReader(sbGot.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();

        JsonObject resObj=urlRequestMaker.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values").getJsonObject(0);

        JsonReader jsonReaderCheck = Json.createReader(new StringReader("{\n" +
                "            \"formInstId\": 680032,\n" +
                "            \"rowId\": 679919,\n" +
                "            \"pokId\": 679929,\n" +
                "            \"nvalue\": 13018261000\n" +
                "          }"));
        JsonObject jsonObjectCheck=jsonReaderCheck.readObject();
        jsonReaderCheck.close();

        Assert.assertEquals(jsonObjectCheck,resObj);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(URLRequestMaker.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
