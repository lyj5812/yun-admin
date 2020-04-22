package com.lyj.gen.service;

import com.lyj.gen.dto.DataSourceDto;

import java.util.List;

public interface SourceService {

    Boolean add(DataSourceDto dto);

    List<Object> getSourceList();
}
