package com.lyj.gen.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyj.gen.service.GenService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;


@RestController
@RequestMapping("/gen")
public class GenController {

    @Autowired
    private GenService genService;

    /**
     * 生成代码
     */
    @PostMapping("/{sourceId}")
    public void genCode(HttpServletResponse response, @PathVariable("sourceId") String sourceId, @RequestBody String tableNames) throws IOException {
        JSONArray array = JSONObject.parseArray(tableNames);
        List<String> tableNameList = array.toJavaList(String.class);
        byte[] data = genService.genCode(sourceId, tableNameList);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"yun.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    public static void main(String[] args) {
        CronUtil.schedule("*/10 * * * * *", new Task() {
            @Override
            public void execute() {
                Console.log("Task excuted.");
                CronUtil.getScheduler().stop();
            }
        });

        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
