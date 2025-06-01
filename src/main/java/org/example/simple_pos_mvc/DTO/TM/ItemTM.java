package org.example.simple_pos_mvc.DTO.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ItemTM {
    private String item_id;
    private String name;
    private int qty;
    private double unit_price;
}
