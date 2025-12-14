package kg.management.identity.repository.user;

import kg.management.identity.entity.user.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, UserPhone.Id>, JpaSpecificationExecutor<UserPhone> {
}
