package kr.performuse.performuseserver.global.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(

    @Id
    val tsid: String,

    @CreatedDate
    var createdDate: LocalDateTime,

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime,

)
