package com.zl.webshop.service.impl;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.zl.webshop.service.FileService;
import cn.hutool.core.util.IdUtil;

/**
 * 
 * <p>
 * Title: FileServiceImpl
 * </p>
 * <p>
 * Description: 上传文件业务实例
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年4月3日
 * </p>
 */
@Service
public class FileServiceImpl implements FileService {
  private Logger logger = LoggerFactory.getLogger(getClass());
private static final String PATH = "D:\\upload\\";
  @Override
  public String upLoadFile(MultipartFile upload) throws Exception {
    String newFileName = null;
    if (upload != null && upload.getOriginalFilename() != null
        && upload.getOriginalFilename().length() > 0) {
      // 图片上传物理路径
      String imgPath = PATH;
      // 获取图片原始名称
      String originalFilename = upload.getOriginalFilename();

      // 生成新的图片名称
      // 为了避免文件名冲突，使用java的UUID随机生成字符串再加上原来图片后缀名构成新的名字
      newFileName =
          IdUtil.simpleUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
      // 新图片
      File newFile = new File(imgPath + newFileName);

      // 将内存中的数据写入磁盘
      try {
        upload.transferTo(newFile);
      } catch (Exception e) {
        logger.error(e.getMessage());
        throw e;
      }

    }
    // 返回已保存的文件名
    return newFileName;
  }

  @Override
  public boolean deleteFile(String fileName) {
    File file=new File(PATH+fileName);
    boolean flag=false;
    if(file.isFile()) {
      flag=file.delete();
    }
    return flag;
  }

}
