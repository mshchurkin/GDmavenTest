package mshchurkin.GdApiTest;

import mshchurkin.Backend.APIWorker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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


}
