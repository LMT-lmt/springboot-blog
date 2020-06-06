package cn.imlmt.blog;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.mapper.BlogMapper;
import cn.imlmt.blog.mapper.TypeMapper;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogShowBean;
import cn.imlmt.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootBlogApplicationTests {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    BlogService blogService;


    @Test
    void test1(){
        List<Type> hotType = typeMapper.getHotType(5);
        for (Type type : hotType) {
            System.out.println(type);
        }
    }
    @Test
    void test2(){
        List<BlogListBean> blogList = blogMapper.getRecommendBlogList(5);
        for (BlogListBean bean : blogList) {
            System.out.println(bean);
        }
    }

    @Test
    void test3(){
        List<BlogShowBean> list = blogMapper.getLatestBlogList();
        for (BlogShowBean bean : list) {
            System.out.println(bean);
        }
    }

    @Test
    void test4(){
        Blog blog = blogMapper.getBlogById(3l);
        System.out.println(blog);
    }

    @Test
    void test6(){
        Blog blog = blogService.getBlogById(3l);
        System.out.println(blog);
    }

    @Test
    void test5(){
        List<BlogShowBean> list = blogMapper.getSearchBlogList("是");
        for (BlogShowBean bean : list) {
            System.out.println(bean);
        }
    }

    @Test
    void test7(){

    }

    @Test
    void contextLoads() {

        Blog blog = blogMapper.getBlogById(3l);
        System.out.println(blog);
    }

}
