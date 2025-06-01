package org.example.simple_pos_mvc.DTO.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailTM {
    private String order_id;
    private String item_id;
    private int qty;
    private double price;
}
