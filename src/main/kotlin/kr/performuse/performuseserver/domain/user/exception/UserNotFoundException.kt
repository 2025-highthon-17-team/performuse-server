package kr.performuse.performuseserver.domain.user.exception

import kr.performuse.performuseserver.global.error.ErrorCode
import kr.performuse.performuseserver.global.error.BusinessException

class UserNotFoundException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "존재하지 않는 유저에요",
)
