package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.bookshop.bookservice.entity.composedKey.ShoppingCartId;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopping_cart", schema = "public", catalog = "bookshop_db")
public class ShoppingCartEntity {

    @EmbeddedId
    private ShoppingCartId id;

    @Basic
    @Column(name = "amount", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer amount;

    @Basic
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addedAt;

}