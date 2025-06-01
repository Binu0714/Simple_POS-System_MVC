package org.example.simple_pos_mvc.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SignUpDto {
    private String user_id;
    private String username;
    private String password;
    private String email;
}
