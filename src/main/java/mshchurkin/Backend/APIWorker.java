package mshchurkin.Backend;

import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class APIWorker {

    private String COOKIE_SESSION_ID;

    private static APIWorker instance;

    private APIWorker() {
    }

    public static APIWorker getInstance() {
        if (instance == null) {
            instance = new APIWorker();
        }
        return instance;
    }

    /**
     * GreenData API Authorization
     */
    public String executeAuthorization() throws IOException {

        String url = "http://46.146.245.83/demo2//api/authentication?j_password=d0xkR5h675lO57P&j_username=admin#/";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        int responseCode = con.getResponseCode();
        COOKIE_SESSION_ID = con.getResponseMessage();

        StringBuilder builder = new StringBuilder();
        builder.append(con.getResponseCode())
                .append(" ")
                .append(con.getResponseMessage())
                .append("\n");

        System.out.println(responseCode);
        Map<String, List<String>> map = con.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null)
                if (entry.getKey().contains("Set-Cookie")) {
                    COOKIE_SESSION_ID = entry.getValue().get(0);
                }
        }
        COOKIE_SESSION_ID = COOKIE_SESSION_ID.replace(";path=/demo2/;HttpOnly", "");
        //System.out.println(COOKIE_SESSION_ID);
        con.disconnect();
        return COOKIE_SESSION_ID;
    }

    /**
     * GreenData GET Methods worker
     *
     * @param url    method url
     * @param params method params
     * @return json string
     * @throws IOException can't establish connection IOException
     */
    public String executeGet(URL url, Dictionary<String, String> params) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Cookie", COOKIE_SESSION_ID);
        StringBuilder builder = new StringBuilder();
        builder.append(con.getResponseCode())
                .append(" ")
                .append(con.getResponseMessage())
                .append("\n");
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream()),"ISO-8859-1"));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

//        JsonReader jsonReader = Json.createReader(new StringReader(sb.toString()));
//        JsonObject jsonObject=jsonReader.readObject();
        String myString = sb.toString();
        byte bytes[] = myString.getBytes("ISO-8859-1");
        String value = new String(bytes, "UTF-8");
        return sb.toString();//new String(sb.toString().getBytes());
    }

    /**
     * GreenData POST Methods worker
     *
     * @param url        method url
     * @param jsonObject method body as JSON
     * @return response code
     * @throws IOException can't establish connection IOException
     */
    int executePost(URL url, JsonObject jsonObject) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Cookie", COOKIE_SESSION_ID);
        OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
        os.write(jsonObject.toString());
        os.close();
        int responseCode = con.getResponseCode();
        return responseCode;
    }
}
