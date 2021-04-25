package br.com.lukkascost.commons.module.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseEntity {

    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected UUID id;

    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    protected LocalDateTime updatedAt;

    @PrePersist
    @SuppressWarnings("unused")
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    @SuppressWarnings("unused")
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
