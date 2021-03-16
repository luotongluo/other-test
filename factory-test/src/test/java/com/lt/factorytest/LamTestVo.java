package com.lt.factorytest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description LamTestVo
 * @date 2021/2/19 10:24
 */
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class LamTestVo implements Serializable {
    private static final long serialVersionUID = -2285645856392195673L;

    //    @Getter
//    @Setter
    private int agel;

    //    @Getter
//    @Setter
    private String name;

}
