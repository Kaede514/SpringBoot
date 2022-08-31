package com.kaede.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kaede
 * @create 2022-08-11 16:32
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    private String name;
    private Double weight;

}
