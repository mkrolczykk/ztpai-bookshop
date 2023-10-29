package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_line", schema = "public", catalog = "bookshop_db")
public class OrderLineEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "line_id")
    private Long lineId;

    @Basic
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Basic
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Basic
    @Column(name = "total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    @Basic
    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    @Basic
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

}