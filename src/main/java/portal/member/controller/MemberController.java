package portal.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.member.form.MemberForm;
import portal.member.model.MemberModel;
import portal.member.service.MemberSvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    private final MemberSvc memberSvc;

    @Autowired
    public MemberController(MemberSvc memberSvc) {
        this.memberSvc = memberSvc;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<MemberModel> getAll() {
        return memberSvc.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MemberModel getAll(@Valid @RequestBody MemberForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
        MemberModel member = new MemberModel();
        return member;
    }
}
