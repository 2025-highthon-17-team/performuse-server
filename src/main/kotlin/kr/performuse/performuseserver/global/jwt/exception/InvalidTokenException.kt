package kr.performuse.performuseserver.global.jwt.exception

import kr.performuse.performuseserver.global.error.BusinessException
import kr.performuse.performuseserver.global.error.ErrorCode

class InvalidTokenException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "유효하지 않은 토큰이에요",
)
