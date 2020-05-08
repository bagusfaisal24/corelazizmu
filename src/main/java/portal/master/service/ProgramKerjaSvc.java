package portal.master.service;

import portal.master.form.ProgramKerjaForm;
import portal.master.model.ProgramKerjaModel;

import java.util.List;

public interface ProgramKerjaSvc {

    ProgramKerjaModel save(ProgramKerjaModel programKerja);

    ProgramKerjaModel getSingle(Long id);

    List<ProgramKerjaModel> list();

    ProgramKerjaModel createNew(ProgramKerjaForm form);

    ProgramKerjaModel update(ProgramKerjaModel programKerja, ProgramKerjaForm form);
}
