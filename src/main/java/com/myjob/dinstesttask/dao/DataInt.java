package com.myjob.dinstesttask.dao;

import com.myjob.dinstesttask.models.Data;

import java.util.List;
import java.util.Optional;

public interface DataInt {

    List<Data> read(String tableName);

    void wright(String tableName, Data data);

}
