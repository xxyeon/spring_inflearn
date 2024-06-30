package study.data_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class JpaBaseEntity {

    @Column(updatable = false) //값을 변경하지 못하게 updatable = false로 지정
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void perPersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now; //create할 때 updaet에도 값을 넣어놔야 나중에 쿼리날리거나 할 때 편하다
    }

    @PreUpdate
    public void perUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
