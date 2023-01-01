package util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import util.bean.File;

public interface FileRepository extends JpaRepository<File, String> {
}
