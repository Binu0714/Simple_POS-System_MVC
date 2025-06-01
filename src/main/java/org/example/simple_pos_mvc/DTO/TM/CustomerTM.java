package org.example.simple_pos_mvc.DTO.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CustomerTM {
    private String cus_id;
    private String cus_name;
    private String cus_nic;
    private String cus_email;
    private String cus_phone;
    private String cus_address;
}
