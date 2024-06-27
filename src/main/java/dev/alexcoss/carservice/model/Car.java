package dev.alexcoss.carservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "car", name = "car")
@Builder
public class Car {

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = "^\\d{4}$")
    private String year;

    @OneToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    @ManyToMany
    @JoinTable(
        name = "car_category", schema = "car",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private Set<Category> categories = new HashSet<>();
}
