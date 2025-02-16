package kr.performuse.performuseserver.domain.room.entity

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Table
import kr.performuse.performuseserver.global.entity.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "room")
class Room(

    override val tsid: String,

    @ElementCollection(fetch = FetchType.LAZY)
    val joinUserTsidList: MutableList<String>,

    override var createdDate: LocalDateTime,

    override var lastModifiedDate: LocalDateTime,

) : BaseEntity(tsid, createdDate, lastModifiedDate) {

    fun addUserTsid(tsid: String) {
        joinUserTsidList.add(tsid)
    }

    fun removeUserTsid(tsid: String) {
        joinUserTsidList.remove(tsid)
    }

}
