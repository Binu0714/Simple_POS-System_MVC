package org.example.simple_pos_mvc.DTO;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderDto {
    private String order_id;
    private String customer_id;
    private String order_date;

    private ArrayList<OrderDetailDto> orderDetailsDTOS;
}
