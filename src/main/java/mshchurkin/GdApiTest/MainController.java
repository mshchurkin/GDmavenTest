package mshchurkin.GdApiTest;

import mshchurkin.Backend.APIWorker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonArray;
import java.io.IOException;
import java.net.URL;

@Controller
@Scope("session")
public class MainController {
    APIWorker aw = new APIWorker();
    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        String message = aw.executeAuthorization();
        mav.addObject("COOIKE_SESSION", message);
        String urlString = "http://46.146.245.83/demo2//api/qa/headers";
        URL url = new URL(urlString);
        JsonArray dataJSON = aw.executeGet(url, null);
        return mav;
    }

    @RequestMapping(value = "/data")
    public JsonArray template() throws IOException {
        String urlString = "http://46.146.245.83/demo2//api/qa/headers";
        URL url = new URL(urlString);
        JsonArray dataJSON = aw.executeGet(url, null);
        return  dataJSON;
    }
}
