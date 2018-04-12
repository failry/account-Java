package com.whyalwaysmea.account.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.ExpenditureTypeMapper;
import com.whyalwaysmea.account.parameters.WaysTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.ExpenditureService;
import com.whyalwaysmea.account.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 16:49 2018/4/10
 * @Description: 收支分类，途径
 */
@Service
@Slf4j
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private ExpenditureTypeMapper expenditureTypeMapper;

    @Override
    public List<ExpenditureType> getAllDefaultExpenditure() {
        ExpenditureType expenditureType = new ExpenditureType();
        expenditureType.setCreatorId("-1");
        return expenditureTypeMapper.select(expenditureType);
    }

    @Override
    public PageBean<ExpenditureType> getAllParentExpenditure() {
        String currentUserId = UserUtils.getCurrentUserId();
        Example example = new Example(ExpenditureType.class);
        example.createCriteria().andEqualTo("creatorId", currentUserId).andIsNull("pid");
        Page<ExpenditureType> pageInfo = PageHelper.startPage(1, 10).doSelectPage(() -> expenditureTypeMapper.selectByExample(example));
        log.info(pageInfo.toString());
        return PageBean.data(pageInfo);
    }

    @Override
    public List<ExpenditureType> getChildExpenditureTypeByParendId(int pid) {
        String currentUserId = UserUtils.getCurrentUserId();
        ExpenditureType expenditureType = new ExpenditureType();
        expenditureType.setPid((long) pid);
        expenditureType.setCreatorId(currentUserId);
        return expenditureTypeMapper.select(expenditureType);
    }

    @Override
    public ExpenditureType addExpenditureType(WaysTypeParam param) {
        ExpenditureType newExpenditure = new ExpenditureType();
        String userId = UserUtils.getCurrentUserId();
        Long pid = param.getPid();
        if(pid != null) {
            ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(pid);
            if(expenditureType == null) {
                throw new MyException(WaysError.ERROR_Expenditure_PID);
            }
            newExpenditure.setPid(pid);
        }
        long maxOrderId = expenditureTypeMapper.getMaxOrderId(userId, pid);
        BeanUtils.copyProperties(param, newExpenditure);
        newExpenditure.setOrderId(maxOrderId + 10);
        newExpenditure.setCreatorId(userId);
        expenditureTypeMapper.insertSelective(newExpenditure);

        return newExpenditure;
    }

    @Override
    public ExpenditureType updateExpenditureType(WaysTypeParam param) {
        Long id = param.getId();
        ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(id);
        if(expenditureType == null) {
            throw new MyException(WaysError.ERROR_Expenditure_ID);
        }
        BeanUtils.copyProperties(param, expenditureType);
        expenditureTypeMapper.updateByPrimaryKey(expenditureType);
        return expenditureType;
    }

    @Override
    public boolean deleteExpenditureType(int id) {
        ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(id);
        if(expenditureType == null) {
            throw new MyException(WaysError.ERROR_Expenditure_ID);
        }
        int delete = expenditureTypeMapper.deleteByPrimaryKey(id);
        return delete == 1 ;
    }

    @Override
    public void orderExpenditureType() {

    }


}
