/**
 * <p>
 * Title: AdminProductServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.webshop.dao.ProductCategoryDao;
import com.zl.webshop.dao.ProductDao;
import com.zl.webshop.dao.ProductImageDao;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.entity.ProductImage;
import com.zl.webshop.service.AdminProductService;
import com.zl.webshop.service.FileService;

/**
 * <p>
 * Title: AdminProductServiceImpl
 * </p>
 * <p>
 * Description: 管理员产品业务接口实现
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 */
@Service
public class AdminProductServiceImpl implements AdminProductService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private ProductDao productDao;
  @Autowired
  private ProductCategoryDao productCategoryDao;
  @Autowired
  private ProductImageDao productImageDao;
  @Autowired
private FileService fileService;
  @Override
  public List<ProductExecution> getProducts(String searchText, int offset, int limit) {
    // 获取商品和类别
    List<Product> products = productDao.fuzzyQueryAllByText(searchText, offset, limit);
    List<ProductCategory> categories = new ArrayList<ProductCategory>();
    products.stream().forEach(x -> categories.add(productCategoryDao.queryById(x.getCategoryId())));
    // 存入返回结果
    List<ProductExecution> result = new ArrayList<ProductExecution>();
    ProductExecution productExecution = null;
    for (int i = 0; i < products.size(); ++i) {
      productExecution = new ProductExecution(categories.get(i).getId(),
          categories.get(i).getCategoryName(), products.get(i));
      //获取商品的其他图片
      productExecution.setProductImages(productImageDao.queryByProductId(products.get(i).getId()));
      result.add(productExecution);
    }
    return result;
  }


  @Override@Transactional(rollbackFor = RuntimeException.class)
  public ProductExecution updateProduct(Product product,List<ProductImage>otherImages) {
    ProductExecution productExecution = null;
    Product oldProduct=null;
    List<ProductImage>oldImages=null;
    try {
      //获取旧产品数据
      oldProduct=productDao.queryById(product.getId());
      oldImages=productImageDao.queryByProductId(product.getId());
      //先更新产品表，如果无误则开始更新图片文件
      productDao.updateProduct(product);
      //先删除图片表原有图片，再添加新图片，最后删除原有图片的物理文件
      if(otherImages.size()>0) {
        productImageDao.deleteByProductId(product.getId());
        otherImages.stream().forEach(x->{
          x.setProductId(product.getId());
          productImageDao.addImage(x);});
        oldImages.stream().forEach(x->fileService.deleteFile(x.getImage()));
      }
    //更新新图片 删除旧有图片
      if(product.getImage()!=null&&oldProduct.getImage()!=null) {
        fileService.deleteFile(oldProduct.getImage());
      }
     
      //装填返回结果
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
      productExecution.setProductImages(productImageDao.queryByProductId(product.getId()));
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }


  @Override@Transactional(rollbackFor = RuntimeException.class)
  public ProductExecution deleteProduct(Product product) {
    ProductExecution productExecution = null;
    Product oldProduct=null;
    try {
      //获取有关商品的图片信息
      oldProduct=productDao.queryById(product.getId());
      List<ProductImage>images=productImageDao.queryByProductId(product.getId());
      //删除表内数据
      productImageDao.deleteByProductId(product.getId());
      productDao.deleteProduct(product);
      //当删除表成功后执行删除文件操作
      images.stream().forEach(x->fileService.deleteFile(x.getImage()));
      if(oldProduct.getImage()!=null) {
        fileService.deleteFile(oldProduct.getImage());
      }
      int count = productDao.count();
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(count);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }


  @Override@Transactional(rollbackFor = RuntimeException.class)
  public ProductExecution addProduct(Product product) {
    ProductExecution productExecution = null;
    try {
      //添加商品和商品图片
      productDao.addProduct(product);
      //装填返回结果
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(productDao.count());
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }


  @Override
  public ProductExecution addOtherImages(Product product, List<ProductImage> otherImages) {
    ProductExecution productExecution = null;
    try {
      //添加新图片
     otherImages.stream().forEach(x->{
       x.setProductId(product.getId());
       productImageDao.addImage(x);
     });
      
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
      productExecution.setProductImages(productImageDao.queryByProductId(productExecution.getProduct().getId()));
      productExecution.setProductMaxCount(productDao.count());
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }


  @Override
  public ProductExecution deleteOtherImages(Product product) {
    ProductExecution productExecution = null;
    try {
      productImageDao.deleteByProductId(product.getId());
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
      productExecution.setProductImages(productImageDao.queryByProductId(productExecution.getProduct().getId()));
      productExecution.setProductMaxCount(productDao.count());
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }


  @Override
  public ProductExecution updateOtherImages(Product product, List<ProductImage> otherImages) {
    ProductExecution productExecution = null;
    
    try {
     
      if(otherImages.size()>0) {
        //获取原有图片用来删除物理文件
        List<ProductImage> oldImages=productImageDao.queryByProductId(product.getId());
        //删除原有图片，再添加新增图片
        productImageDao.deleteByProductId(product.getId());
        otherImages.stream().forEach(x->productImageDao.addImage(x));
        //删除原有图片的物理文件
        oldImages.stream().forEach(x->fileService.deleteFile(x.getImage()));
      }
     //装填结果
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
      productExecution.setProductImages(productImageDao.queryByProductId(productExecution.getProduct().getId()));
      productExecution.setProductMaxCount(productDao.count());
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return productExecution;
  }

}
