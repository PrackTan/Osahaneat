package com.cybersoft.osahaneat.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // phải có phương thức khởi tạo rỗng (contructor) và phương thức có tham số
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "cate_id")
    private int cateId;
    @Column(name = "res_id")
    private int resId;
}
