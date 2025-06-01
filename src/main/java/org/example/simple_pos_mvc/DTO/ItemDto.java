package org.example.simple_pos_mvc.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ItemDto {
    private String item_id;
    private String name;
    private int qty;
    private double unit_price;
}
