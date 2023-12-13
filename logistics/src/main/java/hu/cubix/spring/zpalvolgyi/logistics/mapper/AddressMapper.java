package hu.cubix.spring.zpalvolgyi.logistics.mapper;

import hu.cubix.spring.zpalvolgyi.logistics.dto.AddressDto;
import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    public AddressDto addressToDto(Address address);
    public Address dtoToAddress(AddressDto addressDto);
    public List<AddressDto> addressesToDtos(List<Address> addresses);
    public List<Address> dtosToAddresses(List<AddressDto> addressDtos);
}
