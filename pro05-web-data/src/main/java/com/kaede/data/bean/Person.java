package com.kaede.data.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kaede
 * @create 2022-08-13 11:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;

}
