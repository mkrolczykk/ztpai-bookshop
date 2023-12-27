package pl.bookshop.auth.util.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.bookshop.auth.util.dto.EmployeesListDto;
import pl.bookshop.auth.util.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = """
        SELECT
            su.user_id AS employeeId,
            su.name AS employeeName,
            su.surname AS employeeSurname
        FROM
            system_user su
        JOIN user_role ur ON su.user_id = ur.user_id
        JOIN role r ON ur.role_id = r.role_id
        WHERE
            r.name = \'ROLE_EMPLOYEE\'
    """, nativeQuery = true)
    List<EmployeesListDto> getEmployees();

    boolean existsByUsername(String username);

}
