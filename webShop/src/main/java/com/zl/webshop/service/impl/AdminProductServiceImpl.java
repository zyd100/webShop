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
import com.zl.webshop.dao.ProductCategoryDao;
import com.zl.webshop.dao.ProductDao;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.service.AdminProductService;

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

  @Override
  public List<ProductExecution> getProducts(String searchText, int offset, int limit) {
    // 获取商品和类别
    List<Product> products = productDao.fuzzyQueryAllByText(searchText, offset, limit);
    List<ProductCategory> categories = new ArrayList<ProductCategory>();
    products.stream().forEach(x -> categories.add(productCategoryDao.queryById(x.getCategoryId())));
    // 存入返回结果
    List<ProductExecution> result = new ArrayList<ProductExecution>();
    for (int i = 0; i < products.size(); ++i) {
      result.add(new ProductExecution(categories.get(i).getId(),
          categories.get(i).getCategoryName(), products.get(0)));
    }
    return result;
  }


  @Override
  public ProductExecution updateProduct(Product product) {
    ProductExecution productExecution = null;
    try {
      productDao.updateProduct(product);
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    return productExecution;
  }


  @Override
  public ProductExecution deleteProduct(Product product) {
    ProductExecution productExecution = null;
    try {
      productDao.deleteProduct(product);
      int count = productDao.count();
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(count);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    return productExecution;
  }


  @Override
  public ProductExecution addProduct(Product product) {
    ProductExecution productExecution = null;
    try {
      productDao.addProduct(product);
      ProductCategory category = productCategoryDao.queryById(product.getCategoryId());
      productExecution = new ProductExecution(category.getId(), category.getCategoryName(),
          productDao.queryById(product.getId()));
      productExecution.setProductMaxCount(productDao.count());
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    return productExecution;
  }

}
