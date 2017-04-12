package mshchurkin.Controllers;

import mshchurkin.Backend.APIWorker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Set;

@Controller
@Scope("session")
public class MainController {
    APIWorker aw = APIWorker.getInstance();

    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        String message = aw.executeAuthorization();
        mav.addObject("COOKIE_SESSION", message);
        return mav;
    }

    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public String data() throws IOException {
        String urlString = "http://46.146.245.83/demo2//api/risk/forms/335329/instances/existing";
        URL url = new URL(urlString);
        StringBuilder sb = aw.executeGet(url, null);
        return sb.toString();
    }

    public String columnsInit() throws IOException {
        String columnsResult="";
        String urlString = "http://46.146.245.83/demo2//api/risk/forms/335329/instances/existing";
        URL url = new URL(urlString);
        StringBuilder sb = aw.executeGet(url, null);
        JsonReader jsonReader = Json.createReader(new StringReader(sb.toString()));
        JsonArray jsonArray=jsonReader.readArray();
        JsonObject jsonObject=jsonArray.getJsonObject(0);
        Set<String> s =jsonObject.keySet();
        Object[] arr=s.toArray();
        columnsResult=columnsResult+"[";
        for(int i=0;i<arr.length;i++) {
            columnsResult = columnsResult + "{ field: '" + arr[i].toString() + "'},";
        }
        columnsResult=columnsResult.substring(0,columnsResult.length()-1);
        columnsResult=columnsResult+"]";
        return columnsResult;
    }

}

