package lifestyle.bookmark.domain.member.domain.repository;

import lifestyle.bookmark.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByLoginId(String loginId);
}
