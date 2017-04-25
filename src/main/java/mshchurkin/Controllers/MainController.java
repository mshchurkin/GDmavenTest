package mshchurkin.Controllers;

import mshchurkin.Backend.APIWorker;
import mshchurkin.Backend.URLRequestMaker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

@Controller
@Scope("session")
public class MainController {

    private URLRequestMaker urlRequestMaker = new URLRequestMaker();
    private static StringBuilder sb;

    //mappings
    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        APIWorker aw = APIWorker.getInstance();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        String message = aw.executeAuthorization();
        mav.addObject("COOKIE_SESSION", message);
        return mav;
    }

    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public String data() throws IOException {
        return sb.toString().substring(10,sb.toString().length()-1);
    }

    public String columnsInit() throws IOException {

        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/679970";
//        Map<String,String> params=new HashMap<>();
//        params.put("formInstId","679770");
//        params.put("rowId","680131");
//        params.put("pokId","680130");

        //urlRequestMaker.setParams(params);
        urlRequestMaker.setUrlString(urlString);
        sb = urlRequestMaker.getSb();

        String columnsResult = "";

        JsonReader jsonReader = Json.createReader(new StringReader(sb.toString()));
        //JsonArray jsonArray=jsonReader.readArray();
        //JsonObject jsonObject=jsonArray.getJsonObject(0);
        JsonObject jsonObject = jsonReader.readObject();
        Set<String> s = jsonObject.keySet();
        Object[] arr = s.toArray();
        columnsResult = columnsResult + "[";
        for (Object anArr : arr) {
            columnsResult = columnsResult + "{ field: '" + anArr.toString() + "'},";
        }
        columnsResult = columnsResult.substring(0, columnsResult.length() - 1);
        columnsResult = columnsResult + "]";
        return columnsResult;
    }
}