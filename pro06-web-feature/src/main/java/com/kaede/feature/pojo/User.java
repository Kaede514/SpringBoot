package com.kaede.feature.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kaede
 * @create 2022-08-28 10:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;
    private String password;

}
