package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "currency", schema = "public", catalog = "bookshop_db")
public class CurrencyEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "currency_id")
    private Long currencyId;

    @Basic
    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Basic
    @Column(name = "shortcut")
    private String shortcut;

    @Basic
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addedAt;

}