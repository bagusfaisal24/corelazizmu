package portal.ranting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.ranting.form.RantingForm;
import portal.ranting.model.RantingModel;
import portal.ranting.repository.RantingRepository;

import java.util.List;

@Service
public class RantingSvc {

    private final RantingRepository rantingRepository;

    @Autowired
    public RantingSvc(RantingRepository rantingRepository) {
        this.rantingRepository = rantingRepository;
    }

    public List<RantingModel> getAllRanting() {
        return (List<RantingModel>) rantingRepository.findAll();
    }

    public RantingModel findOne(Long id) {
        return rantingRepository.findOne(id);
    }

    public RantingModel createNew(RantingForm form) {
        RantingModel ranting = new RantingModel();
        ranting.setName(form.getName());
        ranting.setKetuaRanting(form.getKetuaRanting());
        return save(ranting);
    }

    public RantingModel update(Long id, RantingForm form) {
        RantingModel ranting = findOne(id);
        ranting.setName(form.getName());
        ranting.setKetuaRanting(form.getKetuaRanting());
        return save(ranting);
    }

    public RantingModel save(RantingModel ranting) {
        return rantingRepository.save(ranting);
    }
}
