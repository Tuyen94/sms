package tuyen.bui.sms.infrastructure.until;

import org.modelmapper.ModelMapper;

public class ModelMapperUntil {

    public static ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
