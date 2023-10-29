package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.bookshop.bookservice.entity.composedKey.SystemUserFavoriteBookId;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "system_user_favorite_book", schema = "public", catalog = "bookshop_db")
public class SystemUserFavoriteBookEntity {

    @EmbeddedId
    private SystemUserFavoriteBookId id;

    @Basic
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addedAt;

}