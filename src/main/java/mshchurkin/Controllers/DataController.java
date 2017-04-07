package mshchurkin.Controllers;

import mshchurkin.Backend.APIWorker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URL;


@Controller
@Scope("session")
public class DataController {
    APIWorker aw = APIWorker.getInstance();
    @RequestMapping(value = "/data", produces = "application/json; charset=windows-1251")
    @ResponseBody
    public String data() throws IOException {
        String urlString = "http://46.146.245.83/demo2//api/qa/headers";
        URL url = new URL(urlString);
        String dataJSON = aw.executeGet(url, null);
        return dataJSON;
    }
}
