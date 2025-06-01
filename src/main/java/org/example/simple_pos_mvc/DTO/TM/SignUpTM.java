package org.example.simple_pos_mvc.DTO.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SignUpTM {
    private String user_id;
    private String username;
    private String password;
    private String email;
}
