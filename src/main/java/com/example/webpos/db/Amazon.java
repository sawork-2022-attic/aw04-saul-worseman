package com.example.webpos.db;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.webpos.model.OptimizedProduct;

import com.example.webpos.model.Product;
import com.example.webpos.reposity.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class JD implements PosDB {

    private List<OptimizedProduct> products = null;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<OptimizedProduct> getProducts() {
//        try {
//            if (products == null) {
//                List<Product> l = productRepository.findAll();
//                products = transferToOptimizedProducts(l);
//            }
//        } catch (IOException e) {
//            products = new ArrayList<>();
//        }
        if (products == null) {
                List<Product> l = productRepository.findAll();
                products = transferToOptimizedProducts(l);
            }
        return products;
    }

    @Override
    public OptimizedProduct getProduct(String productId) {

        for (OptimizedProduct p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<OptimizedProduct> parseJD(String keyword) throws IOException {

        // 获取请求https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        // 解析网页
        Document document = Jsoup.parse(new URL(url), 10000);
        // 所有js的方法都能用
        Element element = document.getElementById("J_goodsList");
        // 获取所有li标签
        Elements elements = element.getElementsByTag("li");
        // System.out.println(element.html());
        List<OptimizedProduct> list = new ArrayList<>();

        // 获取元素的内容
        for (Element el : elements) {
            // 关于图片特别多的网站，所有图片都是延迟加载的
            String id = el.attr("data-spu");
            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
            String price = el.getElementsByAttribute("data-price").text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (title.indexOf("，") >= 0)
                title = title.substring(0, title.indexOf("，"));

            OptimizedProduct product = new OptimizedProduct(id, title, Double.parseDouble(price), img);

            list.add(product);
        }
        return list;
    }


    public OptimizedProduct transferToOptimizedProduct(Product product){
        OptimizedProduct optimizedProduct = new OptimizedProduct();
        optimizedProduct.setId(product.getAsin());
        if(product.getImageURLHighRes().size() > 0)
            optimizedProduct.setImage(product.getImageURLHighRes().get(0));
        if(product.getTitle() != null)
            optimizedProduct.setName(product.getTitle());
        optimizedProduct.setPrice(9999);
        return optimizedProduct;
    }

    public List<OptimizedProduct> transferToOptimizedProducts(List<Product> products){
        List<OptimizedProduct> li = new ArrayList<>();
        for(var p: products){
            li.add(transferToOptimizedProduct(p));
        }
        return li;
    }


}