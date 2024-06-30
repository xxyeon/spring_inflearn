package study.data_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 스프링데이터 jpa Auditing
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{

//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;

    /**
     * 실무에서는 BaseTimeEntity와 BaseEntity를 나눠서 적절하게 사용한다
     * 생성일과 수정일이 없는 테이블은 거의 없는 반면 수정자와 생성자는 없는 테이블이 있을 수도 있기 때문.
     */


    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}
