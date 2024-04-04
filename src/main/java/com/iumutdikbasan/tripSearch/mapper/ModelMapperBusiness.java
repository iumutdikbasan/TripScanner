package com.iumutdikbasan.tripSearch.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperBusiness {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
