package com.lyj.gen.service;

import com.lyj.gen.dto.TableDto;

import java.util.List;

public interface TableService {

    List<TableDto> getTableList(String sourceId);

    TableDto getTableInfo(String sourceId, String tableName);

    Boolean editTableInfo(String sourceId, String tableName, TableDto tableDto);
}
