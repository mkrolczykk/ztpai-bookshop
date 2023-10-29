package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.bookshop.bookservice.entity.composedKey.SystemUserAddressId;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "system_user_address", schema = "public", catalog = "bookshop_db")
public class SystemUserAddressEntity {

    @EmbeddedId
    private SystemUserAddressId id;

    @Basic
    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Basic
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

}