package pl.bookshop.bookservice.entity.composedKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SystemUserAddressId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "address_id", nullable = false)
    private Long addressId;

}
