package com.library.management.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum AvailabilityStatus {
        AVAILABLE, CHECKED_OUT;

        @JsonCreator
        public static AvailabilityStatus fromString(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Availability status is required");
            }
            try {
                return AvailabilityStatus.valueOf(value.toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid availability status. Must be 'AVAILABLE' or 'CHECKED_OUT'"
                );
            }
        }
        @JsonValue
        public String toValue() {
            return this.name().toLowerCase();
        }
    }

    @Id
    private String bookId;
    @NotBlank(message = "title can not be empty")
    private String title;
    @NotBlank(message = "author can not be empty")
    private String author;
    private AvailabilityStatus  availability;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
