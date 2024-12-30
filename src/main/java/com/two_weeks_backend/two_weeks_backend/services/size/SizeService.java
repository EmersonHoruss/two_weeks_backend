package com.two_weeks_backend.two_weeks_backend.services.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.management.EntityName;
import com.two_weeks_backend.two_weeks_backend.entities.management.ManagementCode;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.management.ManagementCodeService;

@Service
public class SizeService extends BaseServiceImplementation<Size> {
    @Autowired
    private ManagementCodeService managementCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Size create(Size size) {
        ManagementCode managementCode = this.managementCodeService.get(EntityName.SIZE);
        String code = "";
        if (managementCode == null) {
            managementCode = new ManagementCode(EntityName.SIZE);
            code = managementCode.getFirstCode();
            managementCode.subtractCode(code);
            this.managementCodeService.create(managementCode);
        } else {
            code = managementCode.getFirstCode();
            managementCode.subtractCode(code);
            this.managementCodeService.update(managementCode);
        }

        if (managementCode.allCodesAreUsed()) {
            throw new RuntimeException("se ha alcanzado el límite de códigos, no se puede crear ninguna talla más");
        }

        size.setCode(code);
        Size savedType = super.create(size);
        return savedType;
    }

    @Override
    public Size update(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());
        size.setActivated(retrievedSize.getActivated());
        size.setCode(retrievedSize.getCode());
        return this.baseRepository.save(size);
    }

    public Size setActivation(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());
        retrievedSize.setActivated(size.getActivated());
        return this.baseRepository.save(retrievedSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        Size size = this.baseRepository.getReferenceById(id);
        this.baseRepository.delete(size);

        ManagementCode managementCode = this.managementCodeService.get(EntityName.TYPE);
        if (managementCode == null)
            throw new RuntimeException("eliminación no se puede ejecutar porque no existe el código");

        String code = size.getCode();
        managementCode.addCode(code);
        this.managementCodeService.update(managementCode);
    }
}
