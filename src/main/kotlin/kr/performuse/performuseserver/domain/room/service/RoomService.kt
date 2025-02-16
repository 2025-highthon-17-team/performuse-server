package kr.performuse.performuseserver.domain.room.service

import kr.performuse.performuseserver.domain.room.entity.Room
import kr.performuse.performuseserver.domain.room.presentation.dto.RoomResponse
import kr.performuse.performuseserver.domain.room.repository.EmitterRepository
import kr.performuse.performuseserver.domain.room.repository.RoomRepository
import kr.performuse.performuseserver.global.util.QrcodeUtil
import kr.performuse.performuseserver.global.util.S3Util
import kr.performuse.performuseserver.global.util.SecurityUtil
import kr.performuse.performuseserver.global.util.TsidUtil
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime

@Service
class RoomService(
    private val s3Util: S3Util,
    private val tsidUtil: TsidUtil,
    private val qrcodeUtil: QrcodeUtil,
    private val securityUtil: SecurityUtil,
    private val roomRepository: RoomRepository,
    private val emitterRepository: EmitterRepository,
) {

    fun createRoom(): RoomResponse {

        val room = Room(
            tsid = tsidUtil.createTsid(),
            joinUserTsidList = mutableListOf(),
            createdDate = LocalDateTime.now(),
            lastModifiedDate = LocalDateTime.now(),
        )

        roomRepository.save(room)

        val qrcode = qrcodeUtil.generateQrcode(room.tsid)

        val qrcodeUrl = s3Util.uploadImage("${room.tsid}/qrcode.png", qrcode)

        return RoomResponse(
            qrcodeUrl = qrcodeUrl,
        )
    }

    fun
            joinRoom(roomTsid: String): SseEmitter {

        val userTsid = securityUtil.getTsid()

        val emitter = SseEmitter(60 * 60 * 1000L).apply {
            onCompletion { emitterRepository.leaveRoom(roomTsid, userTsid) }
            onTimeout { emitterRepository.leaveRoom(roomTsid, userTsid) }
            onError { emitterRepository.leaveRoom(roomTsid, userTsid) }
        }

        emitterRepository.joinRoom(roomTsid, userTsid, emitter)

        emitter.send(emitter)

        return emitter
    }

    fun publishMessage(roomTsid: String) {

        val room = emitterRepository.getRoom(roomTsid) ?: return

        room.forEach { (_, emitter) ->
            emitter.send("message")
        }

    }

}