package kr.performuse.performuseserver.global.jwt.exception

import kr.performuse.performuseserver.global.error.BusinessException
import kr.performuse.performuseserver.global.error.ErrorCode

class SignatureNotMatchException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "서명이 일치하지 않아요",
)
