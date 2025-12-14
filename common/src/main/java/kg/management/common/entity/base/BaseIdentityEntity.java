package kg.management.common.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public abstract class BaseIdentityEntity extends MutableEntity<Long> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }
}