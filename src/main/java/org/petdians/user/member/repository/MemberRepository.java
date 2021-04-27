package org.petdians.user.member.repository;


import org.petdians.user.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, String> {



}
