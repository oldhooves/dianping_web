package com.sunda.dao;

import java.util.List;

import com.sunda.bean.Dic;

public interface DicDao {
    List<Dic> select(Dic dic);
}