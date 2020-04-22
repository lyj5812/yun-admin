package com.lyj.admin.upload.service.impl;
import com.lyj.admin.upload.domain.Chunk;
import com.lyj.admin.upload.service.ChunkService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ChunkServiceImpl implements ChunkService {

    @Override
    public void saveChunk(Chunk chunk) {

    }

    /**
     * 校验分块是否上传
     * @param identifier
     * @param chunkNumber
     * @return
     */
    @Override
    public boolean checkChunk(String identifier, Integer chunkNumber) {

        return true;
    }

}
