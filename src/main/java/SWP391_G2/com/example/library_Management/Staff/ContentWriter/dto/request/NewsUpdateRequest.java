package SWP391_G2.com.example.library_Management.Staff.ContentWriter.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsUpdateRequest {
    String lastName;
    String firstName;
}
