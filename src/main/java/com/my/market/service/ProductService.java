package com.my.market.service;

import com.my.market.model.Image;
import com.my.market.model.Product;
import com.my.market.model.User;
import com.my.market.repository.ProductRepository;
import com.my.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductByTitle(String title){
        return productRepository.findProductByTitle(title);
    }

    public void saveProduct(Principal principal, Product product, MultipartFile ... files) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        for(int i = 0; i < files.length; i++){
            MultipartFile mf = files[i];
            if(!mf.isEmpty()){
               Image image = toImageEntity(mf);
               if(i == 0)
                   image.setPreviewImage(true);
               product.addImages(image);
            }
        }
        Product savedProduct = productRepository.save(product);
        savedProduct.setPreviewImageId(savedProduct.getImageList().get(0).getId());
        productRepository.save(savedProduct);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null)
            return new User();
        return userRepository.findUserByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile mf) throws IOException {
        Image image = new Image();
        image.setName(mf.getName());
        image.setOriginalFileName(mf.getOriginalFilename());
        image.setContentType(mf.getContentType());
        image.setSize(mf.getSize());
        image.setBytes(mf.getBytes());
        return image;

    }

    public void deleteProduct(int id){
      productRepository.deleteProductById(id);
    }

    public Product getProductById(int id) {
        Optional<Product> productOptional =  productRepository.findById(id);
        return productOptional.orElse(null);
    }
}
