package org.example.simple_pos_mvc.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDto {
    private String order_id;
    private String item_id;
    private int qty;
    private double price;
}
