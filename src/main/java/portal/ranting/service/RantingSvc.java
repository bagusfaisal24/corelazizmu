package portal.ranting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.core.exception.DataNotFoundException;
import portal.ranting.form.RantingForm;
import portal.ranting.model.RantingModel;
import portal.ranting.repository.RantingRepository;
import portal.util.TransactionDate;

import java.util.List;

@Service
public class RantingSvc {

    private final RantingRepository rantingRepository;
    private final TransactionDate transactionDate;

    @Autowired
    public RantingSvc(RantingRepository rantingRepository,
                      TransactionDate transactionDate) {
        this.rantingRepository = rantingRepository;
        this.transactionDate = transactionDate;
    }

    public List<RantingModel> getAllRanting() {
        return (List<RantingModel>) rantingRepository.getAll();
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
    
    public void delete (Long id) {
        RantingModel ranting = findOne(id);
        if (ranting == null)throw new DataNotFoundException("data tidak ditemukan");
        ranting.setDeletedAt(transactionDate.now());
        save(ranting);
    }
}
