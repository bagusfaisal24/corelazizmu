package com.example.portal.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.example.tron.util.DateTimePreferences.ASIA_JAKARTA;
import static com.example.tron.util.DateTimePreferences.RFC_3339;

@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
abstract public class BaseModel {

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_3339, timezone = ASIA_JAKARTA)
    private Timestamp createdAt;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_3339, timezone = ASIA_JAKARTA)
    private Timestamp updatedAt;

    @JsonIgnore
    private Timestamp deletedAt;

    @SuppressWarnings("unused")
    @PrePersist
    void onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
        if (createdBy == null) createdBy = 0L;
    }

    @SuppressWarnings("unused")
    @PreUpdate
    void onUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
