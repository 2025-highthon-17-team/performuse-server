package kr.performuse.performuseserver.domain.room.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.performuse.performuseserver.domain.room.presentation.dto.RoomResponse
import kr.performuse.performuseserver.domain.room.service.RoomService
import kr.performuse.performuseserver.global.response.DataResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/room")
@Tag(name = "RoomController", description = "Room API 문서")
class RoomController(
    private val roomService: RoomService,
) {

    @PostMapping("/create")
    @Operation(summary = "방 생성 API")
    fun createRoom(): DataResponse<RoomResponse> {
        val response = roomService.createRoom()
        return DataResponse.ok("방 생성 성공", response)
    }

    @GetMapping("/join/{roomTsid}", produces = ["text/event-stream"])
    @Operation(summary = "방 입장 API (토큰 필요)")
    fun joinRoom(@PathVariable roomTsid: String): SseEmitter {
        val response = roomService.joinRoom(roomTsid)
        return response
    }

    @PostMapping("/publish/{roomTsid}")
    @Operation(summary = "메세지 전송 API (테스트중)")
    fun publishMessage(@PathVariable roomTsid: String) {
        roomService.publishMessage(roomTsid)
    }

}