package mshchurkin.Backend;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonLogics {

    private APIWorker aw = APIWorker.getInstance();//GreenData API class
    private String urlString=null; //URL Connection String
    private Map<String,String> params=null; //Connection Params
    private Map<String,String> headers=null; //Connection Headers
    private URL url=null; //URL Connection

    /**
     * Returns results from GreenData Server
     * @return StringBuilder result from called API Method
     * @throws IOException page on GreenData server not found
     */
    StringBuilder getSb() throws IOException {

        if(params!=null) {
            if (params.size() != 0) {
                urlString=urlString+"?";
                for (Map.Entry<String, String> param : params.entrySet()) {
                    String property = param.getKey();
                    String value = param.getValue();
                    urlString=urlString+property+"="+value+"&";
                }
                urlString=urlString.substring(0,urlString.length()-1);
            }
        }
            url = new URL(urlString);

        //заголовки
        Map<String,String> headers=new HashMap<>();

        StringBuilder sb = aw.executeGet(url, headers);
        return sb;
    }

    /**
     * Gets JSON Array with needed name
     * @param jsonObject json object with different arrays
     * @param wantedJsonArrName name of array
     * @return needed JSON Array
     */
    JsonObject getJsonArray(JsonObject jsonObject, String wantedJsonArrName){
        return jsonObject.getJsonObject("values").getJsonObject(wantedJsonArrName);
    }

    /**
     * Gets names of items stored in JSON Array
     * @param jsonArray Array with Items
     * @param wantedKey item type
     * @return Collection of item names
     * @throws IOException page on GreenData server not found
     */
    ArrayList<String> getNames(JsonArray jsonArray, String wantedKey) throws IOException {
        ArrayList<Integer> iDs=new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject=jsonArray.getJsonObject(i);
            Integer res= jsonObject.getInt(wantedKey);
            iDs.add(res);
        }
        ArrayList<String> names =new ArrayList<>();
        if(iDs.size()!=0){
            for (Integer id:iDs) {
                this.setUrlString("http://46.146.245.83/demo2/api/sys/dynamicObjects/"+id+"");
                StringBuilder stringBuilder = this.getSb();
                JsonReader jsonReader = Json.createReader(new StringReader(stringBuilder.toString()));
                JsonObject jsonObject=jsonReader.readObject();
                jsonReader.close();
                String name=this.getJsonArray(jsonObject,"NAME").get("value").toString();
                name=name.substring(1,name.length()-1);
                names.add(name);
            }
        }
        names= new ArrayList<>(new LinkedHashSet<>(names));
        Collections.reverse(names);
        return names;
    }

    /**
     * Gets values of items stroed in JSON Array
     * @param jsonArray Array with Items
     * @param valueKey item value type
     * @return Collection of item values
     */
    ArrayList<Long> getValues(JsonArray jsonArray, String valueKey){
        ArrayList<Long> values=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++) {
            Long value= jsonArray.getJsonObject(i).getJsonNumber(valueKey).longValue();
            values.add(value);
        }
        Collections.reverse(values);
        return values;
    }

    /**
     * Returns JSON with selected form
     * @return JSON with selected form data
     * @throws IOException page on GreenData server not found
     */
    public JsonArray getResultJsonArray() throws IOException {
        StringBuilder stringBuilder=this.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(stringBuilder.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();
        JsonArray jsonArray= this.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values");
        ArrayList<String>rowNames=this.getNames(jsonArray,"rowId");
        ArrayList<String>pokNames=this.getNames(jsonArray,"pokId");
        ArrayList<Long>values=this.getValues(jsonArray,"nvalue");

        String tempString="[";
        for (int i=0;i<rowNames.size();i++) {
            tempString = tempString + "{ \"ID\":  " + i + ", \"1\":  \"" + rowNames.get(i) + "\",\"2\":  " + values.get(i) + " }, ";
            tempString = tempString.replaceAll("\r?\n", "");
        }
        tempString=tempString.substring(0,tempString.length()-2);
        tempString=tempString+"]";
        byte[] ptext = tempString.getBytes(ISO_8859_1);
        tempString = new String(ptext, UTF_8);
        JsonReader tempReader=Json.createReader(new StringReader(tempString));
//            for(String pokName:pokNames) {
//                tempString=tempString+"'"+pokName+"': ''"
//            }
        return tempReader.readArray();
    }

    /**
     * Return JSON with selected form column names
     * @return JSON with column names
     * @throws IOException page on GreenData server not found
     */
    public String getColumnNames()throws IOException{

        String columnsResult = "";

        StringBuilder stringBuilder=this.getSb();
        JsonReader jsonReader = Json.createReader(new StringReader(stringBuilder.toString()));
        JsonObject jsonObject=jsonReader.readObject();
        jsonReader.close();
        JsonArray jsonArray= this.getJsonArray(jsonObject,"FORM_INST_DATA").getJsonObject("value").getJsonArray("values");
        ArrayList<String>pokNames=this.getNames(jsonArray,"pokId");

        columnsResult = columnsResult + "[{ field: \"ID\"},{ field: \"1\", title: \"Наименование\"},";
        for (String pokName : pokNames) {
            String temp ="{ field: \"2\" , title: \"" + pokName + "\"},";
            byte[] ptext = temp.getBytes(ISO_8859_1);
            temp = new String(ptext, UTF_8);
            columnsResult = columnsResult + temp;
        }
        columnsResult = columnsResult.substring(0, columnsResult.length() - 1);
        columnsResult = columnsResult + "]";
        return columnsResult;
    }

    /**
     * Return URL Connection string
     * @return connection string
     */
    public String getUrlString() {
        return urlString;
    }

    /**
     * sets URL Connection string
     * @param urlString connection string
     */
    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    /**
     * Return params of URL Connection
     * @return connection params
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * sets URL Connection Params
     * @param params connection params
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /**
     * Return URL Connection headers
     * @return connection headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * sets headers to URL Connection
     * @param headers connection headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Returns URL connection
     * @return URL connection
     */
    public URL getUrl() {
        return url;
    }

}
