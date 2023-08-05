package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    @Column(name = "cotton_part")
    @Min(value = 0, message = "Cotton part percentage cannot be negative.")
    @Max(value = 100, message = "Cotton part percentage cannot be greater than 100.")
    private Integer cottonPart;
    @Min(value = 1, message = "Quantity cannot be less than 1.")
    private Integer quantity;
}
