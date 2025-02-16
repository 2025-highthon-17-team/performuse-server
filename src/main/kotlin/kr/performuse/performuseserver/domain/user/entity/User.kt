package kr.performuse.performuseserver.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.performuse.performuseserver.global.entity.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User(

    override val tsid: String,

    @Column(nullable = false)
    val identifier: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    var nickname: String,

    override var createdDate: LocalDateTime,

    override var lastModifiedDate: LocalDateTime,

) : BaseEntity(tsid, createdDate, lastModifiedDate)
