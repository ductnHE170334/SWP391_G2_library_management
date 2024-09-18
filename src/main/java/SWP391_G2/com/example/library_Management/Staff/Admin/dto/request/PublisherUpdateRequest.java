package SWP391_G2.com.example.library_Management.Staff.Admin.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherUpdateRequest {
    String lastName;
    String firstName;
}
