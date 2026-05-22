package com.ssafy.study.domain.member.repository;

import com.ssafy.study.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private final Map<Long, MemberEntity> memberDB = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity member) {
        memberDB.put(member.getId(), member);
        return member;
    }

    public Optional<MemberEntity> findByUsername(String username) {
        for (MemberEntity member : memberDB.values()) {
            if (member.getUsername().equals(username)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long memberId) {
        MemberEntity member = memberDB.get(memberId);
        if (member != null) {
            return Optional.of(member);
        }
        return Optional.empty();
    }

    public void deleteById(Long memberId) {
        MemberEntity memberEntity = memberDB.get(memberId);
        MemberEntity remove = memberDB.remove(memberId);

    }

}
