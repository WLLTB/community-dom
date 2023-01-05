package blog.repository;

import blog.bean.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    Page<Blog> findBlogByTitleIsLike(String title, Pageable Pageable);

    Blog findBlogById(String id);
}
