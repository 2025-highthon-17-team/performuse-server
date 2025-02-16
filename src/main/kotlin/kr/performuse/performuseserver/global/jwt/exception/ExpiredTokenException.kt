package kr.performuse.performuseserver.global.jwt.exception

import kr.performuse.performuseserver.global.error.BusinessException
import kr.performuse.performuseserver.global.error.ErrorCode

class ExpiredTokenException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "토큰이 만료되었어요",
)
