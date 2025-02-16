package kr.performuse.performuseserver.domain.user.exception

import kr.performuse.performuseserver.global.error.ErrorCode
import kr.performuse.performuseserver.global.error.BusinessException

class PasswordNotMatchedException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "비밀번호가 일치하지 않아요",
)
