package kr.performuse.performuseserver.global.jwt.exception

import kr.performuse.performuseserver.global.error.BusinessException
import kr.performuse.performuseserver.global.error.ErrorCode

class TokenNotProvidedException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "토큰이 제공되지 않았어요",
)
