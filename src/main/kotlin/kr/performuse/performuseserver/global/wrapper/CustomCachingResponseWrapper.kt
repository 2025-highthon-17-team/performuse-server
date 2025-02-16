package kr.performuse.performuseserver.global.wrapper

import jakarta.servlet.ServletOutputStream
import jakarta.servlet.WriteListener
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponseWrapper
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter

class CustomCachingResponseWrapper(response: HttpServletResponse) : HttpServletResponseWrapper(response) {

    private val cachedContent = ByteArrayOutputStream()
    private var printWriter: PrintWriter? = null

    override fun getOutputStream(): ServletOutputStream {
        return object : ServletOutputStream() {
            override fun isReady(): Boolean = true
            override fun setWriteListener(writeListener: WriteListener?) {}

            override fun write(b: Int) {
                cachedContent.write(b)
                response.outputStream.write(b)
            }
        }
    }

    override fun getWriter(): PrintWriter {
        if (printWriter == null) {
            printWriter = PrintWriter(OutputStreamWriter(cachedContent, characterEncoding), true)
        }
        return printWriter!!
    }

    fun getCachedContent(): ByteArray {
        printWriter?.flush()
        return cachedContent.toByteArray()
    }

}