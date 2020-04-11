package portal.ranting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.ranting.form.RantingForm;
import portal.ranting.model.RantingModel;
import portal.ranting.service.RantingSvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/ranting")
public class RantingController {

    private final RantingSvc rantingSvc;

    @Autowired
    public RantingController(RantingSvc rantingSvc) {
        this.rantingSvc = rantingSvc;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RantingModel> getAll() {
        return rantingSvc.getAllRanting();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public RantingModel getDetail(@PathVariable Long id) {
        return rantingSvc.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RantingModel createNew(@Valid @RequestBody RantingForm form,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
        return rantingSvc.createNew(form);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RantingModel update(@PathVariable Long id, @Valid @RequestBody RantingForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
        return rantingSvc.update(id, form);
    }
}
