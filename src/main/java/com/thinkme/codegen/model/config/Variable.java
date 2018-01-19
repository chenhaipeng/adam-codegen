package com.thinkme.codegen.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Variable {
    private String name;
    private String value;
}