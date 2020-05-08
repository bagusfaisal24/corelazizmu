package portal.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.master.form.ProgramKerjaForm;
import portal.master.model.ProgramKerjaModel;
import portal.master.service.ProgramKerjaSvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/master/program-kerja")
public class ProgramKerjaController {

    private final ProgramKerjaSvc programKerjaSvc;

    @Autowired
    public ProgramKerjaController(ProgramKerjaSvc programKerjaSvc) {
        this.programKerjaSvc = programKerjaSvc;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProgramKerjaModel> getAll() {
        return programKerjaSvc.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramKerjaModel createNew(@Valid @RequestBody ProgramKerjaForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
        return programKerjaSvc.createNew(form);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ProgramKerjaModel update(@RequestBody ProgramKerjaForm form,
                                    @PathVariable Long id) {
        ProgramKerjaModel programKerja = programKerjaSvc.getSingle(id);
        return programKerjaSvc.update(programKerja, form);
    }
}
