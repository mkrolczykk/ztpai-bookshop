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
@Table(name = "shipping_method", schema = "public", catalog = "bookshop_db")
public class ShippingMethodEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "method_id")
    private Long methodId;

    @Basic
    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Basic
    @Column(name = "method_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal methodCost;

    @Basic
    @Column(name = "currency_id", nullable = false)
    private Long currency;

    @Basic
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

}