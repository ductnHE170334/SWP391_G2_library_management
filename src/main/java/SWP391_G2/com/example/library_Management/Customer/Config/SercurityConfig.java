package SWP391_G2.com.example.library_Management.Customer.Config;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SercurityConfig {

    private final CustomerService customerService;

    public SercurityConfig(CustomerService customerService) {
        this.customerService = customerService;
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Cho phép truy cập không cần xác thực vào các trang login, register, và các file tĩnh
                        .requestMatchers("/customer/login", "/customer/register","/fonts/**", "/css/**", "/js/**", "/images/**").permitAll()
                        // Các yêu cầu khác phải được xác thực
                        .anyRequest().authenticated()
                )
                // Sử dụng form login tùy chỉnh, không dùng login mặc định của Spring Security
                .formLogin((form) -> form
                        .loginPage("/customer/login") // URL của trang login tùy chỉnh
                        .defaultSuccessUrl("/customer/welcome", true) // URL chuyển đến sau khi login thành công
                        .permitAll() // Cho phép truy cập trang login mà không cần xác thực
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL xử lý logout
                        .logoutSuccessUrl("/customer/login?logout") // Chuyển hướng sau khi logout thành công
                        .permitAll()
                )
                .csrf().disable(); // Tắt CSRF để tránh lỗi POST khi login (có thể bật lại sau khi xử lý CSRF token đúng)

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return (email) -> {
            Customer customer = customerService.findByEmail(email);
            if (customer == null) {
                throw new UsernameNotFoundException("Users not found");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles("USER")
                    .build();
        };
    }
}
