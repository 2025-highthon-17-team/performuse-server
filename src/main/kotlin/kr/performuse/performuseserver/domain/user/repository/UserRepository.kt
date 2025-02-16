package kr.performuse.performuseserver.domain.user.repository

import kr.performuse.performuseserver.domain.user.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {

    fun findByTsid(tsid: String): User?

    fun findByIdentifier(identifier: String): User?

    fun existsByIdentifier(identifier: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

}
