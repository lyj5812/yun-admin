package com.lyj.gen.controller;
import com.lyj.gen.dto.DataSourceDto;
import com.lyj.gen.service.SourceService;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/source")
@Api(tags = "数据源管理")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    /**
     * 添加数据库
     *
     * @param dto
     * @return
     */
    @ApiOperation("添加数据源")
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody DataSourceDto dto) {

        return R.ok(sourceService.add(dto));
    }

    /**
     * 添加数据库
     *
     * @return
     */
    @ApiOperation("数据源列表")
    @GetMapping("/list")
    public R<List<Object>> list() {
        return R.ok(sourceService.getSourceList());
    }

}
