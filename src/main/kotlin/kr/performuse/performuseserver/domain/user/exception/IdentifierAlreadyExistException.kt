package kr.performuse.performuseserver.domain.user.exception

import kr.performuse.performuseserver.global.error.ErrorCode
import kr.performuse.performuseserver.global.error.BusinessException

class IdentifierAlreadyExistException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "이미 존재하는 아이디에요",
)
