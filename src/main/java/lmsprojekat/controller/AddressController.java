package lmsprojekat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.AddressDTO;
import lmsprojekat.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseCrudController<AddressDTO, Long> {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @Override
    protected AddressService getService() {
        return service;
    }
}
