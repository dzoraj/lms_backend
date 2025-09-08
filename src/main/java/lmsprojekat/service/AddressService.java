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
    public AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setAddress(address.getAddress());
        dto.setNumber(address.getNumber());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setStudent(address.getStudent());
        dto.setTeacher(address.getTeacher());
        dto.setUniversityId(address.getUniversity() != null ? address.getUniversity().getId() : null);
        dto.setFacultyId(address.getFaculty() != null ? address.getFaculty().getId() : null);
        return dto;
    }

    @Override
    public Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setAddress(dto.getAddress());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStudent(dto.getStudent());
        address.setTeacher(dto.getTeacher());
        return address;
    }

    @Override
    public void updateEntity(Address address, AddressDTO dto) {
        address.setAddress(dto.getAddress());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStudent(dto.getStudent());
        address.setTeacher(dto.getTeacher());
    }
}
