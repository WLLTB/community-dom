package util.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import util.bean.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, String> {
    Page<File> findFileByNameIsLike(String name, Pageable pageable);
}
