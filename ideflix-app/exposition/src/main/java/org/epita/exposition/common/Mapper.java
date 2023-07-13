package org.epita.exposition.common;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Mapper<E, D> {
    public abstract D mapEntityToDto(E input);

    public abstract E mapDtoToEntity(D input);

    public List<E> mapListDtoToEntity(List<D> inputList) {
        return inputList.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
    }

    public List<D> mapListEntityToDto(List<E> inputList) {
        return inputList.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }
}
