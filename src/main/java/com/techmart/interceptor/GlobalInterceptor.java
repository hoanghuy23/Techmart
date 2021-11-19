package com.techmart.interceptor;

import com.techmart.model.Category;
import com.techmart.service.CategoryService;
import com.techmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    List<Category> pCategoriesList = null;
    List<Map<Integer, Object>> subCategoriesList = new ArrayList<>();
    List<Map<Integer, Object>> prodCategoriesList = new ArrayList<>();

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //Get category
        if(pCategoriesList == null && subCategoriesList.isEmpty() && prodCategoriesList.isEmpty()){
            initCategory();
        }
        request.setAttribute("pCategoriesList",pCategoriesList);
        request.setAttribute("subCategoriesList",subCategoriesList);
        request.setAttribute("prodCategoriesList",prodCategoriesList);
    }

    private void initCategory() {
        try{
            pCategoriesList = categoryService.findParentCategories();
        }catch (Exception e){
            System.out.println("---------ERROR COLLECT PARENT_CATEGORY---------:"+e.getMessage());
        }
//      Get subCategory by parentId
        List<Map<String,Object>> productCategories = new ArrayList<>();

        if(pCategoriesList != null){
            for(int i = 0; i < pCategoriesList.size(); i++){
                List<Category> subCateList = categoryService.findSubCategoriesById(pCategoriesList.get(i).getId());
                Map<Integer,Object> subCategory = new HashMap<>();
                subCategory.put(pCategoriesList.get(i).getId(),subCateList);
                subCategoriesList.add(subCategory);

                System.out.println("-----------------PARENT Category: "+pCategoriesList.get(i).getName());
                System.out.println("-----------------STARTING GET PRODUCT CATEGORY--------------------");

                for(int j = 0; j < subCateList.size(); j++){
                    List<Category> productCateList = categoryService.findSubCategoriesById(subCateList.get(j).getId());
                    Map<Integer,Object> prodCategory = new HashMap<>();
                    prodCategory.put(subCateList.get(j).getId(),productCateList);
                    prodCategoriesList.add(prodCategory);
                }
            }
        }
    }
}
