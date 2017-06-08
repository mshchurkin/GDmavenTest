package mshchurkin.Controllers;

import mshchurkin.Backend.APIWorker;
import mshchurkin.Backend.JsonLogics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
@Scope("session")
public class MainController {

    private JsonLogics jsonLogics = new JsonLogics();
    private static String dataJSON;

    //region mappings
    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        APIWorker aw = APIWorker.getInstance();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        String message = aw.executeAuthorization();
        mav.addObject("COOKIE_SESSION", message);
        return mav;
    }

    @RequestMapping(value = "/data/F5date1", produces = "application/json")
    @ResponseBody
    public String dataF5() throws IOException {
        String fRes="";
        for(int i=0;i<29;i++) {
            String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680632";
            jsonLogics.setUrlString(urlString);
            String res = jsonLogics.getResultJsonArray();
            byte[] ptext = res.getBytes(ISO_8859_1);
            fRes = new String(ptext, UTF_8);
        }
        return fRes;
    }

    @RequestMapping(value = "/data/F1date1", produces = "application/json")
    @ResponseBody
    public String dataF1() throws IOException {
        for(int i=0;i<6;i++) {
            String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680624";
            jsonLogics.setUrlString(urlString);
            jsonLogics.getResultJsonArray();
        }
        return "1";
    }

    @RequestMapping(value = "/data/F5date2", produces = "application/json")
    @ResponseBody
    public String dataF5date2() throws IOException {
        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680329";
        jsonLogics.setUrlString(urlString);
        String res= jsonLogics.getResultJsonArray();
        byte[] ptext = res.getBytes(ISO_8859_1);
        String fRes = new String(ptext, UTF_8);
        return fRes;
    }

    @RequestMapping(value = "/data/F1date2", produces = "application/json")
    @ResponseBody
    public String dataF1date2() throws IOException {
        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680319";
        jsonLogics.setUrlString(urlString);
        return jsonLogics.getResultJsonArray();
    }
    //endregion

    /**
     * Sends JSON to page containing table columns info
     * @param formId identifier of form
     * @return table columns info JSON
     * @throws IOException form was not found on GreenData Server
     */
    public String columnsInit(Integer formId) throws IOException {

        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/"+formId+"";
        jsonLogics.setUrlString(urlString);
        return jsonLogics.getColumnNames();
    }
}