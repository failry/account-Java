package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.AccountRecord;
import com.whyalwaysmea.account.utils.MyMapper;
import com.whyalwaysmea.account.dto.RecordListItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRecordMapper extends MyMapper<AccountRecord> {

    /**
     * 不同类型的时间范围内金额总额
     * @param recordType    收入(1)or支出(2)
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @return
     */
    Integer currentMonthTotalMoney(@Param("recordType") int recordType, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 根据账本id，获取记录列表
     * @param bookId
     */
    List<RecordListItem> getRecordList(@Param("bookId") long bookId, @Param("date") String date);


}