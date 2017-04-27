package mshchurkin.Controllers;

import mshchurkin.Backend.APIWorker;
import mshchurkin.Backend.JsonLogics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

    @RequestMapping(value = "/dataF5", produces = "application/json")
    @ResponseBody
    public String dataF5() throws IOException {
        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680031";
        jsonLogics.setUrlString(urlString);
        return jsonLogics.getResultJsonArray().toString();
    }

    @RequestMapping(value = "/dataF1", produces = "application/json")
    @ResponseBody
    public String dataF1() throws IOException {
        String urlString = "http://46.146.245.83/demo2/api/sys/dynamicObjects/680032";
        jsonLogics.setUrlString(urlString);
        return jsonLogics.getResultJsonArray().toString();
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
        return jsonLogics.getColumnNames().toString();
    }
}