package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;
import com.whyalwaysmea.account.service.RecordService;
import com.whyalwaysmea.account.vo.MemberRecordsVO;
import com.whyalwaysmea.account.vo.RecordListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 20:20
 * @Description:
 */
@Api(description = "收支记录")
@RestController
@RequestMapping("/record")
public class RecordController extends BaseController {

    @Autowired
    private RecordService recordService;

    @ApiOperation("新增收支记录")
    @PostMapping
    public ExecuteResult<AccountRecord> addRecord(@RequestBody @Validated RecordParam recordParam, BindingResult bindingResult) {
        checkParam(bindingResult);
        AccountRecord accountRecord = recordService.addRecord(recordParam);
        return ExecuteResult.ok(accountRecord);
    }

    @ApiOperation("更新收支记录")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<AccountRecord> updateRecord(@PathVariable("id") @ApiParam("记录id") long id, @RequestBody RecordParam recordParam) {
        return ExecuteResult.ok();
    }

    @ApiOperation("删除收支记录")
    @DeleteMapping("/{id:\\d+}")
    public ExecuteResult<Boolean> delRecord(@PathVariable("id") @ApiParam("记录id") long id) {
        return ExecuteResult.ok();
    }

    @ApiOperation("获取收支记录列表")
    @GetMapping("")
    public ExecuteResult<List<RecordListVO>> getRecords(@RequestParam("bookId") @ApiParam("账本id") long bookId, @RequestParam("date") @ApiParam("日期 yyyy-MM") String date) {
        List<RecordListVO> records = recordService.getRecords(bookId, date);
        return ExecuteResult.ok(records);
    }

    @GetMapping("/member/{bookId}")
    @ApiOperation("成员的记账统计")
    public ExecuteResult<List<MemberRecordsVO>> getMemberRecordsVO(@PathVariable("bookId") long bookId) {
        List<MemberRecordsVO> memberRecord = recordService.getMemberRecord(bookId);
        return ExecuteResult.ok(memberRecord);
    }
}
