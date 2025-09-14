package lmsprojekat.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.AddressDTO;
import lmsprojekat.model.Address;
import lmsprojekat.repository.AddressRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.universityrepo.UniversityRepository;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;

@Service
public class AddressService extends AbstractCrudService<AddressDTO, Address, Long> {

    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    public AddressService(AddressRepository addressRepository,
                          StudentRepository studentRepository,
                          TeacherRepository teacherRepository,
                          UniversityRepository universityRepository,
                          FacultyRepository facultyRepository) {
        this.addressRepository = addressRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
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
        dto.setStudentId(address.getStudent() != null ? address.getStudent().getId() : null);
        dto.setTeacherId(address.getTeacher() != null ? address.getTeacher().getId() : null);
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

        if (dto.getStudentId() != null) {
            address.setStudent(studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + dto.getStudentId())));
        }

        if (dto.getTeacherId() != null) {
            address.setTeacher(teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + dto.getTeacherId())));
        }

        if (dto.getUniversityId() != null) {
            address.setUniversity(universityRepository.findById(dto.getUniversityId())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversityId())));
        }

        if (dto.getFacultyId() != null) {
            address.setFaculty(facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + dto.getFacultyId())));
        }

        return address;
    }

    @Override
    public void updateEntity(Address address, AddressDTO dto) {
        address.setAddress(dto.getAddress());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());

        if (dto.getStudentId() != null) {
            address.setStudent(studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + dto.getStudentId())));
        } else {
            address.setStudent(null);
        }

        if (dto.getTeacherId() != null) {
            address.setTeacher(teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + dto.getTeacherId())));
        } else {
            address.setTeacher(null);
        }

        if (dto.getUniversityId() != null) {
            address.setUniversity(universityRepository.findById(dto.getUniversityId())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversityId())));
        } else {
            address.setUniversity(null);
        }

        if (dto.getFacultyId() != null) {
            address.setFaculty(facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + dto.getFacultyId())));
        } else {
            address.setFaculty(null);
        }
    }
}
