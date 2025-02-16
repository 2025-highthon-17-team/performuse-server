package kr.performuse.performuseserver.global.util

import com.github.f4b6a3.tsid.TsidCreator
import org.springframework.stereotype.Component

@Component
class TsidUtil {

    fun createTsid() = TsidCreator.getTsid().toString()

}
