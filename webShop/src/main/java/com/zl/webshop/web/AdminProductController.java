/**
 * <p>
 * Title: AdminProductController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.web;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductImage;
import com.zl.webshop.service.AdminProductService;
import com.zl.webshop.service.FileService;

/**
 * <p>
 * Title: AdminProductController
 * </p>
 * <p>
 * Description:后台管理产品控制器 负责产品的增删改查事件
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 */
@Controller
@RequestMapping(value = "/admin/products")
public class AdminProductController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AdminProductService adminProductService;
  @Autowired
  private FileService fileService;


  /**
   * 
   * <p>
   * Title: getProducts
   * </p>
   * <p>
   * Description: 获取商品
   * </p>
   * 
   * @param searchText 搜索关键词 可以null
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  @RequestMapping(value = "", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getProducts(
      @RequestParam(value = "searchText", required = false) String searchText,
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    Result<List<ProductExecution>> result = null;
    try {
      result = new Result<List<ProductExecution>>(true,
          adminProductService.getProducts(searchText, offset, limit));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteProducts
   * </p>
   * <p>
   * Description: 删除一个产品
   * </p>
   * 
   * @param product 产品
   * @return 删除成功返回最新产品数
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteProducts(@RequestBody Product product) {
    Result<ProductExecution> result = null;
    try {
      result = new Result<ProductExecution>(true, adminProductService.deleteProduct(product));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: addProducts
   * </p>
   * <p>
   * Description: 添加商品
   * </p>
   * 
   * @param product 商品对象，请不要存图片名img
   * @param imageFile 商品图
   * @return 添加结果
   */
  @RequestMapping(value = "", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String addProducts(Product product,
      
      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
    Result<ProductExecution> result = null;
    try {
      // 防止图片保存冲突
      product.setImage(null);
      if (imageFile != null) {
        // 预先添加图片
        product.setImage(fileService.upLoadFile(imageFile));
      }
      result =
          new Result<ProductExecution>(true, adminProductService.addProduct(product));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
      // 发生异常删除预先添加好的图片
      if (product.getImage() != null) {
        fileService.deleteFile(product.getImage());
      }
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: updateProducts
   * </p>
   * <p>
   * Description: 更新商品
   * </p>
   * 
   * @param product 商品对象，请不要存图片名img
   * @param otherImagesFiles 商品的其他图片
   * @param imageFile 商品图
   * @return 更新结果
   */
  @RequestMapping(value = "/{productId}", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String updateProducts(Product product,
      @RequestParam(value = "otherImages", required = false) MultipartFile[] otherImagesFiles,
      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
    Result<ProductExecution> result = null;
    List<ProductImage> otherImages = new ArrayList<ProductImage>();
    ProductImage productImage = null;
    try {
      // 防止图片保存冲突
      product.setImage(null);
      if (otherImagesFiles != null && otherImagesFiles.length > 0) {
        
        for (int i = 0; i < otherImagesFiles.length; i++) {
          // 预先添加图片
          if(otherImagesFiles[i].getOriginalFilename()==null||otherImagesFiles[i].getOriginalFilename().equals("")) {
            continue;
          }
          productImage = new ProductImage();
          productImage.setId(product.getId());
          productImage.setImage(fileService.upLoadFile(otherImagesFiles[i]));
          otherImages.add(productImage);
        }
      }
      if (imageFile != null) {
        // 预先添加图片
        product.setImage(fileService.upLoadFile(imageFile));
      }
      result = new Result<ProductExecution>(true,
          adminProductService.updateProduct(product, otherImages));

    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
      // 发生异常删除预先添加好的图片
      if (otherImages.size() > 0) {
        otherImages.stream().forEach(x -> fileService.deleteFile(x.getImage()));
      }
      if (product.getImage() != null) {
        fileService.deleteFile(product.getImage());
      }
    }
    return JSON.toJSONString(result);
  }
}
