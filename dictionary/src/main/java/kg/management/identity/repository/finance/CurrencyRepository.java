package kg.management.identity.repository.finance;

import kg.management.identity.entity.finance.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String>, JpaSpecificationExecutor<Currency> {
}
