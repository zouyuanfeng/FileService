package com.itzyf.meizhu.controller


import com.itzyf.meizhu.bean.Result
import com.itzyf.meizhu.properties.AppProperties
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:27
 */
@RestController
class IndexController {

    @Autowired
    private val appProps: AppProperties? = null

    @RequestMapping("/")
    fun index(): String {
        return "上传文件服务"
    }

    @RequestMapping(value = ["/fileUpload"], method = [(RequestMethod.POST)])
    fun uploadFile(@RequestParam("file") file: MultipartFile): Result<String> {
        val result = Result<String>()
        if (file.isEmpty) {
            result.msg = "上传的文件为空"
            result.code = 102
            return result
        }
        LOGGER.info("上传文件:" + file.originalFilename!!)
        //临时上传的目录
        val home = "${System.getProperty("user.home")}/${appProps?.imageDir}/"

        val uploadPath = File(home)
        //如果目录不存在
        if (!uploadPath.exists()) {
            //创建目录
            uploadPath.mkdirs()
        }

        val suffix = file.originalFilename!!
                .substring(file.originalFilename!!.lastIndexOf("."))
        try {
            val path = saveBit(file.inputStream, home, suffix)
            result.msg = "上传成功"
            result.data = appProps?.imageUrl + path
            result.code = 200
        } catch (e: IOException) {
            e.printStackTrace()
            result.msg = "上传失败"
            result.code = 102
        }

        return result
    }

    @Throws(IOException::class)
    private fun saveBit(inStream: InputStream, path: String, suffix: String): String {
        //        String guid = Guid.getRandomGUID();

        val name = SimpleDateFormat("yyyyMMddHHmmssSSS").format(Date()) + "" + (Random().nextInt(89) + 10)

        val outStream = ByteArrayOutputStream()
        //创建一个Buffer字符串
        val buffer = ByteArray(1024)
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        var len = 0
        //使用一个输入流从buffer里把数据读取出来
        inStream.use { input ->
            outStream.use {
                while (input.read().also { len = it } != -1) {
                    it.write(len)
                }
            }
        }

        //关闭输入流
        inStream.close()
        //把outStream里的数据写入内存

        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        val data = outStream.toByteArray()
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        val imageFile = File(path + name + suffix)
        //创建输出流
        val fileOutStream = FileOutputStream(imageFile)
        //写入数据
        fileOutStream.write(data)
        return name + suffix
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(IndexController::class.java.simpleName)
    }

}