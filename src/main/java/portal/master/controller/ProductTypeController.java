package portal.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.master.form.ProgramKerjaForm;
import portal.master.model.ProductTypeModel;
import portal.master.model.ProgramKerjaModel;
import portal.master.service.ProductTypeSvc;
import portal.master.service.ProgramKerjaSvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/master/product-type")
public class ProductTypeController {

    private final ProductTypeSvc productTypeSvc;

    @Autowired
    public ProductTypeController(ProductTypeSvc productTypeSvc) {
        this.productTypeSvc = productTypeSvc;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProductTypeModel> getAll() {
        return productTypeSvc.getAll();
    }
}
