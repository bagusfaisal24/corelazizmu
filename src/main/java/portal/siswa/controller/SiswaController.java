package portal.siswa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portal.siswa.model.SiswaModel;
import portal.siswa.service.SiswaSvc;

@RestController
@RequestMapping("/v1/siswa")
public class SiswaController {

    private static final Logger log = LoggerFactory.getLogger(SiswaController.class);

    private final SiswaSvc siswaSvc;

    @Autowired
    public SiswaController(SiswaSvc siswaSvc) {
        this.siswaSvc = siswaSvc;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<SiswaModel> getAll() {
        return siswaSvc.findAll();
    }
}
