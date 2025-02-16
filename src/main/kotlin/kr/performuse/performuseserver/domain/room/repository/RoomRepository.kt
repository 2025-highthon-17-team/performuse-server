package kr.performuse.performuseserver.domain.room.repository

import kr.performuse.performuseserver.domain.room.entity.Room
import org.springframework.data.repository.CrudRepository

interface RoomRepository : CrudRepository<Room, String>