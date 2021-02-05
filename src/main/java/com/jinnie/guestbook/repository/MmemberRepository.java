package com.jinnie.guestbook.repository;


import com.jinnie.guestbook.entity.Mmember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MmemberRepository extends JpaRepository<Mmember, Long> {
}
