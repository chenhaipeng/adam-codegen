package com.thinkme.codegen.model.config;

import lombok.Data;

import java.util.List;

@Data
public class SubTable {

    private String tableName;

    private String foreignKey;

    private List<Variable> variable;
}