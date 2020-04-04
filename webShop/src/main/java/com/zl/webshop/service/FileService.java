package com.zl.webshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * <p>
 * Title: FileService
 * </p>
 * <p>
 * Description: 上传文件接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月3日
 *         </p>
 */
public interface FileService {
  /**
   * 
   * <p>
   * Title: upLoadFile
   * </p>
   * <p>
   * Description: 上传文件并保存文件
   * </p>
   * 
   * @param upload 文件
   * @return 返回上传成功的文件名
   * @throws Exception
   */
  String upLoadFile(MultipartFile upload) throws Exception;
  /**
   * 
  *<p>Title: deleteFile</p> 
  *<p>Description: 删除文件</p> 
  　 * @param fileName 文件名 包含后缀
   */
  boolean deleteFile(String fileName);
}
