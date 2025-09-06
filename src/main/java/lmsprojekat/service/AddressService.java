package lmsprojekat.service;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.AddressDTO;
import lmsprojekat.model.Address;
import lmsprojekat.repository.AddressRepository;
import lmsprojekat.repository.SoftDeleteRepository;

@Service
public class AddressService extends AbstractCrudService<AddressDTO, Address, Long> {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    protected SoftDeleteRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    protected AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setAddressDTO(address.getAddress());
        dto.setNumber(address.getNumber());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setStudent(address.getStudent());
        dto.setTeacher(address.getTeacher());
        dto.setUniversity(address.getUniversity());
        dto.setFaculty(address.getFaculty());
        return dto;
    }

    @Override
    protected Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setAddress(dto.getAddressDTO());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStudent(dto.getStudent());
        address.setTeacher(dto.getTeacher());
        address.setUniversity(dto.getUniversity());
        address.setFaculty(dto.getFaculty());
        return address;
    }

    @Override
    protected void updateEntity(Address address, AddressDTO dto) {
        address.setAddress(dto.getAddressDTO());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStudent(dto.getStudent());
        address.setTeacher(dto.getTeacher());
        address.setUniversity(dto.getUniversity());
        address.setFaculty(dto.getFaculty());
    }
}
