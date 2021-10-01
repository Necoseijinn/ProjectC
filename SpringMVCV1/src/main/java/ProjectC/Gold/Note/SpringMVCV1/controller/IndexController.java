package ProjectC.Gold.Note.SpringMVCV1.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller {
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView modelAndView = new ModelAndView("JSPPages/index.jsp");
        modelAndView.addObject("word","The index page");
        return modelAndView;
    }
}
