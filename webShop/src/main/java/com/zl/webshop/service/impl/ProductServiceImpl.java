/** 
　 * <p>Title: ProductServiceImpl.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.dao.ProductCategoryDao;
import com.zl.webshop.dao.ProductDao;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.service.ProductService;

/** 
　 * <p>Title: ProductServiceImpl</p> 
　 * <p>Description: 产品业务接口实例</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
*/
public class ProductServiceImpl implements ProductService{
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private ProductCategoryDao productCategoryDao;
  @Autowired
  private ProductDao productDao;
  
  @Override
  public List<ProductCategory> getCategories() {
    List<ProductCategory>productCategories=productCategoryDao.queryAll(0, 1000);
    return productCategories;
  }

  @Override
  public ProductExecution getProducts(int categoryId, int offset, int limit) {
    ProductExecution productExecution=null;
   
    return productExecution;
  }

  @Override
  public List<Product> searchByText(String searchText, int offset, int limit) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProductExecution getProduct(int productId, String productName) {
    // TODO Auto-generated method stub
    return null;
  }

}
