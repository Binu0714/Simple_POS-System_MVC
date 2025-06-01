package org.example.simple_pos_mvc.DTO.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderTM {
    private String order_id;
    private String customer_id;
    private String order_date;
}
