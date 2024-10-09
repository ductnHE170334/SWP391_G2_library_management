package SWP391_G2.com.example.library_Management.Customer.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

}
