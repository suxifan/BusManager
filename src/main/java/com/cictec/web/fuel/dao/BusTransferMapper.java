package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusTransfer;

public interface BusTransferMapper {
    int deleteByPrimaryKey(String busTransferId);

    int insert(BusTransfer record);

    int insertSelective(BusTransfer record);

    BusTransfer selectByPrimaryKey(String busTransferId);

    int updateByPrimaryKeySelective(BusTransfer record);

    int updateByPrimaryKey(BusTransfer record);
}