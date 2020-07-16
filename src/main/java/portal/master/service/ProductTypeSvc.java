package portal.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.master.model.ProductTypeModel;
import portal.master.repository.ProductTypeRepositories;

import java.util.List;

@Service
public class ProductTypeSvc {

    @Autowired
    private ProductTypeRepositories productTypeRepo;

    public List<ProductTypeModel> getAll() {
        return (List<ProductTypeModel>) productTypeRepo.findAll();
    }
    
    public ProductTypeModel findOne(Long id){
        return productTypeRepo.findOne(id);
    }
}
