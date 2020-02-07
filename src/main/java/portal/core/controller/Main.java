package portal.core.controller;

import portal.core.model.OkResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {


    @GetMapping("/")
    @ResponseBody
    public OkResponse main(Model model) {
        return new OkResponse();
    }

}
