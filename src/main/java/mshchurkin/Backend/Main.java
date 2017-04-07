package mshchurkin.Backend;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

//    public static void main(String[] args) throws IOException {
//        APIWorker aw = new APIWorker();
//        boolean out = false;
//        do {
//            Scanner scanInput = new Scanner(System.in);
//            String switcher = scanInput.nextLine();
//            switch (switcher) {
//                case "1": {
//                    aw.executeAuthorization();
//                }
//                break;
//                case "2": {
//                    String urlString = "http://46.146.245.83/demo2//api/qa/headers";
//                    URL url = new URL(urlString);
//                    String map = aw.executeGet(url, null);
//                    System.out.println(map);
//                }
//                break;
//                case "3": {
//                    String atabId = scanInput.nextLine();
//                    String urlString = "http://46.146.245.83/demo2//api/qa/headers/" + atabId;
//                    URL url = new URL(urlString);
//                    String map = aw.executeGet(url, null);
//                    System.out.println(map);
//                }
//                case "4": {
//                    String personJSONData =
//                            "{\n" +
//                                    "\"groupActionDto\": \n" +
//                                    "{\n" +
//                                    "  \"dobj\": {\n" +
//                                    "    \"currentDate\": \"2016-04-03T07:19:52.112Z\",\n" +
//                                    "    \"maskMap\": {},\n" +
//                                    "    \"types\": [\n" +
//                                    "      {\n" +
//                                    "        \"id\": 0,\n" +
//                                    "        \"ident\": \"string\",\n" +
//                                    "        \"name\": \"string\"\n" +
//                                    "      }\n" +
//                                    "    ],\n" +
//                                    "    \"values\": {}\n" +
//                                    "  },\n" +
//                                    "  \"ids\": [\n" +
//                                    "    0\n" +
//                                    "  ]\n" +
//                                    "}\n" +
//                                    "}";
//                    JsonReader reader = Json.createReader(new StringReader(personJSONData));
//                    JsonObject jsonObject = reader.readObject();
//                    reader.close();
//                    String urlString = "http://46.146.245.83/demo2//api/sys/groupActions/attributes";
//                    URL url = new URL(urlString);
//                    int rCode = aw.executePost(url, jsonObject);
//                    System.out.println(rCode);
//                }
//                break;
//                case "5": {
//                    out = true;
//                }
//                break;
//            }
//        } while (!out);
//    }
}
