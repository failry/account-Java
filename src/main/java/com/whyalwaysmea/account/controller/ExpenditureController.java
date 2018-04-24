package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.ExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.ExpenditureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 17:29 2018/4/10
 * @Description:
 */
@RestController
@Api(description = "支出分类")
@RequestMapping("/expenditure")
public class ExpenditureController extends BaseController {

    @Autowired
    private ExpenditureService expenditureService;

    @ApiOperation("获取所有父类支出分类")
    @GetMapping("/parent/list")
    public ExecuteResult<List<ExpenditureType>> getAllParentExpenditureType() {
        List<ExpenditureType> expenditures = expenditureService.getAllParentExpenditure();
        return ExecuteResult.ok(expenditures);
    }

    @ApiOperation("获取所有的父子结构的支出分类")
    @GetMapping("/list")
    public ExecuteResult<List<ExpenditureType>> getAllExpenditureType() {
        List<ExpenditureType> expenditureTypes = expenditureService.getAllExpenditure();
        return ExecuteResult.ok(expenditureTypes);
    }

    @ApiModelProperty("获取父分类下的所有子条目")
    @GetMapping("/child/{pid:\\d+}")
    public ExecuteResult<List<ExpenditureType>> getChildExpenditureTypeByParendId(@PathVariable("pid") @ApiParam("父条目id") int pid) {
        List<ExpenditureType> childExpenditureTypes = expenditureService.getChildExpenditureTypeByParendId(pid);
        return ExecuteResult.ok(childExpenditureTypes);
    }

    @ApiOperation("添加新的支出分类")
    @PostMapping
    public ExecuteResult<ExpenditureType> addExpenditureType(@RequestBody ExpenditureTypeParam param, BindingResult result) {
        checkParam(result);
        ExpenditureType expenditureType = expenditureService.addExpenditureType(param);
        return ExecuteResult.ok(expenditureType);
    }

    @ApiOperation("更新支出分类")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<ExpenditureType> updateExpenditureType(@PathVariable("id") @ApiParam("条目id") long id, @RequestBody ExpenditureTypeParam param, BindingResult result) {
        checkParam(result);
        param.setId(id);
        ExpenditureType expenditureType = expenditureService.updateExpenditureType(param);
        return ExecuteResult.ok(expenditureType);
    }

    @ApiOperation("删除支出分类条目")
    @DeleteMapping("/{id:\\d+}")
    public ExecuteResult<Boolean> deleteExpenditureType(@PathVariable("id") @ApiParam("条目id") Integer id) {
        boolean result = expenditureService.deleteExpenditureType(id);
        return ExecuteResult.ok(result);
    }
}
