package com.lyj.admin.upload.controller;

import com.lyj.admin.upload.domain.Chunk;
import com.lyj.admin.upload.domain.FileInfo;
import com.lyj.admin.upload.service.ChunkService;
import com.lyj.admin.upload.service.FileInfoService;
import com.lyj.pojo.R;
import com.lyj.upload.FileStorage;
import com.lyj.upload.impl.AliyunFileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import static com.lyj.admin.upload.utils.FileUtils.generatePath;
import static com.lyj.admin.upload.utils.FileUtils.merge;

/**
 * @author lyj
 * @date 2019-12-23
 **/
@RestController
@Api(tags = "上传接口")
@Slf4j
@RequestMapping("/upload")
public class UploadController {

    @Autowired(required = false)
    private FileStorage fileStorage;

    private String uploadFolder = "D://";

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private ChunkService chunkService;

    @PostMapping("/aliyun/oss")
    @ApiOperation("阿里云上传文件")
    public R<String> upload(@RequestParam("file") MultipartFile file) {
        Assert.notNull(fileStorage, "文件上传没有初始化");
        Assert.isTrue(fileStorage.getClass().equals(AliyunFileStorage.class), "没有初始化阿里OSS上传接口");
        try {
            Assert.notNull(file, "文件不能为空");
            String fileName = file.getOriginalFilename();
            String key = UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            fileStorage.store(file.getInputStream(), key);
            return R.ok(this.fileStorage.getDownloadUrl(key));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败");
        }
    }


    @PostMapping("/chunk")
    public String uploadChunk(Chunk chunk, MultipartFile file) {
        log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(generatePath(uploadFolder, chunk));
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件 {} 写入成功, uuid:{}", chunk.getFilename(), chunk.getIdentifier());
            chunkService.saveChunk(chunk);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }

    @GetMapping("/chunk")
    public Object checkChunk(Chunk chunk, HttpServletResponse response) {
        if (chunkService.checkChunk(chunk.getIdentifier(), chunk.getChunkNumber())) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
        return chunk;
    }

    @PostMapping("/mergeFile")
    public String mergeFile(@RequestBody FileInfo fileInfo) {
        String filename = fileInfo.getFilename();
        String file = uploadFolder + "/" + fileInfo.getIdentifier() + "/" + filename;
        String folder = uploadFolder + "/" + fileInfo.getIdentifier();
        merge(file, folder, filename);
        fileInfo.setLocation(file);
        fileInfoService.addFileInfo(fileInfo);
        return "合并成功";
    }
}
