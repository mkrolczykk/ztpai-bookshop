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
@Table(name = "address", schema = "public", catalog = "bookshop_db")
public class AddressEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "address_id")
    private Long addressId;

    @Basic
    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Basic
    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Basic
    @Column(name = "city", nullable = false)
    private String city;

    @Basic
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Basic
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

}