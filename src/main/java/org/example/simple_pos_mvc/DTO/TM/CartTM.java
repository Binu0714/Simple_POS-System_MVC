package org.example.simple_pos_mvc.DTO.TM;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CartTM {
    private String item_id;
    private String item_name;
    private double price;
    private int buying_Qty;
    private double total;
    private Button remove;

}
