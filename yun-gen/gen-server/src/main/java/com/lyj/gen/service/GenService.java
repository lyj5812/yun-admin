package com.lyj.gen.service;

import java.util.List;

public interface GenService {

    byte[] genCode(String sourceId, List<String> tableNameList);
}
