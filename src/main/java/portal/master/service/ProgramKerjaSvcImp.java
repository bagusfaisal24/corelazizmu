package portal.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.master.form.ProgramKerjaForm;
import portal.master.model.ProgramKerjaModel;
import portal.master.repository.ProgramKerjaRepositories;

import java.util.List;

@Service
public class ProgramKerjaSvcImp implements ProgramKerjaSvc {

    @Autowired
    private ProgramKerjaRepositories programKerjaRepositories;

    @Override
    public ProgramKerjaModel save(ProgramKerjaModel programKerja) {
        return programKerjaRepositories.save(programKerja);
    }

    @Override
    public List<ProgramKerjaModel> list() {
        return (List<ProgramKerjaModel>) programKerjaRepositories.findAll();
    }

    @Override
    public ProgramKerjaModel createNew(ProgramKerjaForm form) {
        ProgramKerjaModel programKerja = new ProgramKerjaModel();
        programKerja.setName(form.getName());
        return save(programKerja);
    }

    @Override
    public ProgramKerjaModel update(ProgramKerjaModel programKerja, ProgramKerjaForm form) {
        programKerja.setName(form.getName());
        return save(programKerja);
    }

    @Override
    public ProgramKerjaModel getSingle(Long id) {
        return programKerjaRepositories.findOne(id);
    }
}
