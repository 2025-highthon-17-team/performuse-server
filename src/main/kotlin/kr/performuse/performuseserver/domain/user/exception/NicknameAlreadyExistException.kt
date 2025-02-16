package kr.performuse.performuseserver.domain.user.exception

import kr.performuse.performuseserver.global.error.ErrorCode
import kr.performuse.performuseserver.global.error.BusinessException

class NicknameAlreadyExistException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "이미 존재하는 닉네임이에요",
)
