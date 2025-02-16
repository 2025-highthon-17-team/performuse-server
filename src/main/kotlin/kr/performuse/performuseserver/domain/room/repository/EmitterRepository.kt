package kr.performuse.performuseserver.domain.room.repository

import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap

@Component
class EmitterRepository {

    private val roomMap: ConcurrentHashMap<String, MutableMap<String, SseEmitter>> = ConcurrentHashMap()

    fun joinRoom(roomTsid: String, userTsid: String, sseEmitter: SseEmitter) {
        roomMap[roomTsid] = mutableMapOf(userTsid to sseEmitter)
    }

    fun leaveRoom(roomTsid: String, userTsid: String) {
        roomMap[roomTsid]?.remove(userTsid)
    }

    fun getRoom(roomTsid: String): MutableMap<String, SseEmitter>? = roomMap[roomTsid]

}